public class Problem408 {
        public boolean validWordAbbreviation(String word, String abbr) {
            int m = word.length();
            int n = abbr.length();
    
            int i=0;
            int j=0;
            while (i < m && j < n) {
                int jump = 1;
                if (isNumeric(abbr.charAt(j))) {
                    if (abbr.charAt(j) == '0') return false;
                    StringBuilder sb = new StringBuilder();
                    while (j<n && isNumeric(abbr.charAt(j))) {
                        sb.append(abbr.charAt(j));
                        j++;
                    }
                    jump = Integer.parseInt(sb.toString()); 
                } else { 
                    if (word.charAt(i) != abbr.charAt(j)) {
                        return false;
                    }
                    j++;
                }
                i += jump;
            }
            return i == m && n == j;
        }
    
        private boolean isNumeric(char c) {
            return c >= '0' && c <= '9';
        }
    }
}
