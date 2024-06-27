package GUI;

import Interfaccie.Pagine;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ToolBar implements Pagine {

    private JPanel MainPanel;
    private JLabel TitoloApp;

    public ToolBar(final MainJFrame frame){
        TitoloApp.setText(frame.getNomeApp());

    }



    public JPanel getPanel(){
        return  MainPanel;
    }

}
