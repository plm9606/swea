package algorithm;

import java.util.Arrays;

/**
 * 버블 정렬은 서로 옆에 있는 두 원소의 대소를 비교하고 조건에 맞지 않다면 자리를 교환하며 정렬하는 알고리즘
 * 1회전이 끝나면 가장 큰 원소가 맨 끝으로 이동하게 된다. 2회전에는 정렬된 맨 끝 원소를 제외하고 정렬한다.
 *
 * 시간복잡도 O(n^2)
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {2, 6,8,9,10,1,3};
        bubbleSort(arr);
    }

    public static void bubbleSort(int[] arr){
        for(int i=0; i<arr.length-1; i++){
            for(int j=0; j<arr.length-i-1; j++){
                if(arr[j]> arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            System.out.println(i+1 + "회전: " + Arrays.toString(arr));
        }

        System.out.println(Arrays.toString(arr));
    }
}
