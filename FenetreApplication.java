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
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;


public class FenetreApplication extends JFrame 
{
    private final JPanel container = new JPanel();
    private final JPanel container2 = new JPanel();
    private final JPanel boutons = new JPanel();
    private final JPanel boutons2 = new JPanel();
    private final JPanel boutons3 = new JPanel();
    private final JPanel boutons4 = new JPanel();
    private final String[] formes = {"cercle", "triangle", "rectangle", "carre"};
    private final JButton effacer = new JButton("Effacer");
    private final JButton envoyer = new JButton("Envoyer");
    private final JButton crayon = new JButton("Crayon");
    private final JButton gomme = new JButton("Gomme");
    private final JButton enregistrer = new JButton("Enregistrer");
    private final JButton importer = new JButton("Importer");
    private final JButton exercice = new JButton("Exercice Suivant");
    private final JButton ok = new JButton("Ok");
    private final JButton annuler = new JButton("Annuler");
    private final FenetreSaisie draw = new FenetreSaisie();
    private final JLabel question = new JLabel();
    private final JLabel titre = new JLabel();
    private final JLabel reponse = new JLabel();
    private BufferedImage image = new BufferedImage(900, 500, BufferedImage.TYPE_INT_RGB);
    private int nb;
    private int fm;
    private final JFrame fenetre = new JFrame();
    private String Statut;
    JTextField Texte;
    
    public FenetreApplication()
    {
        this.setTitle("Learn2draw");
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
        
        crayon.setForeground(Color.red);
        gomme.setForeground(Color.black);
        
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
    
    private void addText(int yPos, Container containingPanel, JTextField Texte)
    {
        GridBagConstraints GBLtextf = new GridBagConstraints();
        GBLtextf.fill = GridBagConstraints.BOTH;
        GBLtextf.insets = new Insets(20, 0, 5, 0);
        GBLtextf.gridx = 1;
        GBLtextf.gridy = yPos;
        containingPanel.add(Texte, GBLtextf);
    }
    
    private String Question()
    {
        nb = (int)( Math.random()*( 3 - 1 + 1 ) ) + 1;
        fm = (int)( Math.random()*( 4 - 1 + 1 ) ) + 1;
        return "Dessiner "+nb+" "+formes[fm - 1]+"(s)";
    }
    
    public ArrayList getQuestion()
    {
         ArrayList Question = new ArrayList();
        for(int i = 0; i < nb; i++)
        {
            Question.add(formes[fm - 1]);
        }
        return Question;
    }
    
    private void fenetre(String titre)
    {
        fenetre.setTitle(titre);
        fenetre.setSize(500, 200);
        fenetre.setLocationRelativeTo(null);
        fenetre.setResizable(false);
        
        GridBagLayout panelGridBagLayout = new GridBagLayout();
        panelGridBagLayout.columnWidths = new int[] { 150, 100, 150};
        panelGridBagLayout.rowHeights = new int[] { 20, 20, 20, 20, 20, 0 };
        panelGridBagLayout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
        panelGridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
        container2.setLayout(panelGridBagLayout);
        
        addLabel(reponse, "Nom du fichier : ", 1, container2, 18);
        
        Texte = new JTextField();
        addText(2, container2, Texte);
        
        ok.addActionListener(new BoutonListener8());
        annuler.addActionListener(new BoutonListener9());
        
        boutons4.add(ok);
        boutons4.add(annuler);
        
        addPanel(boutons4, 3, container2);
        
        fenetre.setContentPane(container2);
        fenetre.setVisible(true); 
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
            crayon.setForeground(Color.red);
            gomme.setForeground(Color.black);
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
            gomme.setForeground(Color.red);
            crayon.setForeground(Color.black);
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
            Statut = "enregistrer";
            fenetre("Enregistrer l image");
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
            Statut = "importer";
            fenetre("Importer une image");
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
            try
            {
                Graphics graphic = image.getGraphics();
                File outputfile = new File("dessin_analyse.png");
                draw.paint(graphic);
                ImageIO.write(image, "png", outputfile);
                IA test = new IA(getQuestion());
                test.FindContour("dessin_analyse.png");
                addLabel(reponse, test.Analyse(), 3, container, 15);
                container.updateUI();
                reponse.setVisible(true);
            }catch (IOException e)
            {
               System.out.println(e);
            }
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
            reponse.setVisible(false);
        }
    }
    
    class BoutonListener8 implements ActionListener //ok
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
                if("importer".equals(Statut))
                {
                    image = ImageIO.read(new File(Texte.getText()));
                    draw.setImage(image);
                    container.updateUI();
                }
                else
                {
                    Graphics graphic = image.getGraphics();
                    File outputfile = new File(Texte.getText());
                    draw.paint(graphic);
                    ImageIO.write(image, "png", outputfile);
                }
                fenetre.dispose();
            } catch (IOException e)
            {
               System.out.println(e);
            }
        }
    }
    
    class BoutonListener9 implements ActionListener //annuler
    {
        /**
         * 
         * @param arg0 non utilise
         */
        @Override
        public void actionPerformed(ActionEvent arg0)
        {
            fenetre.dispose();
        }
    }
}
