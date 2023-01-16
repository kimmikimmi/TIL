class Problem1446 {
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
      Map<Integer, List<Integer>> map = new HashMap<>();
      int maxSum = 0;
      int size = 0;
      
      for (int i=0; i<nums.size();i++) {
        for (int j=0; j<nums.get(i).size(); j++) {
          map.putIfAbsent(i+j, new ArrayList<>());
          map.get(i+j).add(nums.get(i).get(j));
          if (i+j > maxSum) {
            maxSum = i+j;
          }
          size++;
        }
      }
      
      int[] sorted = new int[size];
      int index = 0;
      for (int i=0; i<=maxSum; i++) {
        int num = map.get(i).size()-1;
        for (int j=num; j>=0; j--) {
          sorted[index] = map.get(i).get(j);
          index++;
        }
      }
      return sorted;
    }
}