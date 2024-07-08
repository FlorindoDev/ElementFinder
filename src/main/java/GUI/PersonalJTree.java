package GUI;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

public class PersonalJTree extends JTree {

    public PersonalJTree(DefaultMutableTreeNode root){
        super(root);
        //-------------------
        DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) this.getCellRenderer();
        Icon closedIcon = new ImageIcon("src/main/java/immagini/folder.png");
        Icon openIcon = new ImageIcon("src/main/java/immagini/open-folder.png");
        Icon leafIcon = new ImageIcon("src/main/java/immagini/leaf.png");
        renderer.setClosedIcon(closedIcon);
        renderer.setOpenIcon(openIcon);
        renderer.setLeafIcon(leafIcon);
        renderer.setBackgroundNonSelectionColor(MainJFrame.BackGround);
        renderer.setBackgroundSelectionColor(MainJFrame.BackGround);

        Font font = renderer.getFont();
        renderer.setFont(font.deriveFont(Font.BOLD));
        renderer.setForeground(Color.BLACK);
        //-------------------
        this.setToggleClickCount(1); //click per aprire cartelle
        this.setBackground(MainJFrame.BackGround);

    }
}
