package GUI;

import Controller.Controller;
import Interfaccie.Pagine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home implements Pagine {

    private JPanel MainJpanel;
    private JPanel ToolBarContainer;
    private JPanel BodyContainer;
    private JPanel ToolBar;
    private JPanel Body;
    private JTextField textField1;
    private JPanel SerchPanel;
    private JButton DirecotryShowser;
    private JButton Cerca;
    private JLabel TextCerca;
    private final MainJFrame frame;
    Controller controller;

    public Home(final MainJFrame frame, Controller controller){
        this.frame = frame;
        this.controller = controller;

        DirecotryShowser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setCurrentDirectory(new java.io.File("."));
                chooser.setDialogTitle("Serch Directory");
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                chooser.setAcceptAllFileFilterUsed(false);

                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    controller.setPath(String.valueOf(chooser.getCurrentDirectory()));
                } else {
                    System.out.println("No Selection ");
                }
            }
        });

    }


    public JPanel getPanel() {
        return MainJpanel;
    }

    private void createUIComponents() {

        ToolBarContainer = new JPanel();
        BodyContainer = new JPanel();
        BodyContainer.setLayout(new GridBagLayout());
        ToolBarContainer.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH; // Si espande in entrambe le direzioni
        gbc.weighty = 1.0; // Fa sì che il componente si espanda verticalmente
        gbc.weightx = 1.0; // Fa sì che il componente si espanda orizzontalmente

        ToolBarContainer.add(new ToolBar(frame).getPanel());

        Cerca = new JButton();
        Cerca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BodyContainer.add(new FileRoot(frame, controller).getPanel(),gbc);
                BodyContainer.revalidate();  // Aggiorna il layout
                BodyContainer.repaint();
            }
        });




    }
}
