import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class MainMenu {
    public void menusystem(ArrayList<NodeHandler.Node> NodeArray) {
        JFrame frame = new JFrame("CE301 Capstone Project"); //creates the UI window
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1000, 750));
        frame.setResizable(false);

        JPanel panel = new JPanel();
        JPanel header = new JPanel();
        JPanel infoBox = new JPanel();
        JButton newFile = new JButton();
        JButton loadFile = new JButton();
        JButton exit = new JButton();

        header.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));
        infoBox.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));

        header.add(new JLabel("<HTML><pre>Welcome to the Hypertext Game Maker tool.</pre></HTML>"));
        infoBox.add(new JLabel("""
                <HTML><pre>This tool has been developed to assist in the creation of HTML Hypertext; the main aims of this software
                is to increase the ease and simplicity for the user to allow them to create their own Hypertext game without the
                need to learn how to code in HTML.</pre></HTML>
                """));
        infoBox.add(new JLabel("""
                <HTML><pre>If you wish to start a new project click the button on the left, if you wish to continue your last project
                select the center button and open the TXT file you compiled after your last session, and if you wish to exit the 
                software please click the right-side button.</pre></HTML>
                """));
        newFile.setText("<HTML><pre>New Project</pre></HTML>");
        loadFile.setText("<HTML><pre>Load Previous Project</pre></HTML>");
        exit.setText("<HTML><pre>Exit</pre></HTML>");

        newFile.addActionListener(e -> { //button to open up the node editor
            NodeManager WindowManager = null;
            WindowManager = new NodeManager();
            assert WindowManager != null;
            WindowManager.window(NodeArray);
            frame.dispose();
        });

        loadFile.addActionListener(e -> {
            JFileChooser jFile = new JFileChooser(System.getProperty("user.dir") + File.separator + "Game_Text_Files");
            int r = jFile.showOpenDialog(null);
            if (r == JFileChooser.APPROVE_OPTION) {
                Compiler.textToNodes(NodeArray, jFile.getSelectedFile());
                NodeManager WindowManager = new NodeManager();
                WindowManager.window(NodeArray);
                frame.dispose();
            }
        });

        exit.addActionListener(e -> { //button to exit the application
            System.exit(0);
        });

        frame.add(header, BorderLayout.NORTH);
        frame.add(infoBox, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.SOUTH);
        panel.add(newFile);
        panel.add(loadFile);
        panel.add(exit);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
