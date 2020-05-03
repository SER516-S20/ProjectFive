import java.awt.*;
import java.io.File;
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
        Set<Integer> hashSet=new HashSet<Integer>();
        sb.append("digraph G {");
        List<Connector> src = Database.selectedTab.src;
        List<Connector> desc = Database.selectedTab.dest;

        for (int i = 0; i < src.size(); i++) {
            Op op1 = src.get(i).op;
            Op op2 = desc.get(i).op;
            hashSet.add(op1.ID);
            hashSet.add(op2.ID);
            System.out.println("src "+op1.label+" id: "+op1.ID);
            System.out.println("dest "+op2.label+" id: "+op2.ID);
            sb.append(op1.ID +" [label=\"" + op1.label + "\"];" + "\n" +
                            op2.ID +" [label=\"" + op2.label + "\"];" + "\n" +
                            op1.ID + "->" +op2.ID + "\n");
        }

        for (Component component :
                currentTab.getComponents()) {
            Op op = (Op) component;
            if(!hashSet.contains(op.ID)){
                sb.append(op.ID +" [label=\"" + op.label + "\"];" + "\n" + op.ID + ";" + "\n");
                hashSet.add(op.ID);
            }

        }

        sb.append("}");
        System.out.println("Translated code is "+sb.toString());


    }

}
