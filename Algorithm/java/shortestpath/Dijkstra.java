import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Dijkstra {
    private final Map<Integer, List<Pair>> adjacencyList;
    
    private final int V;
    private int[] dist;

    private class Pair {
        int dist;
        int v;

        Pair(int dist, int v) {
            this.dist = dist;
            this.v = v;
        }

        int getDist() {
            return this.dist;
        }
    }

    puiblic Dijkstra(final int numOfVertices, final Map<Intger, List<Integer>> adjacencyList) {
        this.V = numOfVertices;
        this.adjacencyList = adjacencyList;
        dist = new int[V];
    }

    public void execute(int start) {
       for (int i=0; i<V; i++) {
            dist[i] = Integer.MAX_VALUE;
       } 
       dist[start] = 0;

       PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparing(Pair::getDist));
       pq.add(new Pair(dist[start], start));

       boolean[] visited = new boolean[V];
       int count = 0;
       while (!pq.isEmpty() && count < V) { // this loop + inner for loop -> E
           Pair u = pq.poll(); // logV
           visited[u.v] = true;

           if (!adjacencyList.containsKey(u.v)) {
               continue; // this node is an island.
           }

           for (Pair nei : adjacencyList.get(u.v)) {
               if (!visited[u] && nei.weight + d[u.v] < d[nei]) {
                   d[nei] = nei.weight + d[u.v];
                   pq.add(new Pair(d[nei], nei));
               }
           }
           count++;
       }
    }

    public static void main(String[] args) {
        new Dijkstra(1, new HashMap<>());
    }
}
