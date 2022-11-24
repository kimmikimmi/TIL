import java.util.Arrays;

/**
 * 이미 정렬되어 있는 i 개짜리 배열에 하나의 원소를 삽입하여 정렬된 i+1 개의 배열을 만드는 과정을 반복한다.
 * 즉, arr[i]에 관심을 두는 시점에 arr[i:i-1]은 정렬이 되어있다. 
 * 고교시절에 배운 수학적 귀납법의 좋은 예시이다.
 * 
 * 또한 거의 정렬이 되어 있는 경우, Theta(n)에 가까운 시간이 든다.
 */
public class InsertionSort {
    
    public static void sort(int[] arr) {

        for (int i=1; i<arr.length; i++) {
            int loc = i-1;
            int newItem = arr[i];

            while (loc>=0 && newItem < arr[loc]) {
                arr[loc + 1] = arr[loc];
                loc--;
            }

            arr[loc+1] = newItem;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 31, 48, 73, 8, 11, 20, 29, 65, 15};
        InsertionSort.sort(arr);
        for (int e : arr) System.out.println(e);
    } 

}
