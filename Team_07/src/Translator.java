import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Translator translates all the symbols and their connections into graphviz code
 *
 * @author Aravind Thillai Villalan
 * @since May 2nd, 2020
 */
public class Translator {
    PanelRightTab currentTab = Database.selectedTab;

    public void translate() {
        StringBuilder sb=new StringBuilder();
        Set<Integer> hashSet= new HashSet<>();
        sb.append("digraph G {");
        List<Connector> src = Database.selectedTab.src;
        List<Connector> desc = Database.selectedTab.dest;

        for (int i = 0; i < src.size(); i++) {
            Op op1 = src.get(i).op;
            Op op2 = desc.get(i).op;
            hashSet.add(op1.ID);
            hashSet.add(op2.ID);
            PanelLog.logString("src "+op1.label+" id: "+op1.ID, Color.GRAY);
            PanelLog.logString("dest "+op2.label+" id: "+op2.ID, Color.GRAY);
            sb.append(op1.ID).append(" [label=\"").append(op1.label).append("\"];").append("\n").append(op2.ID).append(" [label=\"").append(op2.label).append("\"];").append("\n").append(op1.ID).append("->").append(op2.ID).append("\n");
        }

        for (Component component :
                currentTab.getComponents()) {
            Op op = (Op) component;
            if(!hashSet.contains(op.ID)){
                sb.append(op.ID).append(" [label=\"").append(op.label).append("\"];").append("\n").append(op.ID).append(";").append("\n");
                hashSet.add(op.ID);
            }

        }

        sb.append("}");
        String basePath = new File("graphviz/graphvizcode.txt").getAbsolutePath();
        PanelLog.logString("Translated code is written into the file "+basePath, Color.GRAY);
        PanelLog.logString("Translated code is \n"+sb.toString(), Color.GRAY);

        try{
            Files.writeString(Paths.get(basePath), sb.toString());
        }
        catch (IOException e) {
            PanelLog.logString("Error in writing the graphviz code", Color.RED);
            e.printStackTrace();
        }

    }
}
