public class Problem977 {
    public int[] sortedSquares(int[] nums) {
        int [] result = new int[nums.length];
        int i = 0;
        int j = nums.length - 1;
        int ptr = nums.length-1;
        while (i <= j) {
            if (nums[i] * nums[i] >= nums[j] * nums[j]) {
                result[ptr] = nums[i] * nums[i];
                i++;
            } else {
                result[ptr] = nums[j] * nums[j]; 
                j--;
            }
            ptr--;

        }
        return result; 
    }
}