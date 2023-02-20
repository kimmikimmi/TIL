public class Problem456 {
     public boolean find132pattern(int[] nums) {
          if (nums.length < 3 ) return false;
  
          int[] mins = new int[nums.length];
          mins[0] = nums[0];
          for (int i=1; i<nums.length; i++) {
              mins[i] = Math.min(mins[i-1], nums[i]);
          }
  
          Stack<Integer> stack = new Stack<>();
          for (int i=nums.length-1; i>=0; i--) {
              if (nums[i] == mins[i]) continue;
  
              while(!stack.isEmpty() && stack.peek() <= mins[i]) stack.pop();
              
              if (!stack.isEmpty() && stack.peek() < nums[i]) return true;
              stack.push(nums[i]);
          }
  
          return false;
      }
  }