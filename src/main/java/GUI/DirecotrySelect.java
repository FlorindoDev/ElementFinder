package GUI;

import Controller.Controller;

import javax.swing.*;
import java.io.File;

public class DirecotrySelect extends JFileChooser {

    public DirecotrySelect(Controller controller){

        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File(controller.getPath()));
        chooser.setDialogTitle("Serch Directory");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        int results;

        do {

            results = chooser.showOpenDialog(null);
            chooser.setCurrentDirectory(new java.io.File(controller.getPath()));

            if (results == JFileChooser.APPROVE_OPTION && new File(String.valueOf(chooser.getSelectedFile())).exists()) {
                controller.setPath(String.valueOf(chooser.getSelectedFile()));
            }else if(results != JFileChooser.CANCEL_OPTION){
                JOptionPane.showMessageDialog(null, "Cartella non valida, riprova", "Riporva", JOptionPane.WARNING_MESSAGE);
            }
        }while(!(new File(String.valueOf(chooser.getSelectedFile())).exists()) && !(results  == JFileChooser.CANCEL_OPTION));
    }

}
