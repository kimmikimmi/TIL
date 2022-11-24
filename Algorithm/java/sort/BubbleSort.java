public class BubbleSort {

    public static void sort(int[] arr) {
        int n = arr.length;

        for (int i=0; i<n-1; i++) {
            // With this flag, we can increase the performance.
            // Which means, if input array is sorted, then it finish sorting in linear time.
            boolean sorted = true;
            for (int j=0; j<n-1; j++) {

                if (arr[j] > arr[j+1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                    sorted = false;
                }
            }
            if (sorted) {
                return;
            }
        }
    }

   public static void main(String[] args) {
        int[] arr = new int[]{5,4,3,1,2};
        SelectionSort.sort(arr);
        for (int e : arr) {
            System.out.println(e);
        }
   } 
}
