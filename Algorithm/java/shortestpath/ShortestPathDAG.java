import java.util.Arrays;

public class ShortestPathDAG {
    static class Pair {
        int dest;
        int weight;
    }

    private  static int V;
    private Map<Integer, List<Pair>> adjList;

    public void execute(int start) {
        int[] d = new int[V];
        Arrays.fill(d, Integer.MAX_VALUE);
       d[start] = 0;
       
       List<Integer> sorted = topologicalSort();
        for (Integer v : sorted) {
            if (adjList.containsKey(v)) {
                for (Pair nei : adjList.get(v)) {
                    if (d[nei.dest] > d[v] + nei.weight) {
                        d[nei.dest] = d[v] + nei.weight; 
                    }
                }
            }
        }
    }

    private List<Integer> topologicalSort() {
        List<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[V];
        for (Integer v : adjList.keySet()) {
            dfs(result, v, visited);
        }
        return result;
    }

    private void dfs(List<Integer> result, int v, boolean[] visited) {
        if (visited[v]) {
            return;
        }
        visited[v] = true;
        for (Pair nei : adjList.get(v)) {
            dfs(result, nei.dest);
        }
        result.add(v);
    }
}