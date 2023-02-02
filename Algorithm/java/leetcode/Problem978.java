public class Problem978 {
    public int maxTurbulenceSize(int[] arr) {
        if (arr.length == 1) return 1;
        Status prevStatus = Status.EQ;
        int prev = arr[0];
        int cnt = 1;
        int max = cnt;

        for (int i=1; i<arr.length; i++) {
            Status curr = getCurrentStatus(prev, arr[i]);
            if (curr == Status.EQ) {
                cnt = 1;
            } else if (curr != prevStatus) {
                cnt++;
            } else {
                cnt = 2;
            }

            prev = arr[i];
            prevStatus = curr;
            max = Math.max(max, cnt);
        }
        return max;
    }

    private Status getCurrentStatus(int prevVal, int currVal) {
        if (prevVal == currVal) return Status.EQ;
        else if (prevVal > currVal) return Status.DOWN;
        else return Status.UP;
    }

    private enum Status {
        EQ,
        UP,
        DOWN;
    }
}