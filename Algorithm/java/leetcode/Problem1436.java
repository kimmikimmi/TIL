class Problem1436 {

    Map<String, List<String>> adjList;

    public String destCity(List<List<String>> paths) {
       // It does not have cycle.
      //  problem garantees it has one destination.
       // let's finish with DFS 
       adjList = new HashMap<>();
       for (List<String> path : paths) {
         adjList.putIfAbsent(path.get(0), new ArrayList<>());
         adjList.get(path.get(0)).add(path.get(1));
       }

       String start = paths.get(0).get(0);
       return findDestination(start);
    }

    private String findDestination(final String curr) {
      if (adjList.containsKey(curr)) {
        return findDestination(adjList.get(curr).get(0));
      }
      return curr;
    }

}