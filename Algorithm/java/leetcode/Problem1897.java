public class Problem1897 {
    public boolean makeEqual(String[] words) {
        int[] wordCnt = new int[26];
        for (String word : words) {
            for (int i=0; i<word.length(); i++) {
                wordCnt[word.charAt(i) - 'a']++;
            }
        }
        for (int i=0; i<26; i++) {
            if (wordCnt[i] > 0 & wordCnt[i]%words.length != 0) return false;
        }
        return true;
    }
}