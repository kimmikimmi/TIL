class Problem1423 {
    public int maxScore(int[] cardPoints, int k) {
       int max = 0;
       int n = cardPoints.length;
       for (int i=0; i<k; i++) {
           max += cardPoints[i];
       } 

        int sum = max;
        for (int i=1; i<=k; i++) {
            sum -= cardPoints[k-i];
            sum += cardPoints[n-i];
            if (sum > max) {
                max = sum;
            }
        }
        return max;
    }
}