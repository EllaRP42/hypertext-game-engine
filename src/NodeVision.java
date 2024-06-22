import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JScrollPane;


public class NodeVision {
    public void window(ArrayList<NodeHandler.Node> NodeArray) {
        JFrame frame = new JFrame("CE301 Capstone Project"); //creates the UI window
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1000, 750));
        frame.setResizable(false);

        JPanel panel = new JPanel();
        JPanel header = new JPanel();
        JPanel nodebuttons = new JPanel();

        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));
        header.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));
        nodebuttons.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));


        JLabel info = new JLabel("""
                <HTML><pre>Here you can view all currently made nodes and make edits to them.
                Nodes highlighted in red are nodes that have not been connected to any other nodes.</pre></HTML>
                """);
        JButton goBack = new JButton();
        goBack.setText("<HTML><pre>Back</pre></HTML>");


        boolean[] reds = new boolean[NodeArray.size()];
        for (int j = 0; j<NodeArray.size(); j++) {
            NodeHandler.Node n = NodeArray.get(j);
            int id = n.id;
            for (int k = 0; k< NodeArray.size(); k++) {
                NodeHandler.Node n2 = NodeArray.get(k);
                for (int ops = 0; ops < n2.options.size(); ops++) {
                    int dest_id = (n2.options.get(ops).destination != null) ? n2.options.get(ops).destination.id : 0;
                    if (id == dest_id) {
                        reds[j] = true;
                    }
                }
            }
        }

        for (int i = 0; i < NodeArray.size(); i++) {
            JButton nodeTrack = new JButton();
            NodeHandler.Node node = NodeArray.get(i);
            nodeTrack.setText(node.title);
           if (reds[i] == false) {
               nodeTrack.setBorder(new LineBorder(Color.red));
               nodeTrack.setPreferredSize(goBack.getPreferredSize());
           }
            nodebuttons.add(nodeTrack);

            nodeTrack.addActionListener(e -> {
                NodeCreator windowManager = new NodeCreator();
                try {
                    windowManager.UserEditFrame(NodeArray, node, frame);
                } catch (IOException f) {
                    f.printStackTrace();
                }
            });
        }

        goBack.addActionListener(e -> {
            frame.dispose();
            new NodeManager().window(NodeArray);
        });

        nodebuttons.setPreferredSize(new Dimension(1000, ((int)Math.ceil(NodeArray.size()/32.0))*400)); //method of making the scroller adapt to the amount of nodes
        JScrollPane scroll = new JScrollPane(nodebuttons);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setPreferredSize(new Dimension(800,300));

        frame.add(header, BorderLayout.PAGE_START);
        frame.add(panel, BorderLayout.PAGE_END);
        frame.add(scroll, BorderLayout.CENTER);
        header.add(info);
        panel.add(goBack);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
