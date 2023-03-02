public class Problem2260 {
    

    public int minimumCardPickup(int[] cards) {
        Map<Integer, Integer> map = new HashMap<>();

        int min = Integer.MAX_VALUE;
        for (int i=0; i<cards.length; i++) {
            Integer v = map.get(cards[i]);
            if (v != null && min > i - v + 1) {
                    min = Math.min(min, i - v + 1);
            } 
            map.put(cards[i], i);
            
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }
}