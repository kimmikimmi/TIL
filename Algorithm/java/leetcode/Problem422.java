public class Problem422 {
    
    public boolean validWordSquare(List<String> words) {
        for (int i=0; i<words.size(); i++) {
            for (int j=0; j<words.get(i).length(); j++) {
                if (words.size() <= j) {
                    return false;
                }
                if (words.get(j).length() <= i) {
                    return false;
                }
                if (words.get(i).charAt(j) != words.get(j).charAt(i)) {
                    return false;
                }
            } 
        }
        return true;
    }
}