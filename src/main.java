import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class main {
    static JFrame frame;
    public static void main (String[] args) {
        ArrayList<NodeHandler.Node> NodeArray = new ArrayList<>();
        MainMenu mainm = new MainMenu();
        mainm.menusystem(NodeArray);
    }
}
