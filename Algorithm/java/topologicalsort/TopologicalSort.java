import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TopologicalSort {
    // Graph , adjacency list
    private final static int V;

    private Map<Integer, List<Integer>> adjList;
    // DFS way
   public List<Integer> topologicalSort() {
       List<Integer> result = new ArrayList<>();
       boolean[] visited = new boolean[V];
       Arrays.fill(visited, false);
       // pick a node
       for (Integer vertex : adjList.keySet()) {
           dfs(visited, result, start);   
       }
       
       return result;
   }

   private void dfs(boolean[] visited, List<Integer> result, int v) {
       if (visited[v]) {
           return;
       }
       visited[v] = true;
       for (Integer nei : adjList.get(v)) {
           dfs(visited, result, nei);
       }
       result.add(v);
   }
   
   // Indegree Way (My preference)

   public List<Integer> topologicalSort2() {
       List<Integer> result = new ArrayList<>();
       int[] indegrees = new int[V];
       // Fill indegrees
       for (List<Integer> dests : adjList.values()) {
           for (Integer dest : dests) {
               indegrees[dest]++;
           }
       }

       Map<Integer, List<Integer>> buffer;
       buffer.put(0, new ArrayList<>());
       buffer.put(1, new ArrayList<>());
       // 시작 버텍스 
       for (int i=0; i<V; i++) {
           if (indegrees[i] == 0) {
               buffer.get(0).add(i);
           }
       }

       int i=0;
       while (i < V) {
            if (buffer.get(i%2) == 0) {
                throw new IllegalStateException("graph has cycle.");
            }
            for (Integer v : buffer.get(i%2)) {
                result.add(v);
                if (adjList.containsKey(v)) {
                    for (Integer nei : adjList.values()) {
                        indegrees[nei]--;
                        if (indegrees[nei] == 0) {
                            buffer.get((i+1)%2).add(nei);
                        }       
                    }
                }
                buffer.get(i%2).clear();
            }
            i++;
        }
        return result;
   }
}
