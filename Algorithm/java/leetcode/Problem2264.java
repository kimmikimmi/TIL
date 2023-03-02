public class Problem2264 {
    public String largestGoodInteger(String num) {
        int maxDigit = -1;
        for (int i=0; i<num.length()-2; i++) {
            if (num.charAt(i) == num.charAt(i+1) && num.charAt(i) == num.charAt(i+2)) {
                if (num.charAt(i) - '0' > maxDigit) {
                    maxDigit = num.charAt(i) - '0';
                }
                i += 2;
            } 
        }
        StringBuilder sb = new StringBuilder();
        if (maxDigit > -1) {
            sb.append(maxDigit);
            sb.append(maxDigit);
            sb.append(maxDigit);
        }
        return sb.toString();
    }
}