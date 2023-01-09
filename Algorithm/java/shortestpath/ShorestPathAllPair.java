public class ShorestPathAllPair {

    static class Pair {
        int dest;
        int weight;
    }
    private static int V;
    private int[][] adjMat = new int[V][V];

    public void execute() {
        int[][][] dist = new int[V-1][V][V];

        for (int i=0; i<V; i++) {
            for (int j=0; j<V; j++) {
                if (adjMat[i][j] == Integer.MAX_VALUE) {
                    dist[0][i][j] = Integer.MAX_VALUE; 
                } else {
                    dist[0][i][j] = adjMat[i][j];
                }
            }
        }

        for (int m=1; m<V; m++) {
            for (int i=0; i<V; i++) {
                for (int j=0; j<V; j++) {
                    dist[m][i][j] = Math.min(dist[m-1][i][j], dist[m-1][i][m] + dist[m-1][m][j]);
                }
            }
        } 
    }
    
}
