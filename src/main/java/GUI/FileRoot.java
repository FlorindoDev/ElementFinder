package GUI;

import Controller.Controller;
import Interfaccie.Pagine;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class FileRoot implements Pagine {
    private JPanel MainPanel;
    private JTree tree1;
    private JScrollPane ScrollPane;

    private String Path = "C:\\Users\\filix\\Desktop\\file pap";

    FileRoot(final MainJFrame frame,  Controller controller){

        tree1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                TreePath tp = tree1.getPathForLocation(e.getX(), e.getY());
                System.out.printf(String.valueOf(tp));
                Desktop desktop = Desktop.getDesktop();
                String Out = new String(Path);
                if(tp != null){
                    for(int i = 1; i< tp.getPathCount(); i++){
                        Out += "\\" + tp.getPathComponent(i).toString();
                    }
                    File file = new File(Out);
                    try {
                        desktop.open(file);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }

            }
        });

    }

    public JPanel getPanel(){
        return  MainPanel;
    }

    private void createUIComponents() {

        AlberoRicerca a = new AlberoRicerca("*accordo-del*",Path);

        tree1 = a.getTree();

        ScrollPane = new JScrollPane();
        ScrollPane.add(tree1);

    }
}
