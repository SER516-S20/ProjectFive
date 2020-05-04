import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Tranxlator {
    StringBuilder stringBuilder;
    List<TranxlatorNode> nodes;
    List<TranxlatorCluster> clusters;

    Tranxlator() {
        stringBuilder = new StringBuilder();
        stringBuilder.append("digraph{\n");
        nodes = new ArrayList<>();
        clusters = new ArrayList<>();
    }

    //Entry Point
    public void translate() {

        PanelRightTab mainTab = (PanelRightTab) MainFrame.PANEL_RIGHT.getComponentAt(0);
        //For Main Tab
        for (Component component : mainTab.getComponents()
        ) {
            if (component instanceof Op) {
                Op op = (Op) component;
                TranxlatorNode node = new TranxlatorNode(op.label, generateID(op));
                nodes.add(node);
                addNodeToTranxlator(node);
                if (op.label == "#") {
                    PanelRightTab tempPanel = ListenersPanelRightTab.mapOP.get(op);
                    addCluster(tempPanel, MainFrame.PANEL_RIGHT.getComponentZOrder(tempPanel), op);
                }
            }
        }

        for (Component component : MainFrame.PANEL_RIGHT.getComponents()
        ) {
            if (component instanceof PanelRightTab) {
                PanelRightTab panelRightTab = (PanelRightTab) component;
                List<Connector> src = panelRightTab.src;
                List<Connector> desc = panelRightTab.dest;
                for (int i = 0; i < src.size(); i++) {
                    Op srcOp = src.get(i).op;
                    Op destOp = desc.get(i).op;
                    if (srcOp.label == "#") {
                        for (TranxlatorCluster cluster : clusters
                        ) {
                            if (cluster.op == srcOp) {
                                stringBuilder.append(cluster.desc.id
                                        + " -> " + generateID(destOp) + ";\n");
                            }
                        }
                    }
                    if (destOp.label == "#") {
                        for (TranxlatorCluster cluster : clusters
                        ) {
                            if (cluster.op == destOp) {
                                stringBuilder.append(generateID(srcOp)
                                        + " -> " + cluster.src.id + ";\n");
                            }
                        }
                    }
                    else
                        stringBuilder.append(generateID(srcOp)
                                + " -> " + generateID(destOp) + ";\n");
                }
            }
        }
        stringBuilder.append("\n}");
        saveGraph();
    }

    //Create new cluster and add nodes on the associated tab
    public void addCluster(PanelRightTab panelRightTab, int tabNum, Op associatedOp) {
        TranxlatorCluster cluster = new TranxlatorCluster(Integer.toString(tabNum), associatedOp);
        clusters.add(cluster);
        //adds nodes for tab
        for (Component component : panelRightTab.getComponents()
        ) {
            if (component instanceof Op) {
                Op op = (Op) component;
                TranxlatorNode node = new TranxlatorNode(op.label, generateID(op));
                nodes.add(node);
                addNodeToTranxlator(node);
                node.cluster = cluster;
                if (op.label == "(")
                    cluster.src = node;
                if (op.label == ")")
                    cluster.desc = node;
                if (op.label == "#") {
                    PanelRightTab tempPanel = ListenersPanelRightTab.mapOP.get(op);
                    addCluster(tempPanel, MainFrame.PANEL_RIGHT.getComponentZOrder(tempPanel), op);
                }
            }
        }
//        //adds connections for tab
//        List<Connector> src = panelRightTab.src;
//        List<Connector> desc = panelRightTab.dest;
//        for(int i=0;i<src.size();i++){
//            Op srcOp = src.get(i).op;
//            Op destOp = desc.get(i).op;
//            stringBuilder.append(generateID(srcOp)+" -> "+ generateID(destOp)+";\n");
//        }
        createCluster(cluster);
    }

    //add node with label to stringbuilder
    public void addNodeToTranxlator(TranxlatorNode node) {
        stringBuilder.append(node.id);
        stringBuilder.append(" [ label=\"");
        stringBuilder.append(node.label);
        stringBuilder.append("\"];\n");
    }

    //create cluster and add nodes to cluster
    public void createCluster(TranxlatorCluster cluster) {
        stringBuilder.append("\nsubgraph cluster");
        stringBuilder.append(cluster.label + "{\n");
        stringBuilder.append("label = \"tab" + cluster.label + "\";\n");
        for (TranxlatorNode node : nodes
        ) {
            if (node.cluster == cluster)
                stringBuilder.append(node.id + ";\n");
        }
        stringBuilder.append("}\n\n");
    }

    //save as file
    public void saveGraph() {
        try (FileOutputStream oS = new FileOutputStream(new File("file.dot"))) {
            oS.write(stringBuilder.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //generate id from op.ID and opLocation
    public String generateID(Op op) {
        return op.ID + "" + op.getX() + "" + op.getY();
    }
}
