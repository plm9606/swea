package algorithm.sort;

import java.util.Arrays;

/**
 * 선택 정렬은 말 그대로 원소를 넣을 인덱스를 먼저 선택하고 그 자리에 맞는 값을 찾는 방법이다.
 * 해당 자리를 선택하고 그 자리에 오는 값을 찾는 것
 * ex. 선택한 인덱스가 0이라고 하면 배열을 탐색하면서 가장 작은 원소값을 찾고 0번째 원소와 swap한다
 *
 * 시간복잡도: O(n^2)
 * 공간복잡도: O(n)
 */
public class SelectionSort {
    public static void main(String[] args) {
        int[] arr = {4,9,10,30,1,5,7};
        selection(arr);
    }

    public static void selection(int[] arr){
        for(int i=0; i<arr.length-1; i++){ // index select
            int min = i;
            for(int j=i+1; j<arr.length; j++){
                if(arr[min] > arr[j]){
                    min = j;
                }
            }
            if(min != i){
                int temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
            }

            System.out.println(i+1 + "회전: " + Arrays.toString(arr));
        }

        System.out.println(Arrays.toString(arr));
    }
}
