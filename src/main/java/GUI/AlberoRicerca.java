package GUI;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class AlberoRicerca{



    ArrayList<String> PathFileFound = new ArrayList<>();

    private final DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");

    private final JTree tree;

    public AlberoRicerca(String Ricerca, String Path){
        Ricerca = FortmatRicerca(Ricerca);
        FindElement("Get-ChildItem -Path '"+ Path + "' -Recurse -Filter '"+ Ricerca +"' -ErrorAction SilentlyContinue | Select-Object @{Expression={$_.FullName}} | Format-Table -AutoSize");
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
                        if(line.charAt(0) != '$' && line.charAt(0) != '-'){
                            line = line.replace(" ","");
                            PathFileFound.add(line);
                        }
                    }

                }

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
            if(!curDirecotry.equalsIgnoreCase(Arr)){
                Path.add(curDirecotry);
            }else{
                return Path;
            }


            oldpos = pos+oldpos+1;

        }

        return Path;

    }

    public ArrayList<String> GetPath(int NumPath){
        return GetParzialePath(NumPath,"Desktop");
    }

    public void CreateTree(){


        for(int i = 0 ; i<PathFileFound.size(); i++){
            for(String p : GetPath(i)){
                InsertIntoTree(root, p, i);
            }

        }

    }


    public void InsertIntoTree(TreeNode node, String Dato, int numPath){
        ArrayList<String> Path = GetParzialePath(numPath,Dato);
        for(String Direcotry: Path){
            for(int i = 0; i<node.getChildCount(); i++){

            }
        }

    }

    public String FortmatRicerca(String Ric){
        Ric = Ric.replace(" ", "*");
        return Ric;
    }
    public JTree getTree(){return tree;}



}
