import java.util.Arrays;
import java.util.Comparator;

public class MergeSort<T> {
    
    public static <T> void sort(T[] arr, Comparator<T> comp) {
        int n = arr.length;
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        T[] arr1 = Arrays.copyOfRange(arr, 0, mid);
        T[] arr2 = Arrays.copyOfRange(arr, mid, n);
    
        sort(arr1, comp);
        sort(arr2, comp);

        merge(arr1, arr2, arr, comp);
    }

    public static <T> void merge(T[] arr1, T[] arr2, T[] arr, Comparator<T> comp) {
       int i=0;
       int j=0;
       
       while (i+j < arr.length) {
           if (j == arr2.length || i < arr1.length && comp.compare(arr1[i], arr2[j]) < 0) {
               arr[i+j] = arr1[i++];
           } else {
               arr[i+j] = arr2[j++];
           }
       }
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{5,4,3,2,1};
        MergeSort.sort(arr, Comparator.naturalOrder());
        for (Integer i : arr) System.out.println(i);
    }
}
