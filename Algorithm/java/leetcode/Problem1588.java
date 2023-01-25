class Problem1588 {
    public int sumOddLengthSubarrays(int[] arr) {
       int sum = 0;
       int cnt = 0;
       for (int i=0; i<arr.length; i++) {
           cnt = 0;
           for (int j=0; j<=i; j++) {
             int k = arr.length - j;
             if (k % 2 == 1) cnt++;
             cnt += k / 2;
             cnt -= (j+1) / 2;
           }
           sum += arr[i] * cnt;
       } 
       return sum;
    }
}