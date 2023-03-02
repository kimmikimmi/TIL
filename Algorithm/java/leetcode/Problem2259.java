public class Problem2259 {
    public String removeDigit(String number, char digit) {
        int n = number.length();
        int index = -1;

        for (int i=0; i<n; i++) {
            if (number.charAt(i) != digit) continue;
            index = i;
            if (i != number.length()-1 && number.charAt(i) < number.charAt(i+1)) {
                break;
            }   
        }
        return number.substring(0, index) + number.substring(index+1, number.length());
    }
}