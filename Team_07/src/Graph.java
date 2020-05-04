import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Different operations for the graph formed by the operators and their connections
 *
 * @author Praveen Kumar P
 * https://www.geeksforgeeks.org/detect-cycle-in-a-graph/
 * @since March 15, 2020
 */
class Graph {

    private final int TOTAL_VERTICES;
    private final List<List<Integer>> ADJACENT_VERTICES;

    public Graph(int TOTAL_VERTICES) {
        this.TOTAL_VERTICES = TOTAL_VERTICES;
        ADJACENT_VERTICES = new ArrayList<>(TOTAL_VERTICES);
        for (int i = 0; i < TOTAL_VERTICES; i++)
            ADJACENT_VERTICES.add(new LinkedList<>());
    }

    private boolean isCyclicUtil(int i, boolean[] visited,
                                 boolean[] stack) {
        if (stack[i])
            return true;

        if (visited[i])
            return false;

        visited[i] = true;
        stack[i] = true;
        List<Integer> children = ADJACENT_VERTICES.get(i);
        for (Integer c : children)
            if (isCyclicUtil(c, visited, stack))
                return true;
        stack[i] = false;
        return false;
    }

    public void addEdge(int source, int dest) {
        ADJACENT_VERTICES.get(source).add(dest);
    }

    /**
     * @return True if the graph is cyclic
     */
    public boolean isCyclic() {
        boolean[] visited = new boolean[TOTAL_VERTICES];
        boolean[] recStack = new boolean[TOTAL_VERTICES];
        for (int i = 0; i < TOTAL_VERTICES; i++)
            if (isCyclicUtil(i, visited, recStack))
                return true;
        return false;
    }
    
    public int countConnectedComponents() {
        boolean[] visited = new boolean[TOTAL_VERTICES];
        int count = 0;
        int index;
        while((index = checkGraphIsVisited(visited))!=-1){
            dfs(index, visited);
            count++;
        }
        return count;
    }

    public int checkGraphIsVisited(boolean[] visited){

        for (int i = 0; i <visited.length ; i++) {
            if(!visited[i])
                return i;
        }
        return -1;
    }

    public void dfs(int start, boolean[] visited) {
        visited[start] = true;
        for (int i = 0; i < ADJACENT_VERTICES.get(start).size(); i++) {
            int destination = ADJACENT_VERTICES.get(start).get(i);
            if (!visited[destination])
                dfs(destination, visited);
        }
    }
}


