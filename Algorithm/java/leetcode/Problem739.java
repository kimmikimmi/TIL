public class Problem739 {
        public int[] dailyTemperatures(int[] temperatures) {
            Stack<int[]> s = new Stack<>();
            int n = temperatures.length;
            int[] ans = new int[n];
    
            for (int i=n-1; i>=0; i--) {
                while (!s.isEmpty() && s.peek()[1] <= temperatures[i]) {
                    s.pop();
                }
                ans[i] = s.isEmpty() ? 0 : s.peek()[0] - i;
                
                s.add(new int[]{i, temperatures[i]});
            }
    
            return ans;
        }
    }