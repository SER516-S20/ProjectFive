import java.awt.*;
import java.util.List;

/**
 * Compiler checks the validity of the graph in the right working space
 *
 * @author Karandeep Singh Grewal
 * @author Aditya Bajaj
 * @author Praveen Kumar P
 * @since March 15, 2020
 */
public class Compiler {
    PaneRightTab currentTab = Database.selectedTab;

    /**
     * Checks for the errors in the current tab
     *
     * @return Error
     */
    public String compile() {
        String parenthesisError = getParError();
        if (!parenthesisError.equals("No Error"))
            return parenthesisError;
        if (getTotalOp("@") > 0) {
            hasLoop();
            if (!hasLoop())
                return "@ is not having the loop";
        }
        return areAllOperatorsConnected() ? "Success" : "Connections Pending";
    }

    /**
     * Checks for the errors related to "( and ")" in the current tab
     *
     * @return Error
     */
    String getParError() {
        if (getTotalOp("(") == 0)
            return "No '(' operator";
        if (getTotalOp("(") > 1)
            return "There should only one '(' operator";
        if (getTotalOp(")") == 0)
            return "No ')' operator";
        if (getTotalOp(")") > 1)
            return "There should only one ')' operator";
        return "No Error";
    }

    /**
     * Checks if all the operators' connectors are connected once or not
     *
     * @return true is connected
     */
    boolean areAllOperatorsConnected() {
        for (Component component : currentTab.getComponents()
        ) {
            if (component instanceof Op)
                if (!isOperatorConnected((Op) component))
                    return false;
        }
        return true;
    }

    /**
     * Counts the number of operators in the current tab with "opLabel" symbol
     *
     * @return Total Operators of that type
     */
    int getTotalOp(String operatorLabel) {
        int count = 0;
        for (Component component : currentTab.getComponents()
        ) {
            Op operator = (Op) component;
            if (operator.label.equals(operatorLabel)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Checks if the operator is connected or not
     *
     * @return True if operator connected
     */
    boolean isOperatorConnected(Op operator) {
        for (Component component : operator.inputConnector.getComponents()) {
            if (component instanceof Connector)
                if (!((Connector) component).connected)
                    return false;
        }
        for (Component component : operator.outputConnector.getComponents()) {
            if (component instanceof Connector)
                if (!((Connector) component).connected)
                    return false;
        }
        return true;
    }

    /**
     * Checks if all the "@" operators in the current tab have a loop or not
     *
     * @return False if any "@" operator has no loop
     */
    boolean hasLoop() {
        List<Connector> src = Database.selectedTab.src;
        List<Connector> desc = Database.selectedTab.dest;
        Graph graph = new Graph(currentTab.getComponentCount());
        for (int i = 0; i < src.size(); i++) {
            Op op1 = src.get(i).op;
            Op op2 = desc.get(i).op;
            int minID = MainFrame.PANE_RIGHT.getSelectedIndex() == 0 ? 8 : 1;
            graph.addEdge(op1.ID - minID, op2.ID - minID);
        }
        return (graph.isCyclic());
    }
}
