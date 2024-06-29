package Controller;

import java.io.*;

public class Controller {

    private String Path = "C:\\";

    private final String filePath = "src/main/java/Path.txt";
    public Controller(){

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                Path = linea;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPath(String p){

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Path = p;
    }

    public String getPath(){
        return Path;
    }

}
