public class Problem220 {
    
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        TreeSet<Integer> set = new TreeSet<>();

        for (int i=0; i<nums.length; i++) {

            Integer s = set.ceiling(nums[i]);
            if (s != null && s - nums[i] <= valueDiff) return true;

            s = set.floor(nums[i]);
            if (s != null &&  nums[i] - s <= valueDiff) return true;

            set.add(nums[i]);
            if (set.size() > indexDiff)
                set.remove(nums[i-indexDiff]);
        }
        return false;
    }
}