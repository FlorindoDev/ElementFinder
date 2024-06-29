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

public class FileRoot implements Pagine {
    private JPanel MainPanel;
    private JTree tree1;
    private JScrollPane ScrollPane;


    FileRoot(final MainJFrame frame,  Controller controller){

        tree1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                TreePath tp = tree1.getPathForLocation(e.getX(), e.getY());
                System.out.printf(String.valueOf(tp));
                Desktop desktop = Desktop.getDesktop();
                /*File file = new File("C:\\Users\\filix\\Desktop\\algoritmi lezione 20 grafi raggungibilita e componenti part 1 02.11.pdf");
                try {
                    desktop.open(file);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }*/

            }
        });

    }

    public JPanel getPanel(){
        return  MainPanel;
    }

    private void createUIComponents() {
        /*
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
        DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("Node 1");
        DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("Node 1");
        root.add(node1);
        root.add(node2);
        node1.add(new DefaultMutableTreeNode("Child 1.1"));
        node1.add(new DefaultMutableTreeNode("Child 1.2"));
        DefaultMutableTreeNode fileNode = new DefaultMutableTreeNode("file.txt");
        //fileNode.setUserObject("C:\\Users\\filix\\Desktop\\Domande Lasd.txt");
        node2.add(fileNode);*/

        AlberoRicerca a = new AlberoRicerca("*accordo-del*","C:\\Users\\filix\\Desktop\\file pap");

        tree1 = a.getTree();

        ScrollPane = new JScrollPane();
        ScrollPane.add(tree1);

    }
}
