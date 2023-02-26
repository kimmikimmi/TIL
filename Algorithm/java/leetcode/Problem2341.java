public class Problem2341 {
        public int[] numberOfPairs(int[] nums) {
            int[] cnts = new int[101];
    
            for (int num : nums) {
                cnts[num]++;
            }
            int[] ans = new int[2];
    
            for (int cnt : cnts) {
                ans[0] += cnt / 2;
                ans[1] += cnt % 2; 
            } 
            return ans;
        }
    }