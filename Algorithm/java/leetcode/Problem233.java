public class Problem233 {
        public int countDigitOne(int n) {
            if (n == 0) return 0;
    
            int digit = getDigit(n);
            int[] pre = new int[digit];
            int[] post = new int[digit];
    
            for (int i=0; i<digit; i++) {
                pre[i] = n / (int)Math.pow(10, i+1); 
            }
            for (int i=digit-1; i>=0; i--) {
                post[i] = n % (int)Math.pow(10, i);
            }
    
            int sum = 0;
            for (int i=0; i<digit; i++) {
                int r = n % 10;
                sum += pre[i] * (int)Math.pow(10, i);
                if (r > 1) 
                    sum += (int)Math.pow(10, i);
                else if (r > 0) 
                    sum += post[i] + 1;
                n /= 10;
            }
    
            return sum;
        }
    
        private int getDigit(int n) {
            int digit = 0;
            while (n > 0) {
                digit++;
                n /= 10;
            }
            return digit;
        }
        
    }