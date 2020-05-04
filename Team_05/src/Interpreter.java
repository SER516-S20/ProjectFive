import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;

public class Interpreter {
	private String result;
	private int indentNum;
	
	public Interpreter() {
		// TODO Auto-generated constructor stub
		result = "";
		indentNum = 0;
	}
	
	public void processNodes(TabInfo tabinfo)
	{
		List<Connection> connects = tabinfo.getConnectionCollection();
		Hashtable<Integer, ButtonBox> nodes = tabinfo.getshapes();
		Hashtable<Integer, HashSet<Integer>> edges = new Hashtable<Integer, HashSet<Integer>>();
		for(Connection connection:connects)
		{
			if(!edges.containsKey(connection.getSourceButton()))
			{
				edges.put(connection.getSourceButton(),new HashSet<Integer>());
			}
			edges.get(connection.getSourceButton()).add(connection.getDestButton());
		}
	}
	
	private ArrayList<Integer> findNodeTypeOf(String symbol, Hashtable<Integer, ButtonBox> table)
	{
		ArrayList<Integer> nodes = new ArrayList<Integer>();
		for(int key:table.keySet())
		{
			if(table.get(key).getSymbol().equals(symbol))
				nodes.add(key);
		}
		return nodes;
	}
}
