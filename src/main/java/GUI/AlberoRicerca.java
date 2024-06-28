package GUI;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class AlberoRicerca extends JTree {

    private static boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");

    ArrayList<String> PathFileFound = new ArrayList<String>();

    public AlberoRicerca(DefaultMutableTreeNode root){
        super(root);
    }

    public void BuildTree(String command){

        if(isWindows){

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

}
