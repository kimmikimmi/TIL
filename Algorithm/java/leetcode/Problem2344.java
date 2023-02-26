public class Problem2344 {
        public int minOperations(int[] nums, int[] numsDivide) {
            
            int gcd = numsDivide[0];
            for (int i=1; i<numsDivide.length; i++) {
                gcd = gcd(gcd, numsDivide[i]);
                // if (gcd == 1 && numsDivide[i] != 1) return -1;
            }
    
            int min = Integer.MAX_VALUE;
            for (int n : nums) {
                if (gcd % n == 0) {
                    min = Math.min(min, n);
                }
            }
            if (min == Integer.MAX_VALUE) return -1;
    
            int cnt = 0;
            for (int n : nums) {
                if (n < min) cnt++;
            }
            return cnt;
        }
    
    
        private int gcd (int x, int y) {
            if (x < y) {
                int temp = x;
                x = y;
                y = temp;
            }
            int q = x / y;
            int r = x % y;
    
            if (r == 0) return y;
    
            return gcd(y, r);
        }
    }