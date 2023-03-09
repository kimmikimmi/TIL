public class Problem802 {
    
    public List<Integer> eventualSafeNodes(int[][] graph) {
      int[] outdegrees = new int[graph.length];
      Map<Integer, List<Integer>> indegrees = new HashMap<>();
      
      PriorityQueue<Integer> pq = new PriorityQueue<>();
      
      for (int i=0; i<graph.length; i++) {
        outdegrees[i] += graph[i].length;
        for (int j=0; j<graph[i].length; j++) {
            indegrees.putIfAbsent(graph[i][j], new ArrayList<>());
            indegrees.get(graph[i][j]).add(i);    
        }
        if (outdegrees[i] == 0) {
            pq.add(i);
        }
      }

      List<Integer> result = new ArrayList<>();
      while (!pq.isEmpty()) {
          Integer v = pq.poll();
          result.add(v);
          if (!indegrees.containsKey(v)) {
              continue;
          }
          for (int u : indegrees.get(v)) {
              outdegrees[u]--;
              if (outdegrees[u] == 0) {
                  pq.add(u);
              }
          }
      }
      result.sort(Comparator.naturalOrder());
      return result;
    }
}