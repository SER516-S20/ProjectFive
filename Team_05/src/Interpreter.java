import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;

/**
 * 
 * @author Yijian Hu
 *
 */
public class Interpreter {
	private String result;
	
	public Interpreter() {
		if(Model.getTabs().containsKey("Tab")) {
			result = "digraph Tab\n" + processNodes(Model.getTabs().get("Tab"));
		}
	}
	
	public String getResult(){
		return result;
	}
	
	public String processNodes(TabInfo tabinfo){
		List<Connection> connects = tabinfo.getConnectionCollection();
		Hashtable<Integer, ButtonBox> nodes = (Hashtable<Integer, ButtonBox>) tabinfo.getshapes().clone();
		Hashtable<Integer, HashSet<Integer>> edges = new Hashtable<Integer, HashSet<Integer>>();
		for(Connection connection:connects){
			if(!edges.containsKey(connection.getSourceButton())){
				edges.put(connection.getSourceButton(),new HashSet<Integer>());
			}
			edges.get(connection.getSourceButton()).add(connection.getDestButton());
		}
		ArrayList<Integer> temp = findNodeTypeOf("(",nodes);
		String code = "";
		if(temp.size()>0){
			int start = temp.get(0);
			code = generateCode(start,nodes,edges);
		}
		else{
			System.out.print("No start symbol!!!");
		}
		return code;
	}
	
	private ArrayList<Integer> findNodeTypeOf(String symbol, Hashtable<Integer, ButtonBox> table){
		ArrayList<Integer> nodes = new ArrayList<Integer>();
		for(int key:table.keySet()){
			if(table.get(key).getSymbol().equals(symbol))
				nodes.add(key);
		}
		return nodes;
	}
	
	private String generateCode(int nodeID, Hashtable<Integer, ButtonBox> nodes,Hashtable<Integer, HashSet<Integer>> edges){
		String code = "";
		ButtonBox node = nodes.get(nodeID);
		HashSet<Integer> edge = edges.get(nodeID);
		if(edge==null && !node.getSymbol().equals(")"))return code;
		switch(node.getSymbol()){
		case "-":
		case "||":
		case "<":
		case ">":
			for(int id:edge){
				ButtonBox next = nodes.get(id);
				code+=line(node,next);
			}
			for(int id:(HashSet<Integer>)edge.clone()){
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
			for(int id:(HashSet<Integer>)edge.clone()){
				if(findNodeOnPath(id,nodeID,nodes,(Hashtable<Integer, HashSet<Integer>>)edges.clone())){
					edge.remove(id);
					Hashtable<Integer, HashSet<Integer>> newedges = (Hashtable<Integer, HashSet<Integer>>) edges.clone();
					newedges.remove(nodeID);
					loop += indent(generateCode(id,nodes,newedges));
				}
				else{
					ex += line(node,nodes.get(id));
				}
			}
			code+="{\n"+loop;
			code+="}\n";
			for(int id:(HashSet<Integer>)edge.clone()){
				ButtonBox next = nodes.get(id);
				code+=line(node,next);
				edge.remove(id);
				code+=generateCode(id,nodes,edges);
			}
			break;
		case "#":
			code += "subgraph "+node.getTitle()+"\n";
			String sub = "";
			if(Model.getTabs().containsKey(node.getTitle())){
				sub = processNodes(Model.getTabs().get(node.getTitle()));
				code += sub;
			}
			else{
				System.out.println("Sub graph " + node.getTitle()+"not exist");
			}
			for(int id:(HashSet<Integer>)edge.clone()){
				ButtonBox next = nodes.get(id);
				edge.remove(id);
				code+=generateCode(id,nodes,edges);
			}
			break;
		case "(":
			for(int id:edge){
				ButtonBox next = nodes.get(id);
				code+="start -> "+nodes.get(id).getTitle() + ";\n";
				code+=generateCode(id,nodes,edges);
				code=indent(code);
				code+="}\n";
				code="{\n"+code;
			}
			break;
		case ")":
			break;
		}
		return code;
	}
	
	private boolean findNodeOnPath(int start, int end, Hashtable<Integer, ButtonBox> nodes, Hashtable<Integer, HashSet<Integer>> edges){
		boolean found = false;
		HashSet<Integer> edge = edges.get(start);
		if(edge==null) return false;
		edge = (HashSet<Integer>) edge.clone();
		if(edge.contains(end)){
			found = true;
		}
		else
			for(int node:(HashSet<Integer>)edge.clone()){
				edge.remove(node);
				found = findNodeOnPath(node,end,nodes,edges);
				if(found)break;
			}
		return found;
	}
	
	private void printConnections(Hashtable<Integer, HashSet<Integer>> edges){
		System.out.println("Connections:");
		for(int id:edges.keySet()){
			String s, d;
			s=Model.getTabs().get("Tab").getshapes().get(id).getSymbol();
			for(int dest:edges.get(id)){
				d=Model.getTabs().get("Tab").getshapes().get(dest).getSymbol();
				System.out.println(s+" -> "+d);
			}
		}
	}
	
	private String indent(String input){
		return input.replaceAll("(?m)^","\t");
	}
	
	private String line(ButtonBox a, ButtonBox b){
		String left, right;
		left = a.getTitle();
		right = b.getTitle();
		if(b.getSymbol().equals(")"))
			right = "end";
		String code = left + " -> " + right + ";\n";
		return code;
	}
}
