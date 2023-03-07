public class Problem1135 {
        public int minimumCost(int n, int[][] connections) {
          Arrays.sort(connections, Comparator.comparingInt(x -> x[2]));
          
          rank = new int[n+1];
          parent = new int[n+1];
          for (int i=1; i<=n; i++) {
            parent[i] = i;
          }
          
          int cnt = 1;
          int sum = 0;
          for (int[] conn : connections) {
            if (!union(conn[0], conn[1])) {
                cnt++;
                sum += conn[2];
            }
            if (cnt == n) break; 
          }
          return cnt < n ? -1 : sum;
        }
    
        int[] rank;
        int[] parent;
        private int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
    
        private boolean union(int x, int y) {
            int xx = find(x);
            int yy = find(y);
    
            if (xx == yy) return true;
    
            if (rank[xx] > rank[yy]) {
                parent[yy] = xx;
            } else {
                parent[xx] = yy;
                if (rank[xx] == rank[yy]) {
                    rank[yy]++;
                }
            }
            return false;
        }
    }