class Problem1590 {
    public int minSubarray(int[] nums, int p) {
        int n = nums.length;
        int target = 0;
        for (int num : nums) {
            target += num;
            target %= p;
        }
        if (target == 0) {
            return 0;
        }

        int answer = n;
        int prefixSum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i=0; i<n; i++) {
            prefixSum += nums[i];
            prefixSum %= p;
            int key = (prefixSum - target + p)%p;
            
            if(map.containsKey(key)) {
                answer = Math.min(answer, i - map.get(key));
            }
            map.put(prefixSum, i);
        }

        return answer < n ? answer : -1;
    }
}