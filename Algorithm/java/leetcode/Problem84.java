public class Problem84 {
        public int largestRectangleArea(int[] heights) {
            int max = 0;
            Stack<Integer> s = new Stack<>();
    
            for (int i=0; i<heights.length; i++) {
                while (!s.isEmpty() && heights[s.peek()] >= heights[i]) {
                    int x = s.pop();
                    int w = s.isEmpty() ? i : i - s.peek() - 1;
                    max = Math.max(max, heights[x] * w);
                }
    
                s.push(i);
            }
            int len = heights.length;
            while (!s.isEmpty()) {
                int x = s.pop();
                int w = s.isEmpty() ? len : len - s.peek() - 1;
                max = Math.max(max, heights[x] * w);
            }
    
            return max;
        }
    }    
}
