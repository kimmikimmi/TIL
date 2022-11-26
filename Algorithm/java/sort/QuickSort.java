import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class QuickSort<K> {

    public static <K> void sortInPlace(K[] S, Comparator<K> comp, int a, int b) {
        if (a >= b) {
            return;
        }
        int left = a;
        int right = b - 1;
        K pivot = S[b];
        K temp;

        while (left <= right) {
            while (left <= right && comp.compare(S[left], pivot) < 0) {
                left++;
            }
            while (left <= right && comp.compare(S[right], pivot) > 0) {
                right--;
            }

            if (left <= right) {
                temp = S[left];
                S[left] = S[right];
                S[right] = temp;

                left++; right--;
            }

            temp = S[left]; 
            S[left] = S[b];
            S[b] = temp;

            sortInPlace(S, comp, a, left - 1);
            sortInPlace(S, comp, left + 1, b);
        }
    }

    public static <K> void sort(Queue<K> S, Comparator<K> comp) {
       int n = S.size();
       if (n <2) return;
       
       // Divide
       K pivot = S.element();
       Queue<K> L = new LinkedList<>();
       Queue<K> E = new LinkedList<>();
       Queue<K> R = new LinkedList<>();

       while (!S.isEmpty()) {
           K elem = S.poll();
           int c = comp.compare(elem, pivot);
           if (c < 0) {
               L.add(elem);
           } else if (c == 0) {
               E.add(elem);
           } else {
               R.add(elem);
           }
       }

       // Conquer
       sort(L, comp);
       sort(R, comp);

       while (!L.isEmpty()) {
            S.add(L.poll());
       }

       while (!E.isEmpty()) {
            S.add(E.poll());
       }

       while (!R.isEmpty()) {
            S.add(R.poll());
       }
    }
    
    // [l, r] 을 정렬한다.
    public void quickSort2(int[] arr, int l , int r) {
        if (l < r) {
            int q = partition(arr, l , r);
            quickSort2(arr, l, q-1); 
            quickSort2(arr, q+1, r); 
        }
    }

    public int partition(int[] arr, int l , int r) {
        int x = arr[r];
        int i = l-1;
        for (int j=l; j<r; j++) {
            if (arr[j] < x) {
                i++;
                int tmp = arr[j];
                arr[j] = arr[i];
                arr[i] = tmp; 
            }
        }

        int tmp = arr[r];
        arr[r] = arr[i+1];
        arr[i+1] = tmp;
        
        return i+1;
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(5);
        queue.add(3);
        queue.add(4);
        queue.add(2);
        queue.add(1);
        queue.add(2);
        QuickSort.sort(queue, Comparator.naturalOrder());
        for (Integer i : queue) System.out.println(i);
    }
}
