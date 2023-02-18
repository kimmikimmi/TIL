public class Problem496 {
       public int[] nextGreaterElement(int[] nums1, int[] nums2) {
          int m = nums1.length;
          int n = nums2.length;
    
          Map<Integer, Integer> indices = new HashMap<>();
          for (int i=0; i<n; i++) {
            indices.put(nums2[i], i+1);
          }
          
          int[] ans = new int[m];
          Arrays.fill(ans, -1);
          for (int i=0; i<m; i++) {
            int x = indices.get(nums1[i]);
            while (x < n) {
              if (nums2[x] > nums1[i]) {
                ans[i] = nums2[x];
                break;
              }
              x++;
            }
          }
          return ans;
        }
}
