import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;

public class Interpreter {
	private String result;
	
	public Interpreter() {
		// TODO Auto-generated constructor stub
		if(Model.getTabs().contains("Tab")) {
			System.out.println(processNodes(Model.getTabs().get("Tab")));
		}
	}
	
	public String processNodes(TabInfo tabinfo)
	{
		List<Connection> connects = tabinfo.getConnectionCollection();
		Hashtable<Integer, ButtonBox> nodes = (Hashtable<Integer, ButtonBox>) tabinfo.getshapes().clone();
		Hashtable<Integer, HashSet<Integer>> edges = new Hashtable<Integer, HashSet<Integer>>();
		for(Connection connection:connects)
		{
			if(!edges.containsKey(connection.getSourceButton()))
			{
				edges.put(connection.getSourceButton(),new HashSet<Integer>());
			}
			edges.get(connection.getSourceButton()).add(connection.getDestButton());
		}
		ArrayList<Integer> temp = findNodeTypeOf("(",nodes);
		String code = "";
		if(temp.size()>0)
		{
			int start = temp.get(0);
			code = generateCode(start,nodes,edges);
		}
		else
		{
			System.out.print("No start symbol!!!");
		}
		return code;
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
	
	private String generateCode(int nodeID, Hashtable<Integer, ButtonBox> nodes,Hashtable<Integer, HashSet<Integer>> edges)
	{
		String code = "";
		ButtonBox node = nodes.get(nodeID);
		HashSet<Integer> edge = edges.get(nodeID);
		if(edge==null)return code;
		switch(node.getSymbol())
		{
		case "-":
		case "||":
		case "<":
		case ">":
			for(int id:edge)
			{
				ButtonBox next = nodes.get(id);
				code+=node.getTitle()+" -> " + next.getTitle()+"\n";
			}
			for(int id:(HashSet<Integer>)edge.clone())
			{
				ButtonBox next = nodes.get(id);
				edge.remove(id);
				code+=generateCode(id,nodes,edges);
			}
			break;
		case "@":
			code += node.getTitle()+" Loop->\n";
			String loop, ex;
			loop = "";
			ex = "";
			for(int id:(HashSet<Integer>)edge.clone())
			{
				if(findNodeOnPath(id,nodeID,(Hashtable<Integer, HashSet<Integer>>)edges.clone()))
				{
					edge.remove(id);
					Hashtable<Integer, HashSet<Integer>> newedges = (Hashtable<Integer, HashSet<Integer>>) edges.clone();
					newedges.remove(nodeID);
					loop += generateCode(id,nodes,newedges).indent(1);
				}
				else
				{
					ex += node.getTitle() + "->" + nodes.get(id).getTitle() + "\n";
				}
			}
			code+=loop;
			for(int id:(HashSet<Integer>)edge.clone())
			{
				
				ButtonBox next = nodes.get(id);
				code+=node.getTitle()+" -> " + next.getTitle()+"\n";
				edge.remove(id);
				code+=generateCode(id,nodes,edges);
			}
			break;
		case "#":
			code += "Sub "+node.getTitle()+"\n";
			String sub = "";
			if(Model.getTabs().contains(node.getTitle()))
			{
				sub = processNodes(Model.getTabs().get(node.getTitle()));
				code += sub.indent(1);
			}
			else
			{
				System.out.println("Sub graph " + node.getTitle()+"not exist");
			}
			break;
		case "(":
			for(int id:edge)
			{
				ButtonBox next = nodes.get(id);
				code+=generateCode(id,nodes,edges);
				code=code.indent(1);
			}
			break;
		case ")":
			break;
		}
		return code;
	}
	
	private boolean findNodeOnPath(int start, int end, Hashtable<Integer, HashSet<Integer>> edges)
	{
		boolean found = false;
		HashSet<Integer> edge = edges.get(start);
		if(edge.contains(end))
			found = true;
		else
			for(int node:(HashSet<Integer>)edge.clone())
			{
				edge.remove(node);
				found = findNodeOnPath(node,end,edges);
				if(found)break;
			}
		
		return found;
	}
}
