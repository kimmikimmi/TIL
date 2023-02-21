public class Problem405 {
    
        public String toHex(int num) {
            if (num == 0) return "0";
            String numNStr = ""; 
            int n = 16;
            long val = num;
            if (num < 0)
                val = (long)Math.pow(2, 32) + num; 
            while(val > 0){
                numNStr = String.valueOf(toHexx(val % n)) + numNStr;
                val /= n;
            }
            return numNStr;
        }
    
        private char toHexx(long a) {
            if (a < 10) {
                return (char)(a + '0');
            }
            else if (a == 10) {
                return 'a';
            } else if (a == 11) {
                return 'b';
            } else if (a == 12) {
                return 'c';
            } else if (a == 13) {
                return 'd';
            } else if (a == 14) {
                return 'e';
            } else {
                return 'f';
            }
        }
    }