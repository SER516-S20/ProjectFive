import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;

public class Interpreter {
	private String result;
	
	public Interpreter() {
		// TODO Auto-generated constructor stub
		System.out.println("Interpreter:");
		if(Model.getTabs().containsKey("Tab")) {
			result = "Graph Tab\n" + processNodes(Model.getTabs().get("Tab"));
			System.out.println(result);
		}
	}
	
	public String getResult()
	{
		return result;
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
		//System.out.println(node.getSymbol()+"Eval Code: ");
		//printConnections(edges);
		HashSet<Integer> edge = edges.get(nodeID);
		if(edge==null && !node.getSymbol().equals(")"))return code;
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
			code += "Loop " + node.getTitle() + "->\n";
			String loop, ex;
			loop = "";
			ex = "";
			for(int id:(HashSet<Integer>)edge.clone())
			{
				if(findNodeOnPath(id,nodeID,nodes,(Hashtable<Integer, HashSet<Integer>>)edges.clone()))
				{
					edge.remove(id);
					Hashtable<Integer, HashSet<Integer>> newedges = (Hashtable<Integer, HashSet<Integer>>) edges.clone();
					newedges.remove(nodeID);
					//newedges.get(nodeID).clear();
					loop += generateCode(id,nodes,newedges).indent(4);
					//System.out.print("Loop: \n"+loop);
				}
				else
				{
					ex += node.getTitle() + "->" + nodes.get(id).getTitle() + "\n";
				}
			}
			code+=loop;
			code+="End\n";
			for(int id:(HashSet<Integer>)edge.clone())
			{
				//System.out.println("@ 2ed loop id: " +id);
				ButtonBox next = nodes.get(id);
				code+=node.getTitle()+" -> " + next.getTitle()+"\n";
				edge.remove(id);
				code+=generateCode(id,nodes,edges);
			}
			break;
		case "#":
			code += "Sub "+node.getTitle()+"\n";
			String sub = "";
			if(Model.getTabs().containsKey(node.getTitle()))
			{
				sub = processNodes(Model.getTabs().get(node.getTitle()));
				code += sub;
			}
			else
			{
				System.out.println("Sub graph " + node.getTitle()+"not exist");
			}
			for(int id:(HashSet<Integer>)edge.clone())
			{
				ButtonBox next = nodes.get(id);
				edge.remove(id);
				code+=generateCode(id,nodes,edges);
			}
			break;
		case "(":
			for(int id:edge)
			{
				ButtonBox next = nodes.get(id);
				code+=generateCode(id,nodes,edges);
				code=code.indent(4);
				code+="End\n";
			}
			break;
		case ")":
			break;
		}
		//System.out.println(node.getSymbol()+"return Code: \n"+code+"\n");
		return code;
	}
	
	private boolean findNodeOnPath(int start, int end, Hashtable<Integer, ButtonBox> nodes, Hashtable<Integer, HashSet<Integer>> edges)
	{
		//System.out.println("Searching: " + nodes.get(start).getSymbol() + " , "+ nodes.get(end).getSymbol());
		boolean found = false;
		HashSet<Integer> edge = edges.get(start);
		if(edge==null) return false;
		edge = (HashSet<Integer>) edge.clone();
		if(edge.contains(end))
		{
			found = true;
			//System.out.println("Searching: " + nodes.get(start).getSymbol() + " , "+ nodes.get(end).getSymbol() + " Found in edge");
		}
		else
			for(int node:(HashSet<Integer>)edge.clone())
			{
				edge.remove(node);
				found = findNodeOnPath(node,end,nodes,edges);
				if(found)break;
			}
		
		return found;
	}
	
	private void printConnections(Hashtable<Integer, HashSet<Integer>> edges)
	{
		System.out.println("Connections:");
		for(int id:edges.keySet())
		{
			String s, d;
			s=Model.getTabs().get("Tab").getshapes().get(id).getSymbol();
			for(int dest:edges.get(id))
			{
				d=Model.getTabs().get("Tab").getshapes().get(dest).getSymbol();
				System.out.println(s+" -> "+d);
			}
		}
	}
}
