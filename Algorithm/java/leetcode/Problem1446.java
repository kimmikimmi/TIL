class Problem1446 {
    public int maxPower(String s) {
       int max = 1;
       char representative = s.charAt(0);
       int cur = max;
       for (int i=1; i<s.length(); i++) {
         if (s.charAt(i) == representative) {
           cur++;
         } else {
           cur = 1;
           representative = s.charAt(i);
         }

         if (cur > max) {
           max = cur;
         }
      } 
      return max;
    }
}