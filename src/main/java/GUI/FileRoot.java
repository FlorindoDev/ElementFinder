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
    private  final Controller controller;

    private String StringToFind;

    FileRoot(final MainJFrame frame,  Controller controller, String Name){

        this.controller = controller;
        this.StringToFind = Name;

        tree1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                TreePath tp = tree1.getPathForLocation(e.getX(), e.getY());
                System.out.printf(String.valueOf(tp) + "\n");
                Desktop desktop = Desktop.getDesktop();
                String Out = Path;
                boolean check = false;
                if(tp != null){
                    for(int i = 1; i< tp.getPathCount(); i++){
                        if(Out.length() == 3 && !check){
                            check =true;
                        }else{
                            Out += "\\" + tp.getPathComponent(i).toString();
                        }

                    }
                    System.out.printf(Out + "\n");
                    File file = new File(Out);
                    try {
                        desktop.open(file);
                    } catch (IOException | IllegalArgumentException ex) {
                        JOptionPane.showMessageDialog(null, "Path non trovato, Messaggio:\n" + ex.getMessage() , "Errore Path", JOptionPane.WARNING_MESSAGE);
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
            a = new AlberoRicerca(StringToFind,Path);
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
