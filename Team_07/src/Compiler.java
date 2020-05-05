import java.awt.*;
import java.util.ArrayList;
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
	PanelRightTab currentTab  = Database.selectedTab;
	
	public void compile()
	{
		int tabCount = MainFrame.PANEL_RIGHT.getTabCount();
		String tabMsg;
		String tabTitle;
		String logMsg;
		int errorStatus = 0;
		for(int i = 0; i < tabCount; i++)
		{
			currentTab = (PanelRightTab)MainFrame.PANEL_RIGHT.getComponentAt(i);
			tabTitle = MainFrame.PANEL_RIGHT.getTitleAt(i);
			tabMsg = compileTab();
			logMsg = "Tab '" + tabTitle + "': " + tabMsg;
			if(tabMsg.equals("Compile success!"))
				PanelLog.logString(logMsg, Color.GREEN);
			else {
				PanelLog.logString(logMsg, Color.RED);
				errorStatus = 1;
			}
		}
		if(errorStatus == 0)
			PanelLog.logString("Compilation Success!", Color.GRAY);
		else
			PanelLog.logString("Error in Compilation", Color.RED);
	}
	/**
	 * Checks for the errors in the current tab
	 *
	 * @return Error
	 */
	 String compileTab() {
		String msg;
		String parenthesisError = getParError();
		if (!parenthesisError.equals("No Error"))
			msg = parenthesisError;
		else if (!areAllOperatorsConnected())
			msg = "Connections Pending";
		else
			msg = parseGraph();
		return msg;
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
		if (getTotalOp("<") != getTotalOp(">"))
			return "Operators '<' and '>' should be equal in number";
		return "No Error";
	}

	/**
	 * Checks if all the operators' connectors are connected once or not
	 *
	 * @return true is connected
	 */
	boolean areAllOperatorsConnected() {
		for (Component component : currentTab.getComponents()) {
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
		for (Component component : currentTab.getComponents()) {
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
	String parseGraph() {
		List<Connector> src = currentTab.src;
		List<Connector> desc = currentTab.dest;
		Graph graph = new Graph(currentTab.getComponentCount());
		for (int i = 0; i < src.size(); i++) {
			Op op1 = src.get(i).op;
			Op op2 = desc.get(i).op;
			int minID = getMinID();//MainFrame.PANEL_RIGHT.getSelectedIndex() == 0 ? 17 : 1;
			graph.addEdge(op1.ID - minID, op2.ID - minID);
		}
		if (getTotalOp("@") > 0)
			if (!graph.isCyclic())
				return "@ is not having the loop";
		return graph.countConnectedComponents() != 1 ? "Multiple Sub-Graphs present" : "Compile success!";
	}

	private int getMinID() {
		int minID;
		List<Op> allOps = new ArrayList<>();
		for (Component component : currentTab.getComponents()) {
			if (component instanceof Op)
				allOps.add((Op)component);
		}
		minID = allOps.get(0).ID;
		for (Op allOp : allOps)
			if (minID > allOp.ID)
				minID = allOp.ID;
		
		return minID;
	}
}
