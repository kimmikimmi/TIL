public class Problem1168 {
    
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {

      PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(x -> x[1]));

      Map<Integer, List<int[]>> adjList = new HashMap<>();
      for (int[] pipe : pipes) {
        adjList.putIfAbsent(pipe[0], new ArrayList<>());
        adjList.putIfAbsent(pipe[1], new ArrayList<>());
        adjList.get(pipe[0]).add(new int[]{pipe[1], pipe[2]});
        adjList.get(pipe[1]).add(new int[]{pipe[0], pipe[2]});
      }

      adjList.put(0, new ArrayList<>());
      for (int i=0; i<n; i++) {
          adjList.get(0).add(new int[]{i+1, wells[i]});
          adjList.putIfAbsent(i+1, new ArrayList<>());
          adjList.get(i+1).add(new int[]{0, wells[i]});
          q.add(new int[]{i+1, wells[i]});
      }


      Set<Integer> s = new HashSet<>();
      s.add(0);

      int cost = 0;
      while (s.size() < n + 1) {
        int[] v = q.poll();
        if (s.contains(v[0])) continue;

        s.add(v[0]);
        cost += v[1];
        if (!adjList.containsKey(v[0])) continue;
        for (int[] u : adjList.get(v[0])) {
            if (!s.contains(u[0])) {
                q.add(u);
            }
        } 
      }

      return cost;
    }
}