package GUI;

import ExCustom.ElemetNotFound;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeNode;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class AlberoRicerca{


    ArrayList<String> PathFileFound = new ArrayList<>();

    private final DefaultMutableTreeNode root = new PersonalMutableNode("Root");

    private  final  int lenghtStringOutConsole = 8192;

    private final JTree tree;

    private String Path;

    private final HashMap<String, Integer> PathChecker = new HashMap<>();


    public AlberoRicerca(String Ricerca, String Path) throws ElemetNotFound{
        this.Path = Path;
        this.Path = this.Path.trim();
        this.Path = this.Path + "\\";
        Ricerca = FortmatRicerca(Ricerca);
        //System.out.print("Get-ChildItem -Path '"+ Path + "' -Recurse -Filter '"+ Ricerca +"' -ErrorAction SilentlyContinue | Select-Object @{Expression={$_.FullName}} | Out-String -Width " + lenghtStringOutConsole);
        FindElement("Get-ChildItem -Path '"+ Path + "' -Recurse -Filter '"+ Ricerca +"' -ErrorAction SilentlyContinue | Select-Object @{Expression={$_.FullName}} | Out-String -Width " + lenghtStringOutConsole);
        CreateTree();
        tree = new PersonalJTree(root);


    }

    public void FindElement(String command) throws ElemetNotFound{

        if(Main.isWindows){

            String[] cmd = {"C:\\Windows\\System32\\WindowsPowerShell\\v1.0\\powershell.exe", "-NoProfile", "-ExecutionPolicy", "Bypass", "-Command", command};

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
                            line = line.trim();
                            line = line.replace(Path, "");
                            PathFileFound.add(line);
                        }
                    }

                }

                //System.out.print(Path);
                //System.out.print(PathFileFound);

                process.waitFor();
                if(PathFileFound.isEmpty()){
                    throw new ElemetNotFound("Elemento non trovato");
                }


            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public ArrayList<String> GetParzialePath(int NumPath, String Arr){
        //System.out.print("get: " + Arr + "\n");
        int pos = 0;
        int oldpos = 0;
        int countpath = 0;
        Arr = Arr.toLowerCase();

        String curDirecotry;
        ArrayList<String> Path = new ArrayList<>();
        while(pos != -1) {
            pos = (PathFileFound.get(NumPath).substring(oldpos)).indexOf("\\");

            if (pos == -1) {
                curDirecotry = PathFileFound.get(NumPath).substring(oldpos);
            } else {
                curDirecotry = PathFileFound.get(NumPath).substring(oldpos, pos+oldpos);
            }


            if((!curDirecotry.equalsIgnoreCase(Arr) || Path.isEmpty()) || countpath < PathChecker.get(Arr)){

                if(curDirecotry.equalsIgnoreCase(Arr) && Path.isEmpty() && Arr.contains(":")){
                    PathChecker.replace(Arr,PathChecker.get(Arr)+1);
                    return Path;
                }

                if(curDirecotry.equalsIgnoreCase(Arr) && Path.isEmpty() && countpath == PathChecker.get(Arr)){
                    PathChecker.replace(Arr,PathChecker.get(Arr)+1);
                    return Path;
                }
                Path.add(curDirecotry);

                if(curDirecotry.equalsIgnoreCase(Arr)){
                    countpath++;
                }
            }else{
                PathChecker.replace(Arr,PathChecker.get(Arr)+1);
                //System.out.print("get2: " + Path + countpath + "\n");
                //System.out.print(PathChecker);
                return Path;
            }


            oldpos = pos+oldpos+1;

        }

        PathChecker.replace(Arr,PathChecker.get(Arr)+1);
        return Path;

    }

    public void initPathChecker(HashMap<String, Integer> Checker, ArrayList<String> Path){
        Checker.clear();
        for(String p : Path){
            //System.out.print(p + "\n");
            Checker.put(p.toLowerCase(),0);
        }
    }

    public ArrayList<String> GetPath(int NumPath){
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

            Path.add(curDirecotry);

            oldpos = pos+oldpos+1;

        }

        return Path;
    }

    public void CreateTree(){

        for(int i = 0 ; i<PathFileFound.size(); i++){
            ArrayList<String> Path = GetPath(i);
            initPathChecker(PathChecker,Path);
            for(String p : Path){
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
                if(node.getChildAt(i).toString().equalsIgnoreCase(Dato)){
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

            if (node.toString().equalsIgnoreCase(Dato)) {
                return true;
            }

        }

        return false;
    }

    public void InsertIntoTree(TreeNode node, String Dato, int numPath){
        ArrayList<String> Path = GetParzialePath(numPath,Dato);
        //System.out.print("\n" + Path + "Insert: "+ Dato + "\n");
        boolean check = true;
        for(String Direcotry: Path){
            for(int i = 0; i<node.getChildCount(); i++){
                if(node.getChildAt(i).toString().equals(Direcotry)) {
                    node = node.getChildAt(i);
                    check = true;
                    break;
                }
                check = false;

            }
            if(!check){
                break;
            }

        }

        if(ExsistOnLevel(node,Dato)) return;
        ((DefaultMutableTreeNode)node).add(new PersonalMutableNode(Dato));

    }

    public String FortmatRicerca(String Ric){
        Ric = Ric.trim();
        Ric = Ric.replace(" ", "*");
        return "*" + Ric + "*";
    }
    public JTree getTree(){return tree;}



}
