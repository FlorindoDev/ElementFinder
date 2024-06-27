package GUI;

import Controller.Controller;
import Interfaccie.Pagine;

import javax.swing.*;

public class FileRoot implements Pagine {
    private JPanel MainPanel;

    FileRoot(MainJFrame frame,  Controller controller){

    }

    public JPanel getPanel(){
        return  MainPanel;
    }
}
