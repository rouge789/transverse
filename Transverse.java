package transverse;

import java.io.File;

public class Transverse
{
    public static void main(String[] args)
    {
        File opencvLibrary = new File(System.mapLibraryName("opencv_java341"));
        System.load(opencvLibrary.getAbsolutePath());
        FenetreApplication fenetre = new FenetreApplication();
    }    
}
