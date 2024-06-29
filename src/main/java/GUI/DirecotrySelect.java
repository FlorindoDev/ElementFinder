package GUI;

import Controller.Controller;

import javax.swing.*;

public class DirecotrySelect extends JFileChooser {

    public DirecotrySelect(Controller controller){
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Serch Directory");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            controller.setPath(String.valueOf(chooser.getSelectedFile()));
        }
    }

}
