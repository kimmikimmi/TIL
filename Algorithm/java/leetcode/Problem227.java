public class Problem227 {
        public int calculate(String s) {
            // preprocessing
            Stack<String> stack = new Stack<>();
            int k = 0;
            while (k < s.length()) {
                if (s.charAt(k) == ' ') {
                    k++;
                    continue;
                }
                
                if (isOperator(s.charAt(k))) {
                    if (s.charAt(k) == '-') {
                        stack.push("a-");
                    } else if (s.charAt(k) == '*') {
                        stack.push("a*");
                    } else if (s.charAt(k) == '/'){ 
                        stack.push("a/");
                    }
                    k++;
                } else {
                    StringBuilder sb = new StringBuilder();
                    while (k < s.length() && !isOperator(s.charAt(k))) {
                        if (s.charAt(k) != ' ') 
                            sb.append(s.charAt(k));
                        k++;
                    }
                    Integer num = Integer.parseInt(sb.toString()); 
                    if (!stack.isEmpty() && stack.peek().startsWith("a") ) {
                        String operator = stack.pop();
                        if (operator.equals("a*")) {
                            num *= Integer.parseInt(stack.pop());
                        } else if (operator.equals("a/")){
                            num = Integer.parseInt(stack.pop()) / num;
                        } else {
                            num = -num;
                        }
                    }
                    stack.push(num.toString());              
                }
            }
    
            // main process
            int sum = 0;
            while (!stack.isEmpty()) {
                sum += Integer.parseInt(stack.pop());
            }
    
            return sum;
        }
    
    
        private boolean isOperator(char c) {
            return c == '+' || c == '-' || c == '*' || c == '/';
        }
    }