package GUI;

import Controller.Controller;
import ExCustom.ElemetNotFound;
import Interfaccie.Pagine;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class FileRoot implements Pagine {
    private JPanel MainPanel;
    private JTree tree1;
    private JScrollPane ScrollPane;

    private String Path;

    private  Controller controller;

    FileRoot(final MainJFrame frame,  Controller controller){

        this.controller = controller;

        tree1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                TreePath tp = tree1.getPathForLocation(e.getX(), e.getY());
                System.out.printf(String.valueOf(tp));
                Desktop desktop = Desktop.getDesktop();
                String Out = Path;
                if(tp != null){
                    for(int i = 1; i< tp.getPathCount(); i++){
                        if(!(tp.getPathComponent(i).toString().equals("C:\\")) && !(Out.contains("C:\\"))){
                            Out += "\\" + tp.getPathComponent(i).toString();
                        }

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

        this.Path = controller.getPath();

        System.out.print("\n" + Path);
        AlberoRicerca a = null;
        try {
            a = new AlberoRicerca("*accordo-del*",Path);
            tree1 = a.getTree();

        } catch (ElemetNotFound e) {
            tree1 = new JTree(new DefaultMutableTreeNode("Root"));
            JOptionPane.showMessageDialog(null, e.getMessage() , "Errore Ricerca", JOptionPane.WARNING_MESSAGE);

        }


        //tree1 = a.getTree();

        ScrollPane = new JScrollPane();
        ScrollPane.add(tree1);

        //System.out.print("\nQUI\n");
    }
}
