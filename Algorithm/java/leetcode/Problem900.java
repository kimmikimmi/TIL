public class Problem900 {
    private final int[] values;
    private final int[] times;
    private int ptr = 0;

    public RLEIterator(int[] encoding) {
        int n = 0;
        for (int i=0; i<encoding.length; i += 2) {
            if (encoding[i] != 0) n++;
        }

        values = new int[n];
        times = new int[n];
        int index = 0;

        for (int i=0; i<encoding.length; i += 2) {
            if (encoding[i] != 0) {
                times[index] = encoding[i];
                values[index] = encoding[i+1];
                index++;
            }
        }
    }
    
    public int next(int n) {
        
        while ( n > 0 && ptr < times.length) {
            int diff = times[ptr] - n;
            if (diff >= 0) {
                times[ptr] -= n;
                n = 0;
            } else {
                n -= times[ptr];
                times[ptr] = 0;
                ptr++;
            }
        }

        return ptr < times.length ? values[ptr] : -1;
    }
}

/**
 * Your RLEIterator object will be instantiated and called as such:
 * RLEIterator obj = new RLEIterator(encoding);
 * int param_1 = obj.next(n);
 */