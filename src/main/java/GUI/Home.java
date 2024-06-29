package GUI;

import Controller.Controller;
import Interfaccie.Pagine;

import javax.swing.*;
import java.awt.*;

public class Home implements Pagine {

    private JPanel MainJpanel;
    private JPanel ToolBarContainer;
    private JPanel BodyContainer;
    private final MainJFrame frame;
    Controller controller;

    public Home(final MainJFrame frame, Controller controller){
        this.frame = frame;
        this.controller = controller;
    }


    public JPanel getPanel() {
        return MainJpanel;
    }

    private void createUIComponents() {
       //MainJpanel = new JPanel();

        ToolBarContainer = new JPanel();
        BodyContainer = new JPanel();
        BodyContainer.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0; // Fa sì che il componente si espanda orizzontalmente


        ToolBarContainer.add(new ToolBar(frame).getPanel());
        BodyContainer.add(new FileRoot(frame, controller).getPanel(),gbc);




    }
}
