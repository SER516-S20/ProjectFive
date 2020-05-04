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
    //PanelRightTab currentTab = Database.selectedTab;

    PanelRightTab currentTab;

    public void translate() {
        StringBuilder sb = new StringBuilder();
        Set<Integer> hashSet = new HashSet<>();

        int tabCount = MainFrame.PANEL_RIGHT.getTabCount();
        String tabTitle = "";
        for(int i = 0; i < tabCount; i++)
        {
            currentTab = (PanelRightTab)MainFrame.PANEL_RIGHT.getComponentAt(i);
            tabTitle = MainFrame.PANEL_RIGHT.getTitleAt(i);
            if(i==0){
                sb.append("digraph G {");
                sb.append("\n\n");
            }

            sb.append("subgraph cluster_").append(i).append(" {").append("\n");
            sb.append("style=filled;").append("\n");
            sb.append("color=lightgrey;").append("\n");
            sb.append("node [style=filled,color=white];").append("\n");

            List<Connector> src = currentTab.src;
            List<Connector> desc = currentTab.dest;

            for (int j= 0; j < src.size(); j++) {
                Op op1 = src.get(j).op;
                Op op2 = desc.get(j).op;
                hashSet.add(op1.ID);
                hashSet.add(op2.ID);
                sb.append(op1.ID).append(" [label=\"").append(op1.label).append("\"];").append("\n")
                        .append(op2.ID).append(" [label=\"").append(op2.label).append("\"];")
                        .append("\n").append(op1.ID).append("->").append(op2.ID).append(";").append("\n");
            }

            for (Component component :
                    currentTab.getComponents()) {
                Op op = (Op) component;
                if (!hashSet.contains(op.ID)) {
                    sb.append(op.ID).append(" [label=\"").append(op.label).append("\"];").append("\n").append(op.ID).append(";").append("\n");
                    hashSet.add(op.ID);
                }

            }
            sb.append("label = \"tab #").append(i).append("\";").append("\n");
            sb.append("}").append("\n\n");

            if(i==tabCount-1){
                sb.append("}");
            }

        }

        System.out.println(sb.toString());
        String basePath = new File("graphviz/graphvizcode.txt").getAbsolutePath();
        PanelLog.logString("Translated code is written into the file " + basePath, Color.WHITE);
        PanelLog.logString("Translated code is \n" + sb.toString(), Color.WHITE);

//        try {
//            Files.writeString(Paths.get(basePath), sb.toString());
//        } catch (IOException e) {
//            PanelLog.logString("Error in writing the graphviz code", Color.RED);
//            e.printStackTrace();
//        }

    }
}
