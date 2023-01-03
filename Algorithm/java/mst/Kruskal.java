import java.util.Collections;
import java.util.Comparator;

public class Kruskal {
    private class Edge {
        final int src;
        final int dest; 
        final int weight;

        Edge (final int src, final int dest, final int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        int getWeight() {
            return weight;
        }
    }

    private List<Edge> edges = new ArrayList<>();
    private List<Edge> mst = new ArrayList<>();
    private int V; // # of vertices;

    public void execute() {
        Collections.sort(edges, Comparator.comparing(Edge::getWeight));

        Set<Integer> vertices = new HashSet<>();
        for (Edge edge : edges) {
            if (vertices.contains(edge.src) && vertices.contains(edge.dest)) {
                continue;
            }
            if (vertices.contains(edge.src)) {
                vertices.add(edge.dest);
            } else {
                vertices.add(edge.src);
            }

            mst.add(edge);
            if (vertices.size() == V) {
                break;
            }
        }
    }

    public void execute2() {
        Collections.sort(edges, Comparator.comparing(Edge::getWeight));

        Union union = new Union(V);
        union.makeSet(V);
        int v = 0;
        for (Edge edge : edges) {
            if(union.isSameSet(edge.src, edge.dest)) {
                continue;
            }
            union.union(edge.src, edge.dest);
            v++;
            if (v == V) return;
        }
    }
}