class Solution {
    class Pair {
        int first;
        int second;
        
        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        int getFirst() {
            return first;
        }

        int getSecond() {
            return second;
        }
    }

    public int[][] reconstructQueue(int[][] people) {
        ArrayList<int[]> list = new ArrayList<>();

        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparing(Pair::getSecond).thenComparing(Pair::getFirst));
        
        for (int i=0; i<people.length; i++) {
            pq.add(new Pair(people[i][0], people[i][1]));
        }

        boolean isInserted = false;    
        while (!pq.isEmpty()) {
            Pair cur = pq.poll();
            int cnt = cur.second;
            for (int i=0; i<list.size(); i++) {
                if (cnt == 0 && list.get(i)[0] >= cur.first) {
                    list.add(i,new int[]{cur.first, cur.second});
                    isInserted = true;
                }
                if (list.get(i)[0] >= cur.first) {
                    cnt--;
                }
            }
            if (!isInserted) {
                list.add(new int[]{cur.first, cur.second});
            }
            isInserted = false;
        }

        int[][] result = new int[people.length][2];
        for (int i=0; i<list.size(); i++) {
            result[i][0] = list.get(i)[0];
            result[i][1] = list.get(i)[1];
        }

        return result;
    }
}