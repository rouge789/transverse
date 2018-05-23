/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transverse;

import java.util.ArrayList;
import java.util.List;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;


/**
 *
 * @author Matthieu
 */
public class IA
{
    private List<MatOfPoint> contours;
    private int taille;
    private final ArrayList question;
    
    public IA(ArrayList enonce)
    {
        question = enonce;
    }
    
    
    public void FindContour(String nom)
    {
        Mat image = Imgcodecs.imread(nom);
        contours = new ArrayList<>();
        Mat gray = new Mat();
        Imgproc.cvtColor(image, gray, Imgproc.COLOR_BGR2GRAY);
        
        Imgproc.findContours(gray, contours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
        taille = contours.size();
        Mat dest = Mat.zeros(image.size(), CvType.CV_8UC3);
        Scalar white = new Scalar(0, 0, 255);
        
        for (int idx = 0; idx < contours.size()-1; idx++)
        {
            Imgproc.drawContours(dest, contours, idx, white);
        }
        Imgcodecs.imwrite("output.png",dest);  
    }
    
    public String AnalyseContour(int i)
    {
        MatOfPoint2f approxCurve = new MatOfPoint2f();
        MatOfPoint2f contour2f = new MatOfPoint2f(contours.get(i).toArray());
        double approxDistance = Imgproc.arcLength(contour2f, true) * 0.02;
        Imgproc.approxPolyDP(contour2f, approxCurve, approxDistance, true);
        if (approxCurve.total() == 3)
        {
            return "triangle";
        }
        else if (approxCurve.total() == 4)
        {
            Rect rect = Imgproc.boundingRect(contours.get(i));
            double coeff = rect.height / rect.width;
            if ((0.9 < coeff) && (coeff < 1.1))
            {
                return "carre";
            }
            else
           {
                return "rectangle";
            }
        }
        else if(approxCurve.total() == 8)
        {
            return "cercle";
        }
        else
        {
            return null;
        }
    }
    
    public String Analyse()
    {
        if(taille == 1)
        {
            return "Pas de formes a analyser";
        }
        else
        {
            for (int i = 0; i < contours.size()-1; i = i+2)
            {
                if(question.contains(AnalyseContour(i)))
                {
                    for(int j = 0; j < question.size(); j++)
                    {
                        if(question.get(j).equals((AnalyseContour(i))))
                        {
                            question.remove(j);
                            j = question.size();
                        }
                    }
                }
                else
                {   
                    if(AnalyseContour(i) == null)
                    {
                        return "Cela ne correspond pas a une forme ";
                    }
                    else
                    {
                       return "La forme "+AnalyseContour(i)+" n est pas dans la consigne";
                    }
                }
            }
        }
        if(question.isEmpty())
        {
            return "Bonne reponse";
        }
        else
        {
           return "Il manque des formes";
        }
    }
}
