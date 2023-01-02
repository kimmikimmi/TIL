import java.util.HashMap;
import java.util.PriorityQueue;

class Prim {
    private class Entry {
        int w;
        int v; 

        Entry(int w, int v) {
            this.w = w;
            this.v = v;
        }
    }
    private Map<List<Entry>> adjList = new HashMap<>();

    private void prim(int s) {
        int n = adjList.size();

        int[] d = new int[n];
        for (int i=0; i<n; i++) {
            d[i] = Integer.MAX_VALUE;
        }
        d[s] = 0;
        PriorityQueue<Entry> pq = new PriorityQueue<>();
        pq.add(new Entry(d[s], s));

        while (n>0) {
            Entry entry = pq.poll();

            if (!adjList.containsKey(entry.v)) {
                continue;
            }
            for (Entry nei : adjList.get(entry.v)) {
                if (nei.w < d[nei.v]) {
                    d[nei.v] = nei.w;
                    pq.add(new Entry(d[nei.v], nei.w));
                }
            }
            n--;
        }

    }


}
