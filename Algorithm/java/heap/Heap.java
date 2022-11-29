// Min heap.
public class Heap {

    // TODO(bright2013): TO BE MOVED
    public void sort(int[] arr, int n) {
        buildHeap(arr, n);
        for (int i=n-1; i>=1; i--) {
            int tmp = arr[0];
            arr[0] = arr[i];
            arr[i] = tmp;

            heapify(arr, 1, i-1);
        }
    }

    /**
     *  arr[1...n] 을 힙으로 만든다.
     *  Time Complexity = Theta(n)
     *  모든 heapify 의 수행시간을 logn 으로 잡은 것은 과하게 잡은 상한이다.
     */
    private void buildHeap(int[] arr, int n) {
        int half = n/2;

        for (int i=half; i>=0; i--) {
            heapify(arr, i, n); 
        }
    }

    /**
     * arr[k]를 루트로 하는 트리를 힙성질을 만족하도록 조정한다.
     * arr[k]의 두 자식을 루트로 하는 서브트리는 모두 힙의 성질을 만족한다.
     * @param arr
     * @param k 서브힙의 루트
     * @param n 최대 인덱스
     */
    private void heapify(int[] arr, int k, int n) {
       int left = 2*k + 1;
       int right = 2*k + 2;
       
        int smaller;
        if (right <= n) {
            if (arr[left] < arr[right]) {
                smaller = left; 
            } else {
                smaller = right;
            }
        } else if(left <= n) {
            smaller = left;
        } else {
            // case: k is the index for leaf node.
            return;
        }
        
        if (arr[smaller] < arr[k]) {
            int tmp = arr[smaller];
            arr[smaller] = arr[k];
            arr[k] = tmp;
            heapify(arr, smaller, n);
        }
    }
}
