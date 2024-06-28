package GUI;

import Controller.Controller;

public class Main {
    private static final Controller controller = new Controller();
    public static void main(String[] args){

        final MainJFrame frame = new MainJFrame("Element Finder", 1500,700);
        frame.SetPanel(new Home(frame, controller).getPanel());
        frame.Resize(1400,700);

    }


}