import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Popup {
    public void popup(ArrayList<NodeHandler.Node> NodeArray) {
        JFrame frame = new JFrame("CE301 Capstone Project");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500,200));
        frame.setResizable(false);

        JPanel panel = new JPanel();
        JPanel header = new JPanel();
        JPanel button = new JPanel();
        JButton save = new JButton();
        JTextArea nameField = new JTextArea();

        panel.setLayout(new FlowLayout(FlowLayout.CENTER));

        panel.add(new JLabel("<HTML><pre>Please enter the name of your game.</pre></HTML>"));
        save.setText("<HTML><pre>Save</pre></HTML>");

        save.addActionListener(e -> {
            frame.dispose();
            String name = nameField.getText();
            Compiler.gameFileGen(NodeArray, name);
        });

        header.add(nameField);
        button.add(save);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(header, BorderLayout.CENTER);
        frame.add(button, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
