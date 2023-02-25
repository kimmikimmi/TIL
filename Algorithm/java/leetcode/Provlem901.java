public class Provlem901 {
            Stack<Pair<Integer, Integer>> stack;
            public StockSpanner() {
                stack = new Stack<>();
            }
            
            public int next(int price) {
                int c = 1;
                
                while (!stack.isEmpty() && stack.peek().getKey() <= price) {
                    c += stack.pop().getValue();
                }
                stack.push(new Pair(price, c));
        
                return c;
            }
        }
        
        /**
         * Your StockSpanner object will be instantiated and called as such:
         * StockSpanner obj = new StockSpanner();
         * int param_1 = obj.next(price);
         */