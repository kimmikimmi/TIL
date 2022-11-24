public class SelectionSort {

    // Ascending 
    static void sort(int[] arr) {
       final int n = arr.length;
       
       for (int i=n-1; i>=0; i--) {
            // 1st, find the largest, 
            int k = findLargest(arr, 0, i);

            // 2nd, swap with ith 
            int tmp = arr[k];
            arr[k] = arr[i];
            arr[i] = tmp;
       } 

    }

    private static int findLargest(int[] arr, int start, int end) {
        int max = start;
        for (int i=start; i<end; i++) {
            if (arr[i] > arr[max]) {
                max = i;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{5,4,3,1,2};
        SelectionSort.sort(arr);
        for (int e : arr) {
            System.out.println(e);
        }
    }
    
}
