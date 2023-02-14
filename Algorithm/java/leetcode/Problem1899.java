public class Problem1899 {
    public boolean mergeTriplets(int[][] triplets, int[] target) {
        boolean first = false;
        boolean second = false;
        boolean third = false;
        
        for (int i=0; i<triplets.length; i++) {
            if (triplets[i][0] == target[0] && triplets[i][1] <= target[1] && triplets[i][2] <= target[2]) {
                first = true;
            }
            if (triplets[i][1] == target[1] && triplets[i][0] <= target[0] && triplets[i][2] <= target[2]) {
                second = true;
            }
            if (triplets[i][2] == target[2] && triplets[i][0] <= target[0] && triplets[i][1] <= target[1]) {
                third = true;
            }

            if (first && second && third) {
                return true;
            }
        }
        
        return false;
    }
}