public class Problem1912 {
    public long maxAlternatingSum(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        long answer = 0L;
        int curr = 0;
        int n = nums.length;
        
        while (curr<n-1) {
            while (curr < n-1 && nums[curr] <= nums[curr+1]) {
                curr++;
            }
            answer += nums[curr];
            while (curr < n-1 && nums[curr] >= nums[curr+1]) {
                curr++;
            }
            if (curr < n-1) {
                answer -= nums[curr];
            }
        }

        return answer;
    }
}