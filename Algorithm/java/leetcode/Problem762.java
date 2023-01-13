public class Problem762 {
    class Solution {
        public int countPrimeSetBits(int left, int right) {
            int sum = 0;
            for (int i=left; i<=right; i++) {
              int cnt = calculateOneBits(i);
              if (isPrime(cnt)) {
                sum++;
              }
            }
            return sum;
        }
    
        private int calculateOneBits(int n) {
          int cnt = 0;
          int dec = n;
          while (dec > 0) {
            if (dec % 2 == 1) {
              cnt++;
            }
            dec /= 2;
          }
    
          return cnt;
        }
    
        // O(10^3)
        private boolean isPrime(final int n) {
          if (n == 1) {
            return false;
          }
          
          int sqrt = (int) Math.sqrt(n);
          for (int i=2; i<=sqrt; i++) {
            if (n % i == 0) {
              return false;
            }
          } 
          return true;
        }
    }
}
