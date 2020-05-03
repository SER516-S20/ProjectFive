import java.awt.*;
import java.util.List;

/**
 * Translator translates all the symbols and their connections into graphviz code
 *
 * @author Aravind Thillai Villalan
 * @since May 2nd, 2020
 */
public class Translator {
    PanelRightTab currentTab = Database.selectedTab;

    public void translate() {

        String code="digraph ";
        StringBuilder sb=new StringBuilder();
        sb.append("digraph G {");

        List<Connector> src = Database.selectedTab.src;
        List<Connector> desc = Database.selectedTab.dest;
        //Graph graph = new Graph(currentTab.getComponentCount());
        //System.out.println("Inside translate function");
        for (int i = 0; i < src.size(); i++) {
            Op op1 = src.get(i).op;
            Op op2 = desc.get(i).op;
            //int minID = MainFrame.PANEL_RIGHT.getSelectedIndex() == 0 ? 17 : 1;
            //graph.addEdge(op1.ID-minID , op2.ID-minID);

            //add your line here
            System.out.println("src "+op1.label+" id: "+op1.ID);
            System.out.println("dest "+op2.label+" id: "+op2.ID);
            sb.append("\""+op1.label+"\" -> \""+op2.label+"\";");


        }



        sb.append("}");

        System.out.println("Translated code is "+sb.toString());

    }

}
