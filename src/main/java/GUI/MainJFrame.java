package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MainJFrame  extends JFrame {

    //Colori:
        static Color BackGround = new Color(255, 255, 255);
        static Border ColoreBordi = BorderFactory.createLineBorder(Color.BLACK, 1);
        //static Color NodeClicked = new Color(168, 154, 154);
        //static Color NodeNonClicked = new Color(143, 133, 133);

    //-------------
    String NomeApp = "Element Finder";

    public MainJFrame(String Nome, int W, int H){
        super(Nome);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setSize(W, H);
        //this.setIconImage(Icon.getImage());
        this.setResizable(false);
    }

    public void SetPanel(JPanel NewMainPanel){
        this.setContentPane(NewMainPanel);
    }

    public void Resize(int W, int H){
        this.setSize(W, H);
    }

    public  String getNomeApp(){
        return NomeApp;
    }

}
