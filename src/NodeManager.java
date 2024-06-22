import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class NodeManager {
    public void window(ArrayList<NodeHandler.Node> NodeArray) {
        JFrame frame = new JFrame("CE301 Capstone Project"); //creates the UI window
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setSize(new Dimension(1000, 750));
        frame.setResizable(false);

        JPanel header = new JPanel();
        JPanel panel = new JPanel();

        header.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));

        JButton makeNode = new JButton();
        JButton seeNode = new JButton();
        JButton compileTXT = new JButton();
        JButton compileHTML = new JButton();

        JLabel info = new JLabel("""
                <HTML><pre>Please select 'Make New Node' if you wish to add a new node to your story.
                Please select 'See Current Nodes' to see all of the nodes you have currently made as well as allow you to edit their existing information.
                Please select 'Compile Game to Text Document' to create a .txt document with all the currently existing nodes into one major document,
                this is what the software uses to reload a previous project on the main menu. This .txt document is also used when converting your game into HTML.
                Please select 'Compile Game to HTML Folder' to create a working game with all nodes as HTML files in a dedicated folder. When compiling to HTML, please
                select your previously compiled text document.</pre></HTML>
                """);

        makeNode.setText("<HTML><pre>Make New Node</pre></HTML");
        seeNode.setText("<HTML><pre>See Current Nodes</pre></HTML");
        compileTXT.setText("<HTML><pre>Compile Game to Text Document</pre></HTML");
        compileHTML.setText("<HTML><pre>Compile Game to HTML Folder</pre></HTML");

        makeNode.addActionListener(e -> { //button to open up the node editor
            NodeCreator WindowManager = new NodeCreator();
            try {
                WindowManager.UserInputFrame(NodeArray);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        seeNode.addActionListener(e -> { //button to see current nodes
            NodeVision WindowManager = null;
            WindowManager = new NodeVision();
            assert WindowManager != null;
            WindowManager.window(NodeArray);
            frame.dispose();
        });

        compileTXT.addActionListener(e -> { //button to compile the game to a text file
            Popup p = new Popup();
            p.popup(NodeArray);
        });

        compileHTML.addActionListener(e -> { //button to compile the game to a HTML file
            JFileChooser jFile = new JFileChooser(System.getProperty("user.dir") + File.separator + "Game_Text_Files");
            jFile.showSaveDialog(null);
            Compiler.gameProcessor(jFile.getSelectedFile(), jFile.getSelectedFile().getName().replace(".txt", ""));
        });

        header.add(info);
        panel.add(makeNode);
        panel.add(seeNode);
        panel.add(compileTXT);
        panel.add(compileHTML);
        frame.add(header, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
