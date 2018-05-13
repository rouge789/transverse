package transverse;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;


public class FenetreApplication extends JFrame 
{
    private final JPanel container = new JPanel();
    private final JPanel boutons = new JPanel();
    private final JPanel boutons2 = new JPanel();
    private final JPanel boutons3 = new JPanel();
    private final String[] formes = {"cercle", "triangle", "rectangle", "carré"};
    private final JButton effacer = new JButton("Effacer");
    private final JButton envoyer = new JButton("Envoyer");
    private final JButton crayon = new JButton("Crayon");
    private final JButton gomme = new JButton("Gomme");
    private final JButton enregistrer = new JButton("Enregistrer");
    private final JButton importer = new JButton("Importer");
    private final JButton exercice = new JButton("Exercice Suivant");
    private final FenetreSaisie draw = new FenetreSaisie();
    private final JLabel question = new JLabel();
    private final JLabel titre = new JLabel();
    private BufferedImage image = new BufferedImage(1000, 600, BufferedImage.TYPE_INT_ARGB);
    
    public FenetreApplication()
    {
        this.setTitle("Application");
        this.setSize(1400, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        GridBagLayout panelGridBagLayout = new GridBagLayout();
        panelGridBagLayout.columnWidths = new int[] { 150, 100, 150};
        panelGridBagLayout.rowHeights = new int[] { 20, 20, 20, 20, 20, 0 };
        panelGridBagLayout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
        panelGridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
        container.setLayout(panelGridBagLayout);
        
        crayon.addActionListener(new BoutonListener());
        gomme.addActionListener(new BoutonListener2());
        effacer.addActionListener(new BoutonListener3());
        enregistrer.addActionListener(new BoutonListener4());
        importer.addActionListener(new BoutonListener5());
        envoyer.addActionListener(new BoutonListener6());
        exercice.addActionListener(new BoutonListener7());
        
        boutons.add(crayon);
        boutons.add(gomme);
        boutons.add(effacer);
        boutons2.add(enregistrer);
        boutons2.add(importer);
        boutons3.add(envoyer);
        boutons3.add(exercice);
        
        addLabel(titre, "Apprentissage de Formes", 0, container, 22);
        addLabel(question, Question(), 1, container, 15);
        addZoneSaisie(draw, 2, container);
        addPanel(boutons, 2, container);
        addPanel(boutons2, 3, container);
        addPanel(boutons3, 4, container);
        
        this.setContentPane(container);
        this.setVisible(true); 
    }
    
    private void addLabel(JLabel label, String phrase, int yPos, Container containingPanel, int Taille_Police)
    {
        label.setText(phrase);
        
        GridBagConstraints GBLlabel = new GridBagConstraints();
        GBLlabel.fill = GridBagConstraints.BOTH; //redimensionne le composant pour qu'il prenne toute la place disponible
        GBLlabel.insets = new Insets(20, 0, 5, 5); //padding (haut, gauche, bas, droite)
        GBLlabel.gridx = 1;     //colonne
        GBLlabel.gridy = yPos; //ligne
        
        Font police = new Font("Arial", Font.BOLD, Taille_Police);
        label.setFont(police);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setPreferredSize(new Dimension(600, 20));
        
        containingPanel.add(label, GBLlabel);
    }
    
    private void addZoneSaisie(FenetreSaisie saisie, int yPos, Container containingPanel)
    {
        GridBagConstraints GBLlabel = new GridBagConstraints();
        GBLlabel.fill = GridBagConstraints.BOTH; //redimensionne le composant pour qu'il prenne toute la place disponible
        GBLlabel.insets = new Insets(20, 0, 5, 5); //padding (haut, gauche, bas, droite)
        GBLlabel.gridx = 1;     //colonne
        GBLlabel.gridy = yPos; //ligne
        containingPanel.add(saisie, GBLlabel);
    }
    
    private void addPanel(JPanel panel, int yPos, Container containingPanel)
    {
        GridBagConstraints GBLlabel = new GridBagConstraints();
        GBLlabel.fill = GridBagConstraints.BOTH; //redimensionne le composant pour qu'il prenne toute la place disponible
        GBLlabel.insets = new Insets(20, 0, 5, 5); //padding (haut, gauche, bas, droite)
        GBLlabel.gridx = 2;     //colonne
        GBLlabel.gridy = yPos; //ligne
        containingPanel.add(panel, GBLlabel);
    }
    
    private String Question()
    {
        int nb = (int)( Math.random()*( 3 - 1 + 1 ) ) + 1;
        int fm = (int)( Math.random()*( 4 - 1 + 1 ) ) + 1;
        return "Dessiner "+nb+" "+formes[fm - 1]+"(s)";
    }
    
    class BoutonListener implements ActionListener //crayon
    {
        /**
         * 
         * @param arg0 non utilise
         */
        @Override
        public void actionPerformed(ActionEvent arg0)
        {
            draw.setPointerColor(Color.BLACK);
        }
    }
    
    class BoutonListener2 implements ActionListener //gomme
    {
        /**
         * 
         * @param arg0 non utilise
         */
        @Override
        public void actionPerformed(ActionEvent arg0)
        {
            draw.setPointerColor(Color.WHITE);
        }
    }
    
    class BoutonListener3 implements ActionListener //effacer
    {
        /**
         * 
         * @param arg0 non utilise
         */
        @Override
        public void actionPerformed(ActionEvent arg0)
        {
            draw.erase();
        }
    }
    
    class BoutonListener4 implements ActionListener //enregistrer
    {
        /**
         * 
         * @param arg0 non utilise
         */
        @Override
        public void actionPerformed(ActionEvent arg0)
        {
            try
            {
                Graphics graphic = image.getGraphics();
                File outputfile = new File("dessin.png");
                draw.paint(graphic);
                ImageIO.write(image, "png", outputfile);
            } catch (IOException e)
            {
               System.out.println("Erreur lors de l enregistrement de l image");
            }
        }
    }
    
    class BoutonListener5 implements ActionListener //importer
    {
        /**
         * 
         * @param arg0 non utilise
         */
        @Override
        public void actionPerformed(ActionEvent arg0)
        {
            try
            {
                image = ImageIO.read(new File("dessin.png"));
                draw.setImage(image);
                container.updateUI();
            } catch (IOException e)
            {
               System.out.println("Erreur lors de l importation de l image");
            }
        }
    }
    
    class BoutonListener6 implements ActionListener //envoyer
    {
        /**
         * 
         * @param arg0 non utilise
         */
        @Override
        public void actionPerformed(ActionEvent arg0)
        {
            
        }
    }
    
    class BoutonListener7 implements ActionListener //exercice
    {
        /**
         * 
         * @param arg0 non utilise
         */
        @Override
        public void actionPerformed(ActionEvent arg0)
        {
            question.setText(Question());
            draw.erase();
            question.repaint();
        }
    }
}
