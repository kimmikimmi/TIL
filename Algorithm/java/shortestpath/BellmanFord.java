public class BellmanFord {
    
    private final class Edge {
        int source;
        int dest; 
        int weight;
    }

    private final int V;
    private final List<Edge> edges;
    private final int[] d;

    public BellmanFord(int numOfVertices, List<Edge> edges) {
        this.V = numOfVertices;
        this.edges = edges;
        this.d = new int[V];
    }

    public void execute(int start) {
       for (int i=0; i<V; i++) {
           d[i] = Integer.MAX_VALUE;
       } 
       d[start] = 0;
       for (int i=0; i<V-1; i++) {
           for (edge edge : edges) {
               if (d[edge.dest] > d[edge.source] + edge.weight) {
               d[edge.dest] = d[edge.source] + edge.weight;
               }
           }
       }

       for (Edge edge : edges) {
           if (d[edge.dest] > d[edge.source] + edge.weight) {
               throw new IllegalStateException("음의 사이클 존재!");
           }
       }
    }
}
