package algorithm;

import java.util.Arrays;

/**
 * 삽입정렬은 2번째 원소부터 시작해 그 앞으 원소들과 비교해 해당 원소가 들어갈 적절한 위치에 삽입을 한다.
 * 삽입정렬은 선택정렬과 유사하지만, 더 효율적인 정렬 알고리즘이다.
 */
public class InsertionSort {
    public static void main(String[] args) {
        int[] arr = {5,7,2,72, 10, 3};
        insertion(arr);
    }

    public static void insertion(int[] arr){
        for(int i=1; i<arr.length; i++){
            int temp = arr[i];
            int prev = i-1;
            while (prev>=0 && arr[prev]>temp){
                arr[prev+1] = arr[prev];
                prev--;
            }
            arr[prev+1] = temp;
            System.out.println(i +"회전: " + Arrays.toString(arr));
        }
        System.out.println(Arrays.toString(arr));
    }
}
