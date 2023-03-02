public class Problem1422 {
        public int maxScore(String s) {
            int leftZeros = 0;
            int leftOnes = 0;
            int max = 0;
    
            int numberOfOnes = 0;
            for (int i=0; i<s.length(); i++) {
                if (s.charAt(i) == '1') {
                    numberOfOnes++;
                }            
            }
            
            for (int i=0; i<s.length()-1; i++) {
                if (s.charAt(i) == '0') {
                    leftZeros++;
                } else {
                    leftOnes++;
                }
    
                if (leftZeros + (numberOfOnes - leftOnes) > max) {
                    max = leftZeros + (numberOfOnes - leftOnes);
                    System.out.println("i= " +i);
                    System.out.println(max);
                }
            }
    
            return max;
        }
}
