import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Compiler {

    public static void htmlGen(String title, String header, String para, OpTuple a,
                               OpTuple b, OpTuple c, OpTuple d, File dir) {
        String[] headers = header.split("\n");

        StringBuffer sb = new StringBuffer();
        sb.append("<html><head><title>");
        sb.append(title);
        sb.append("</title><body>");

        for (int i = 0; i < headers.length; i++) {
            sb.append("<h1>");
            sb.append(headers[i]);
            sb.append("</h1>");
        }

        sb.append("<p>");
        sb.append(para);
        sb.append("</p><p><a href=");
        sb.append(a.dest.replace(" ", "%20"));
        sb.append(">");
        sb.append(a.text);
        sb.append("</a></p><p><a href=");
        sb.append(b.dest.replace(" ", "%20"));
        sb.append(">");
        sb.append(b.text);
        sb.append("</a></p><p><a href=");
        sb.append(c.dest.replace(" ", "%20"));
        sb.append(">");
        sb.append(c.text);
        sb.append("</a></p><p><a href=");
        sb.append(d.dest.replace(" ", "%20"));
        sb.append(">");
        sb.append(d.text);
        sb.append("</a></p><p><a href=");
        sb.append("</body></html>");

        try {
            File file = new File(dir, title + ".html");
            FileWriter fw = new FileWriter(file);
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("Error\n");
            e.printStackTrace();
        }

    }

    public static void gameProcessor(File file, String GameName) {
        StringBuffer sb = new StringBuffer();
        File dir = new File(System.getProperty("user.dir") + File.separator + "Game_HTML_Files" + File.separator + GameName);
        dir.mkdir();
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                sb.append(data);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found!");
            e.printStackTrace();
        }
        String[] chapters = sb.toString().split("----------");
        for (int i = 0; i < chapters.length; i++) {
            String[] sections = chapters[i].split("/B");
            String[] title_Junk = sections[0].strip().split("]");
            String title = title_Junk[1].strip();

            String[] header_Junk = sections[1].strip().split("]");
            String header = header_Junk[1].strip();

            String[] para_Junk = sections[2].strip().split("]");
            String para = para_Junk[1].strip();
            para = para.replace("#", "<br><br>");
            String[] options = sections[3].split("#");

            String[] op1_stuff = options[0].split("\\|");
            String op1_text = op1_stuff[0].strip();
            String op1_dest = op1_stuff[1] + ".html";
            OpTuple op1 = new OpTuple(op1_text, op1_dest);

            String[] op2_stuff = options[1].split("\\|");
            String op2_text = op2_stuff[0].strip();
            String op2_dest = op2_stuff[1] + ".html";
            OpTuple op2 = new OpTuple(op2_text, op2_dest);

            String[] op3_stuff = options[2].split("\\|");
            String op3_text = op3_stuff[0].strip();
            String op3_dest = op3_stuff[1] + ".html";
            OpTuple op3 = new OpTuple(op3_text, op3_dest);

            String[] op4_stuff = options[3].split("\\|");
            String op4_text = op4_stuff[0].strip();
            String op4_dest = op4_stuff[1] + ".html";
            OpTuple op4 = new OpTuple(op4_text, op4_dest);

            htmlGen(title, header, para, op1, op2, op3, op4, dir);
        }

    }

    public static void gameFileGen(ArrayList<NodeHandler.Node> NodeArray, String name) {
        try {
            File f = new File(System.getProperty("user.dir") + File.separator + "Game_Text_Files" + File.separator + name + ".txt");
            FileWriter fw = new FileWriter(f);
            for (int i = 0; i < NodeArray.size(); i++) {
                NodeHandler.Node n = NodeArray.get(i);
                String tit = "[T] " + n.title + "/B";
                String head = "[H] " + n.header + "/B";

                if (i != 0) {
                    writeLn(fw, tit);
                } else {
                    fw.write(tit);
                }
                writeLn(fw, head);

                if (n.paragraph.contains("\n")) {
                    String[] para_lines = n.paragraph.split("\n");
                    para_lines[para_lines.length-1] = para_lines[para_lines.length-1] + "/B";
                    writeLn(fw, "[P] " + para_lines[0]);
                    writeLn(fw,"#");

                    for (int j = 1; j<=para_lines.length-2; j++) {
                        writeLn(fw, para_lines[j]);
                        writeLn(fw,"#");
                    };

                    writeLn(fw,para_lines[para_lines.length-1]);
                } else {
                    writeLn(fw, "[P] " + n.paragraph + "/B");
                }

                String s1 = n.options.get(0).text + "|" + ((n.options.get(0).destination != null) ? n.options.get(0).destination.title : "None");
                writeLn(fw, s1);
                writeLn(fw,"#");

                String s2 = n.options.get(1).text + "|" + ((n.options.get(1).destination != null) ? n.options.get(1).destination.title : "None");
                writeLn(fw, s2);
                writeLn(fw,"#");

                String s3 = n.options.get(2).text + "|" + ((n.options.get(2).destination != null) ? n.options.get(2).destination.title : "None");
                writeLn(fw, s3);
                writeLn(fw,"#");

                String s4 = n.options.get(3).text + "|" + ((n.options.get(3).destination != null) ? n.options.get(3).destination.title : "None");
                writeLn(fw, s4);

                writeLn(fw,"----------");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeLn(FileWriter fw, String s) {
        try {
            fw.write(System.lineSeparator() + s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<NodeHandler.Node> textToNodes (ArrayList<NodeHandler.Node> NodeArray, File file) {
        StringBuffer sb = new StringBuffer();
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                sb.append(data);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found!");
            e.printStackTrace();
        }
        String[] chapters = sb.toString().split("----------");
        ArrayList<String> allOptions = new ArrayList<>();
        for (int i = 0; i < chapters.length; i++) {
            String[] sections = chapters[i].split("/B");
            String[] title_Junk = sections[0].strip().split("]");
            String title = title_Junk[1].strip();

            String[] header_Junk = sections[1].strip().split("]");
            String header = header_Junk[1].strip();

            String[] para_Junk = sections[2].strip().split("]");
            String para = para_Junk[1].strip();
            para = para.replace("#", "\r\n");

            NodeHandler.Node n = new NodeHandler.Node(i+1, title, header, para);
            String[] options = sections[3].split("#");

            String[] op1_stuff = options[0].split("\\|");
            String op1_text = op1_stuff[0].strip();
            String op1_dest = op1_stuff[1];
            n.MakeOption(op1_text, n, null);
            allOptions.add(op1_dest);


            String[] op2_stuff = options[1].split("\\|");
            String op2_text = op2_stuff[0].strip();
            String op2_dest = op2_stuff[1];
            n.MakeOption(op2_text, n, null);
            allOptions.add(op2_dest);


            String[] op3_stuff = options[2].split("\\|");
            String op3_text = op3_stuff[0].strip();
            String op3_dest = op3_stuff[1];
            n.MakeOption(op3_text, n, null);
            allOptions.add(op3_dest);


            String[] op4_stuff = options[3].split("\\|");
            String op4_text = op4_stuff[0].strip();
            String op4_dest = op4_stuff[1];
            n.MakeOption(op4_text, n, null);
            allOptions.add(op4_dest);

            NodeArray.add(n);
        }

        for (int i = 0; i<NodeArray.size(); i++) {
            NodeHandler.Node n = NodeArray.get(i);
            int num = 4*(i+1);
            int[] nums = new int[4];
            nums[3] = num-1;
            nums[2] = num-2;
            nums[1] = num-3;
            nums[0] = num-4;
            for (int j = 0; j<nums.length ; j++) {
                for (int k = 0; k < NodeArray.size(); k++) {
                    if (allOptions.get(nums[j]).equals( NodeArray.get(k).title)) {
                        NodeHandler.Node dest = NodeArray.get(k);
                        n.options.get(j).destination = dest;
                    }
                }
            }
        }

        return NodeArray;
    }
}