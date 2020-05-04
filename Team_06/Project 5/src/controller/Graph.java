package controller;

import java.util.*; 
/**
 * 
 * @author Rahul
 * @since 03-12-2020
 * @Description: This class is used to represent the connection between the different diagrams 
 * and to check whether the diagrams follow the predefined rules. 
 */
public class Graph { 
	private int V;
	private ArrayList<LinkedList<Integer>> adj;
	
	public Graph(Integer v){ 
		V = v; 
		adj = new ArrayList<LinkedList<Integer>>();
		for (int i=0; i<v; ++i) {
			adj.add(new LinkedList<Integer>()); 
		}			
	} 

	public void addEdge(int v,int w){ 
		adj.get(v).add(w); 
	} 
	
	public boolean checkConnection(int s){ 
		boolean visited[] = new boolean[V];
		LinkedList<Integer> queue = new LinkedList<Integer>();		 
		visited[s]=true; 
		queue.add(s); 

		while (queue.size() != 0) { 			
			s = queue.poll(); 			
			Iterator<Integer> i = adj.get(s).listIterator(); 
			while (i.hasNext()) 
			{ 
				int n = i.next(); 
				if (!visited[n]) 
				{ 
					visited[n] = true; 
					queue.add(n); 
				} 
			} 
		} 		
		
		for(boolean flag: visited) {
			if (!flag) {
				return false;
			}
		}
		
		return true;
	} 
	
	public boolean checkLoop(Integer s) {
		boolean visited[] = new boolean[V];
		LinkedList<Integer> queue = new LinkedList<Integer>();
		visited[s]=true; 
		queue.add(s); 

		while (queue.size() != 0) { 			
			Integer t = queue.poll(); 			
			Iterator<Integer> i = adj.get(t).listIterator(); 
			while (i.hasNext()) 
			{ 
				int n = i.next(); 
				if (!visited[n]) 
				{ 
					visited[n] = true; 
					queue.add(n); 
				} 
				if (n == s) {
					return true;
				}
			} 
		} 		
		
		return false;
	}
	
}

