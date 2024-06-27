package GUI;

import Interfaccie.Pagine;

import javax.swing.*;

public class ToolBar implements Pagine {

    private JPanel MainPanel;
    private JLabel TitoloApp;

    public ToolBar(MainJFrame frame){
        TitoloApp.setText(frame.getNomeApp());

    }

    public JPanel getPanel(){
        return  MainPanel;
    }

}
