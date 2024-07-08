package GUI;

import Interfaccie.Pagine;

import javax.swing.*;

public class ToolBar implements Pagine {

    private JPanel MainPanel;
    private JLabel TitoloApp;

    public ToolBar(final MainJFrame frame){
        TitoloApp.setText(frame.getNomeApp());
        TitoloApp.setBackground(MainJFrame.BackGround);

    }


    public JPanel getPanel(){
        return  MainPanel;
    }

}
