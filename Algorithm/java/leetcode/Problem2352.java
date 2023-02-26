public class Problem2352 {
        public int equalPairs(int[][] grid) {
            int n = grid.length;
            int cnt = 0;
            for (int i=0; i<n; i++) {
                for (int j=0; j<n; j++) {
                    boolean isMatched = true;
                    for (int k=0; k<n; k++) {
                        if (grid[i][k] != grid[k][j]) {
                            isMatched = false;
                            break;
                        }
                    }
                    if (isMatched) cnt++;
                }
            }
    
            return cnt;
        }
    } 
}
