package transverse;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;

public class FenetreSaisie extends JPanel
{
    private Color CouleurPointeur = Color.BLACK; //couleur du pointeur
    private BufferedImage image;
    private int pointerSize = 8; //taille du point sur le dessin
    private ArrayList<Point> points = new ArrayList<>();  

    public FenetreSaisie()
    {
        this.setPreferredSize(new Dimension(900,500));
        this.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                points.add(new Point(e.getX() - (pointerSize / 2), e.getY() - (pointerSize / 2), pointerSize, CouleurPointeur));
                repaint();
            }
        });

        this.addMouseMotionListener(new MouseMotionListener()
        {
            @Override
            public void mouseDragged(MouseEvent e)
            {
                points.add(new Point(e.getX() - (pointerSize / 2), e.getY() - (pointerSize / 2), pointerSize, CouleurPointeur));
                repaint();
            }
            @Override
            public void mouseMoved(MouseEvent e){}
        });
    }

    @Override
     public void paintComponent(Graphics g)
    {       
        if(image == null)
        {
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
        }
        else
        {
            super.paintComponent(g);
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            g.drawImage(image, 0, 0, this);            
        }
        for(Point p : this.points)
        {
            g.setColor(p.getColor());
            g.fillOval(p.getX(), p.getY(), p.getSize(), p.getSize());
        }
    }        

    public void erase()
    {
        this.points = new ArrayList<>();
        image = null;
        repaint();
    } 
    
    public void setPointerColor(Color color)
    {
        this.CouleurPointeur = color;
    }
    
    public void setImage(BufferedImage image)
    {
        this.image = image;
    }
}