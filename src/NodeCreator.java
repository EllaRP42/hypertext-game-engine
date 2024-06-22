import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class NodeCreator {
    static JFrame frame;
    public void UserInputFrame(ArrayList<NodeHandler.Node> NodeArray) throws IOException {
        frame = new JFrame("CE301 Capstone Project");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setPreferredSize(new Dimension(700, 700));
        frame.setResizable(false);

        String[] choices = new String[NodeArray.size()+1];
        choices[0] = "None";
        for (int i = 0; i<NodeArray.size(); i++) {
            choices[i+1] = NodeArray.get(i).title;
        }

        JPanel panel = new JPanel();
        JPanel panelbutt = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBorder(new EmptyBorder(new Insets(10, 20, 10, 20)));

        JLabel title = new JLabel("<HTML><pre>Title</pre></HTML>");
        JTextArea enterTitle = new JTextArea();
        enterTitle.setLineWrap(true);
        JLabel header = new JLabel("<HTML><pre>Header</pre></HTML>");
        JTextArea enterHeader = new JTextArea();
        enterHeader.setLineWrap(true);
        JLabel paragraph = new JLabel("<HTML><pre>Paragraph</pre></HTML>");
        JTextArea enterPara = new JTextArea();
        enterPara.setLineWrap(true);
        JLabel op1 = new JLabel("<HTML><pre>Option 1</pre></HTML>");
        JTextArea enterop1 = new JTextArea();
        enterop1.setLineWrap(true);
        JLabel op1box = new JLabel("<HTML><pre>Select which Node this option goes to: </pre></HTML>");
        JComboBox<String> choiceBox = new JComboBox<String>(choices);

        JLabel op2 = new JLabel("<HTML><pre>Option 2</pre></HTML>");
        JTextArea enterop2 = new JTextArea();
        enterop2.setLineWrap(true);
        JLabel op2box = new JLabel("<HTML><pre>Select which Node this option goes to: </pre></HTML>");
        JComboBox<String> choiceBox2 = new JComboBox<String>(choices);

        JLabel op3 = new JLabel("<HTML><pre>Option 3</pre></HTML>");
        JTextArea enterop3 = new JTextArea();
        enterop3.setLineWrap(true);
        JLabel op3box = new JLabel("<HTML><pre>Select which Node this option goes to: </pre></HTML>");
        JComboBox<String> choiceBox3 = new JComboBox<String>(choices);

        JLabel op4 = new JLabel("<HTML><pre>Option 4</pre></HTML>");
        JTextArea enterop4 = new JTextArea();
        enterop4.setLineWrap(true);
        JLabel op4box = new JLabel("<HTML><pre>Select which Node this option goes to: </pre></HTML>");
        JComboBox<String> choiceBox4 = new JComboBox<String>(choices);

        JButton confirm = new JButton("<HTML><pre>Save</pre></HTML>");
        panel.add(title);
        panel.add(enterTitle);
        panel.add(header);
        panel.add(enterHeader);
        panel.add(paragraph);
        panel.add(enterPara);
        panel.add(op1);
        panel.add(enterop1);
        panel.add(op1box);
        panel.add(choiceBox);
        panel.add(op2);
        panel.add(enterop2);
        panel.add(op2box);
        panel.add(choiceBox2);
        panel.add(op3);
        panel.add(enterop3);
        panel.add(op3box);
        panel.add(choiceBox3);
        panel.add(op4);
        panel.add(enterop4);
        panel.add(op4box);
        panel.add(choiceBox4);
        panelbutt.add(confirm);


        confirm.addActionListener(e -> {
            String TitleRead = enterTitle.getText();
            String HeadRead = enterHeader.getText();
            String ParaRead = enterPara.getText();
            String op1read = enterop1.getText();
            String choiceread1 = (String) choiceBox.getSelectedItem();
            String op2read = enterop2.getText();
            String choiceread2 = (String) choiceBox2.getSelectedItem();
            String op3read = enterop3.getText();
            String choiceread3 = (String) choiceBox3.getSelectedItem();
            String op4read = enterop4.getText();
            String choiceread4 = (String) choiceBox4.getSelectedItem();

            NodeHandler.Node n = new NodeHandler.Node(NodeArray.size()+1, TitleRead, HeadRead, ParaRead);
            n.MakeOption(op1read, n, (choiceread1 != "None") ? getNode(choiceread1, NodeArray) : null);
            n.MakeOption(op2read, n, (choiceread2 != "None") ? getNode(choiceread2, NodeArray) : null);
            n.MakeOption(op3read, n, (choiceread3 != "None") ? getNode(choiceread3, NodeArray) : null);
            n.MakeOption(op4read, n, (choiceread4 != "None") ? getNode(choiceread4, NodeArray) : null);
            NodeArray.add(n);

            frame.dispose();
        });

        JScrollPane scroll = new JScrollPane(panel);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        frame.add(scroll, BorderLayout.CENTER);
        frame.add(panelbutt, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public NodeHandler.Node getNode (String title, ArrayList<NodeHandler.Node> NodeArray) {
        for (int i = 0; i<NodeArray.size(); i++){
            NodeHandler.Node n = NodeArray.get(i);
            if (n.title == title) {return n;}
        }
        return null;
    }

    public void UserEditFrame(ArrayList<NodeHandler.Node> NodeArray, NodeHandler.Node node, JFrame back) throws IOException {
        frame = new JFrame("CE301 Capstone Project");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500, 700));
        frame.setResizable(false);
        String[] choices = new String[NodeArray.size()+2];
        choices[1] = "None";
        for (int i = 1; i<NodeArray.size(); i++) {
            choices[i+1] = NodeArray.get(i).title;
        }

        String[] c1 = choices.clone();
        if (node.options.get(0).destination != null) {
            c1[0] = node.options.get(0).destination.title;
        } else {
            c1 = Arrays.copyOfRange(c1, 1,c1.length+1);
        }

        String[] c2 = choices.clone();
        if (node.options.get(1).destination != null) {
            c2[0] = node.options.get(1).destination.title;
        } else {
            c2 = Arrays.copyOfRange(c2, 1,c2.length+1);
        }

        String[] c3 = choices.clone();
        if (node.options.get(2).destination != null) {
            c3[0] = node.options.get(2).destination.title;
        } else {
            c3 = Arrays.copyOfRange(c3, 1,c3.length+1);
        }

        String[] c4 = choices.clone();
        if (node.options.get(3).destination != null) {
            c4[0] = node.options.get(3).destination.title;
        } else {
            c4 = Arrays.copyOfRange(c4, 1,c4.length+1);
        }

        JPanel panel = new JPanel();
        JPanel panelbutt = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBorder(new EmptyBorder(new Insets(10, 20, 10, 20)));

        JLabel title = new JLabel("Title");
        JTextArea enterTitle = new JTextArea(node.title);
        enterTitle.setLineWrap(true);
        JLabel header = new JLabel("Header");
        JTextArea enterHeader = new JTextArea(node.header);
        enterHeader.setLineWrap(true);
        JLabel paragraph = new JLabel("Paragraph");
        JTextArea enterPara = new JTextArea(node.paragraph);
        enterPara.setLineWrap(true);
        JLabel op1 = new JLabel("Option 1");
        JTextArea enterop1 = new JTextArea(node.options.get(0).text);
        enterop1.setLineWrap(true);
        JLabel op1box = new JLabel("Select which Node this option goes to: ");
        JComboBox<String> choiceBox = new JComboBox<String>(c1);

        JLabel op2 = new JLabel("Option 2");
        JTextArea enterop2 = new JTextArea(node.options.get(1).text);
        enterop2.setLineWrap(true);
        JLabel op2box = new JLabel("Select which Node this option goes to: ");
        JComboBox<String> choiceBox2 = new JComboBox<String>(c2);

        JLabel op3 = new JLabel("Option 3");
        JTextArea enterop3 = new JTextArea(node.options.get(2).text);
        enterop3.setLineWrap(true);
        JLabel op3box = new JLabel("Select which Node this option goes to: ");
        JComboBox<String> choiceBox3 = new JComboBox<String>(c3);

        JLabel op4 = new JLabel("Option 4");
        JTextArea enterop4 = new JTextArea(node.options.get(3).text);
        enterop4.setLineWrap(true);
        JLabel op4box = new JLabel("Select which Node this option goes to: ");
        JComboBox<String> choiceBox4 = new JComboBox<String>(c4);

        JButton confirm = new JButton("Save");
        panel.add(title);
        panel.add(enterTitle);
        panel.add(header);
        panel.add(enterHeader);
        panel.add(paragraph);
        panel.add(enterPara);
        panel.add(op1);
        panel.add(enterop1);
        panel.add(op1box);
        panel.add(choiceBox);
        panel.add(op2);
        panel.add(enterop2);
        panel.add(op2box);
        panel.add(choiceBox2);
        panel.add(op3);
        panel.add(enterop3);
        panel.add(op3box);
        panel.add(choiceBox3);
        panel.add(op4);
        panel.add(enterop4);
        panel.add(op4box);
        panel.add(choiceBox4);
        panelbutt.add(confirm);


        confirm.addActionListener(e -> {
            String TitleRead = enterTitle.getText();
            String HeadRead = enterHeader.getText();
            String ParaRead = enterPara.getText();
            String op1read = enterop1.getText();
            String choiceread1 = (String) choiceBox.getSelectedItem();
            String op2read = enterop2.getText();
            String choiceread2 = (String) choiceBox2.getSelectedItem();
            String op3read = enterop3.getText();
            String choiceread3 = (String) choiceBox3.getSelectedItem();
            String op4read = enterop4.getText();
            String choiceread4 = (String) choiceBox4.getSelectedItem();

            NodeHandler.Node n = new NodeHandler.Node(node.id, TitleRead, HeadRead, ParaRead);
            n.MakeOption(op1read, n, (choiceread1 != "None") ? getNode(choiceread1, NodeArray) : null);
            n.MakeOption(op2read, n, (choiceread2 != "None") ? getNode(choiceread2, NodeArray) : null);
            n.MakeOption(op3read, n, (choiceread3 != "None") ? getNode(choiceread3, NodeArray) : null);
            n.MakeOption(op4read, n, (choiceread4 != "None") ? getNode(choiceread4, NodeArray) : null);
            NodeArray.set(node.id-1, n);


            frame.dispose();
            back.dispose();
            new NodeVision().window(NodeArray);
        });

        frame.add(panel, BorderLayout.CENTER);
        frame.add(panelbutt, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}



