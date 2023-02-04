public class Problem981 {
    
    private static final String emptyValue = "";

    private static class Ent {
        String val;
        int ts;

        public Ent(final String val, final int ts) {
            this.val= val;
            this.ts = ts;
        }
    }
    private final Map<String, TreeMap<Integer, String>> map;

    public TimeMap() {
        this.map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        map.putIfAbsent(key, new TreeMap<>());
        map.get(key).put(timestamp, value);
    }
    
    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) {
            return emptyValue;
        }
        Map.Entry<Integer, String> entry = map.get(key).floorEntry(timestamp);
        if (entry == null) {
            return emptyValue;
        }
        return entry.getValue();
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */