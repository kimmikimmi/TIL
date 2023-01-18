public class Problem1437 {
    public boolean kLengthApart(int[] nums, int k) {
        int cnt = 0;
        boolean start = false;

        for (int n : nums) {
            
            if (n == 1) {
                if (cnt < k && start) {
                   return false;
                } else {
                 cnt = 0;
                }
            
                start = true;
            
            } else {
                cnt++;
            }
        
        }
        return true;
    }
}
