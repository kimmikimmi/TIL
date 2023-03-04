public class Problem221 {
    
    public int maximalSquare(char[][] matrix) {
        int[][] answer = new int[matrix.length][matrix[0].length];
        int max = 0;

        for (int i=0; i<matrix.length; i++) {
            for (int j=0; j<matrix[0].length; j++) {
                if (i == 0 || j == 0) {
                    answer[i][j] = matrix[i][j] - '0';
                 } else if (matrix[i][j] == '0') 
                    answer[i][j] = 0;
                else {
                    int min = answer[i-1][j-1];
                    min = Math.min(min, answer[i-1][j]);
                    min = Math.min(min, answer[i][j-1]);
                    answer[i][j] = min + 1;
                }

                max = Math.max(max, answer[i][j] * answer[i][j]);
            }
        }

        return max;
    }
}