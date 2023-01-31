public class Problem974 {
    public int subarraysDivByK(int[] nums, int k) {
       Map<Integer, Integer> map = new HashMap<>();
       int prefixSum = 0;
       int cnt = 0;
       for (int num : nums) {
           prefixSum += num;
           int target = (prefixSum%k + k) % k; 
           if (target == 0) {
               cnt++;
           }
           if (map.containsKey(target)) {
               cnt += map.get(target);
           }

           map.putIfAbsent(target, 0);
           map.put(target, map.get(target) +1);
       }

       return cnt; 
    }
}