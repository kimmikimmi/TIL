public class Problem907 {
        public int sumSubarrayMins(int[] arr) {
            long sum = 0;
            int mod = 1000000007;
            Stack<Integer> stack = new Stack<>();
    
            for (int i=0; i<=arr.length; i++) {
                while (!stack.isEmpty() && (i == arr.length || 
                    arr[stack.peek()] >= arr[i])) {
    
                    int mid = stack.pop();
                    int left = stack.isEmpty() ? -1 : stack.peek();
                    int right = i;
    
                    long cnt = (mid - left) *( right - mid) % mod;
    
                    sum += (cnt * arr[mid]) % mod;
                    sum %= mod;
                }
                stack.push(i);
            }
    
            return (int)sum;
        }
    
    }