public class Problem2239 {
    
    public int findClosestNumber(int[] nums) {
        int closest = Integer.MAX_VALUE;

        for (int num : nums) {
            if (Math.abs(closest) > Math.abs(num)) {
                closest = num;
            } else if (closest < 0 && closest == -num) {
                closest = num;
            }
        }

        return closest;
    }
}