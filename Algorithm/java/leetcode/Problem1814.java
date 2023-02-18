public class Problem1814 {
    private final int mod = 1000000007;
    
    public int countNicePairs(int[] nums) {
        HashMap<Integer, Integer> diffCnt = new HashMap<>();
        int sum = 0;
        for (int i=0; i<nums.length; i++) {
            int res = nums[i] - rev(nums[i]);
            
            Integer cnt = diffCnt.get(res);
            if (cnt == null) {
                cnt = 0;
            } else {
                sum = (sum + cnt) % mod;
            }

            diffCnt.put(res, (cnt+1)%mod);
        }

        return sum;
    }

    private int rev(final int n) {
        int ans = 0;
        int q = n;
        while (q > 0) {
            ans = ans * 10 + q%10;
            q /= 10;
        }

        return ans;
    }
}