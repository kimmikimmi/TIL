class Problem1447 {
    public List<String> simplifiedFractions(int n) {
      Set<String> result = new HashSet<>();
      // can be optimize gcd with dp. 
       for (int i=n; i>1; i--) {
         for (int j=1; j<i; j++) {
           int gcd = gcd(j, i);
           
           if (gcd != 1) {
             result.add(j/gcd + "/" + i/gcd);
           } else { 
            result.add(j +"/"+ i);
           } 
         }
       } 

       return result.stream().collect(Collectors.toList());
    }

    // a < b 
    private int gcd(int a, int b) {
      if (b % a == 0) return a;
      return gcd(b % a, a);
    }
}