public class Problem2261 {
    
        public int countDistinct(int[] nums, int k, int p) {
            Set<String> set = new HashSet<>(); // max = 19900 개 (200 * 199) / 2;
            
            // fill set 
            for (int i=0; i<nums.length; i++) {
                String elem = "";
                for (int j=i; j<nums.length; j++) {
                    elem += nums[j] + "-";
                    set.add(elem);
                }
            }
    
            int ans = 0;
            for (String s : set) {
                String[] split = s.split("-");
                int cnt = 0;
                if (split.length > k) {
                    for (String num : split) {
                        if (Integer.parseInt(num) % p == 0) {
                            cnt++;
                        }
                        if (cnt > k) break;
                    }
                }
                if (cnt <= k) {
                    ans++;
                }
            }
    
            return ans;
        }
    }