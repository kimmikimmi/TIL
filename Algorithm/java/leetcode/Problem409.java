public class Problem409 {
        public int longestPalindrome(String s) {
            int maxOdd = 0;
            int evenSum = 0;
    
            Map<Character, Integer> cnts = new HashMap<>();
    
            for (char c : s.toCharArray()) {
                int cnt = cnts.getOrDefault(c, 0) + 1;
                cnts.put(c, cnt);
            }
            for (Integer v : cnts.values()) {
                evenSum += v / 2 * 2;
                if (v%2 == 1) {
                    maxOdd = 1;
                }
            }
            
            return maxOdd + evenSum;
        }
    }