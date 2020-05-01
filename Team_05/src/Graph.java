/**
 * @author Kairui Hsu
**/
public class Graph {
    int V, E; 
    Edge edge[];
  
    public class Edge { 
        int src, dest; 
    }; 
  
    Graph(int v,int e) { 
        V = v; 
        E = e; 
        edge = new Edge[E]; 
        for (int i=0; i<e; ++i) 
            edge[i] = new Edge(); 
    } 
  
    public int find(int parent[], int i) { 
        if (parent[i] == -1) 
            return i; 
        return find(parent, parent[i]); 
    } 
  
    public void Union(int parent[], int x, int y) { 
        int xset = find(parent, x); 
        int yset = find(parent, y); 
        parent[xset] = yset; 
    } 
    
    public int isCycle( Graph graph) { 
        int parent[] = new int[graph.V]; 
        for (int i=0; i<graph.V; ++i) 
            parent[i]=-1; 
        for (int i = 0; i < graph.E; ++i) { 
            int x = graph.find(parent, graph.edge[i].src); 
            int y = graph.find(parent, graph.edge[i].dest); 
            if (x == y) 
                return 1; 
            graph.Union(parent, x, y); 
        } 
        return 0; 
    } 
}
