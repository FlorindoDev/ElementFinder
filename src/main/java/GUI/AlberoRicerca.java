package GUI;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class AlberoRicerca{


    ArrayList<String> PathFileFound = new ArrayList<>();

    private final DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");

    private  final  int lenghtStringOutConsole = 8192;

    private final JTree tree;

    private String Path;


    public AlberoRicerca(String Ricerca, String Path){
        this.Path = Path;
        this.Path = this.Path.replace(" ","");
        this.Path = this.Path + "\\";
        Ricerca = FortmatRicerca(Ricerca);
        FindElement("Get-ChildItem -Path '"+ Path + "' -Recurse -Filter '"+ Ricerca +"' -ErrorAction SilentlyContinue | Select-Object @{Expression={$_.FullName}} | Out-String -Width " + lenghtStringOutConsole);
        CreateTree();
        tree = new JTree(root);

    }

    public void FindElement(String command){

        if(Main.isWindows){

            String[] cmd = {"powershell.exe", "-NoProfile", "-ExecutionPolicy", "Bypass", "-Command", command};

            ProcessBuilder builder = new ProcessBuilder(cmd);
            builder.redirectErrorStream(true);

            try {
                Process process = builder.start();

                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;


                while ((line = reader.readLine()) != null) {
                    if(!line.isEmpty()){
                        //System.out.print(line +"\n");
                        if(line.charAt(0) != '$' && line.charAt(0) != '-'){
                            line = line.replace(" ","");
                            line = line.replace(Path, "");
                            PathFileFound.add(line);
                        }
                    }

                }

                System.out.print(Path);
                System.out.print(PathFileFound);

                process.waitFor();


            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public ArrayList<String> GetParzialePath(int NumPath, String Arr){
        int pos = 0;
        int oldpos = 0;
        String curDirecotry;
        ArrayList<String> Path = new ArrayList<>();
        while(pos != -1) {
            pos = (PathFileFound.get(NumPath).substring(oldpos)).indexOf("\\");

            if (pos == -1) {
                curDirecotry = PathFileFound.get(NumPath).substring(oldpos);
            } else {
                curDirecotry = PathFileFound.get(NumPath).substring(oldpos, pos+oldpos);
            }

            //System.out.print("\n" + curDirecotry + pos);
            if(!curDirecotry.equalsIgnoreCase(Arr) || Path.isEmpty()){
                Path.add(curDirecotry);
            }else{
                return Path;
            }


            oldpos = pos+oldpos+1;

        }

        return Path;

    }

    public ArrayList<String> GetPath(int NumPath){
        return GetParzialePath(NumPath,"");
    }

    public void CreateTree(){

        for(int i = 0 ; i<PathFileFound.size(); i++){
            for(String p : GetPath(i)){
                InsertIntoTree(root, p, i);
            }

        }

    }

    public boolean Exsist(TreeNode node, String Dato){
        Queue<TreeNode> Q = new LinkedList<>();
        Q.add(node);
        while(!Q.isEmpty()){
            node = Q.remove();
            for(int i = 0; i<node.getChildCount(); i++){
                Q.add(node.getChildAt(i));
                if(node.getChildAt(i).toString().equals(Dato)){
                    return true;
                }

            }

        }

        return false;
    }

    public boolean ExsistOnLevel(TreeNode node, String Dato){
        Queue<TreeNode> Q = new LinkedList<>();

        for(int i = 0; i<node.getChildCount(); i++) {
            Q.add(node.getChildAt(i));
        }
        while(!Q.isEmpty()){
            node = Q.remove();

            if (node.toString().equals(Dato)) {
                return true;
            }

        }

        return false;
    }

    public void InsertIntoTree(TreeNode node, String Dato, int numPath){
        ArrayList<String> Path = GetParzialePath(numPath,Dato);
        //if(Exsist(root,Dato)) return;
        System.out.print(Path);
        for(String Direcotry: Path){
            for(int i = 0; i<node.getChildCount(); i++){
                if(node.getChildAt(i).toString().equals(Direcotry)) {
                    if(ExsistOnLevel(node,Dato)) return;
                    node = node.getChildAt(i);
                    if(ExsistOnLevel(node,Dato)) return;
                    break;
                }

            }

        }

        ((DefaultMutableTreeNode)node).add(new DefaultMutableTreeNode(Dato));

    }

    public String FortmatRicerca(String Ric){
        Ric = Ric.replace(" ", "*");
        return Ric;
    }
    public JTree getTree(){return tree;}



}
