package GUI;

import Controller.Controller;
import Interfaccie.Pagine;

import javax.swing.*;

public class Home implements Pagine {

    private JPanel MainJpanel;
    private MainJFrame frame;
    Controller controller;

    public Home(MainJFrame frame, Controller controller){
        this.frame = frame;
        this.controller = controller;


    }


    public JPanel getPanel() {
        return MainJpanel;
    }

    private void createUIComponents() {
       MainJpanel = new JPanel();
       MainJpanel.add(new ToolBar(frame).getPanel());
    }
}
