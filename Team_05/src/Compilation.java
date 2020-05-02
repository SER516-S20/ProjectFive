import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
/**
 * @author Kairui Hsu
**/
public class Compilation extends Thread {
	private List<Connection> connections;
	private Hashtable<Integer, ButtonBox> shapes;
	private boolean checkValid = true;
	private int dotNumber,leftParenthesisCnt, rightParenthesisCnt, LoopButtonCnt;
	String tabName, dialogMessage;

	public Compilation(String tabName) {
	    this.tabName = tabName;
	}
	
	public void run() { 
        try { 
            if(Model.getTabs().get(tabName).getConnectionCollection() != null && Model.getTabs().get(tabName).getshapes() == null) return;
            dialogMessage = Model.getMessage();
            shapes = Model.getTabs().get(tabName).getshapes();
            connections = Model.getTabs().get(tabName).getConnectionCollection();
            if(rule1() && rule2() && rule3()) {
            	dialogMessage = dialogMessage + tabName + ": Compiled Successfully!" + "\n";
            	Model.setMessage(dialogMessage);
            }
        } 
        catch (Exception e) { 
            System.out.println ("Exception is caught"); 
        } 
    }
	//rule1: Only allow one left Parenthesis and one right Parenthesis
	public boolean rule1() {
		leftParenthesisCnt = 0;
		rightParenthesisCnt = 0;
		for(ButtonBox shapeValue : shapes.values()) {
			String str = shapeValue.getSymbol();
			switch(str) {
   				case "(":
   					leftParenthesisCnt++;
   					break;
   				case ")":
   					rightParenthesisCnt++;
   					break;
   				case "@":
   					LoopButtonCnt++;
   				default:
   					break;
			}
		}
		if(leftParenthesisCnt > 1 || rightParenthesisCnt > 1) {
			dialogMessage = dialogMessage + tabName + ": Error! More than one left parenthesis or right parenthesis" + "\n";
			Model.setMessage(dialogMessage);
			return false;
		}else if(leftParenthesisCnt == 0) {
			dialogMessage = dialogMessage + tabName + ": Error! Open Paranthesis Shape Missing" + "\n";
			Model.setMessage(dialogMessage);
   			return false;
		}else if(rightParenthesisCnt == 0) {
			dialogMessage = dialogMessage + tabName + ": Error! Close Paranthesis Shape Missing" + "\n";
			Model.setMessage(dialogMessage);
   			return false;
		}else {
			return true;
		}
	}
    //rule2: Each Bar/Dot needs be connected to at least one another Bar/Dot.
	public boolean rule2() {
		Set<Integer> keys = shapes.keySet();
		for(Integer shapeKey : keys) {
			dotNumber = shapes.get(shapeKey).getBtnDots().length;
			for(int j = 0 ; j < this.connections.size(); j++) {
				Connection finishedconnection = connections.get(j);
				if(finishedconnection.getSourceButton().equals(shapeKey) || finishedconnection.getDestButton().equals(shapeKey)) {
					dotNumber--;
					if(dotNumber == 0) {
						checkValid = true;
						break;
					}
				}else {
					checkValid = false;
				}
			}
			if(checkValid == false || dotNumber > 0) {
				dialogMessage = dialogMessage + tabName + ": Error! Each Bar/Dot needs be connected to at least one another Bar/Dot" + "\n";
				Model.setMessage(dialogMessage);
				checkValid = false;
				break;
			}
		}
		return checkValid;
	}
    //rule3: check if there is a circle inside the graph
	public boolean rule3() {
        int V = shapes.size(), E = connections.size(); 
        Graph graph = new Graph(V, E); 
        for(int i = 0 ; i < E ; i++) {
        	Connection conn = connections.get(i);
        	graph.edge[i].src = new ArrayList<>(shapes.keySet()).indexOf(conn.getSourceButton());//array.get(conn.getSourceButton()).intValue(); 
        	graph.edge[i].dest = new ArrayList<>(shapes.keySet()).indexOf(conn.getDestButton());;//array.get(conn.getDestButton()).intValue(); 
        }
        if (LoopButtonCnt > 0 && graph.isCycle(graph) == 0) {
			dialogMessage = dialogMessage + tabName + ": Error! LoopButton should have a loop connection" + "\n";
			Model.setMessage(dialogMessage);
            System.out.println( "graph doesn't contain cycle" ); 
			return false;
        }
        else {
            return true;
        }
	}
}