class Problem2351 {
    public char repeatedCharacter(String s) {
        boolean[] charMap = new boolean[26];

        for (char c : s.toCharArray()) {
            if (charMap[c - 'a']) return c;
            charMap[c-'a'] = true;
        }

        throw new IllegalArgumentException("Failed to find first letter to appear twice.");
    }
}