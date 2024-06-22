import java.util.ArrayList;

public class NodeHandler {

    public static class Node {
        int id;
        String title;
        String header;
        String paragraph;
        ArrayList<Option> options;

        Node(int id, String title, String header, String paragraph) {
            this.id = id;
            this.title = title;
            this.header = header;
            this.paragraph = paragraph;
            this.options = new ArrayList<Option>();
        }

        public class Option {
            Node destination;
            String text;
            String id;

            Option(Node n, String t, String id) {
                this.destination = n;
                this.text = t;
                this.id = id;
            }
        }


        public Node NodeMaker(int id, String title, String header, String paragraph) {
            Node n = new Node(id, title, header, paragraph);
            return n;
        }

        public void TitleEditor(Node n, String title) {
            n.title = title;
        }

        public void HeaderEditor(Node n, String header) {
            n.header = header;
        }

        public void ParaEditor(Node n, String paragraph) {
            n.paragraph = paragraph;
        }

        public void MakeOption(String text, Node n, Node dest) {
            int len = n.options.size();
            String id = String.valueOf(n.id) + "." + String.valueOf(len+1);
            Option op = new Option(dest, text, id);
            n.options.add(op);
        }

        public void RemoveOption(String id, Node n) {
            for (int i=0; i<n.options.size() ; i++) {
                Option op = n.options.get(i);
                if (op.id == id) {
                    n.options.remove(i);
                }
            }
        }

        public Boolean EditOptionText(Node n, String id, String newtext) {
            boolean success = false;
            for (Option op : n.options) {
                if (op.id == id) {
                    op.text = newtext;
                    success  = true;
                }
            } return success;
        }

        public void EditOptionDest (Node n, String id, Node dest) {
            for (Option op : n.options) {
                if (op.id == id) {
                    op.destination = dest;
                }
            }
        }
    }
}
