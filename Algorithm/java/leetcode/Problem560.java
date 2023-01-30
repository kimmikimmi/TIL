class Problem560 {
    public int subarraySum(int[] nums, int k) {
       Map<Integer, Integer> map = new HashMap<>();
       int cnt = 0;
       int prefixSum = 0;
       for (int n : nums) {
           // preprocess
           prefixSum += n;

           if (prefixSum == k) {
               cnt++;
           }
           if (map.containsKey(prefixSum - k)){
             cnt += map.get(prefixSum - k);
           }

           map.putIfAbsent(prefixSum, 0);
           map.put(prefixSum, map.get(prefixSum) + 1);
       } 
       return cnt;
    }
}