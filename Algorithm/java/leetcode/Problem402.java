public class Problem402 {
        public String removeKdigits(String num, int k) {
            Stack<Character> stack = new Stack<>();
    
            stack.push(num.charAt(0));
            for (int i=1; i<num.length(); i++) {
                while(!stack.isEmpty() && k > 0 && stack.peek() - '0' > num.charAt(i) - '0') {
                    stack.pop();
                    k--;
                }
                stack.push(num.charAt(i));
            }
    
            while (k > 0) {
                stack.pop();
                k--;
            }
            String result = "";
            while (!stack.isEmpty()) {
                result = stack.pop() + result;
            }
    
            result = result.replaceFirst("^0*", "");
    
            if (result.equals("")) result = "0";
    
            return result;
        }
    } 
}
