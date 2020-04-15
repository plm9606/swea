package algorithm.sort;

import java.util.Arrays;

/**
 * heap: 완전 이진 트리를 이용한 자료구조. 최대/최소값 검색에 효과적이다.
 *
 * 1. 최대 힙을 구성
 * 2. 현재 힙 루트는 가장 큰 값이 존재함. 루트의 값을 마지막 요소와 바꾼 후, 힙의 사이즈 하나 줄임
 * 3. 힙의 사이즈가 1보다 크면 위 과정 반복
 *
 * heap sort는 불안정 정렬에 속함
 * 시간복잡도: O(nlogn) (평균/최선/최악악
 */

public class HeapSort {
    public static void main(String[] args) {
        int[] array = { 230, 10, 60, 550, 40, 220, 20 };

        heapSort(array);
        System.out.println("result: " + Arrays.toString(array));

    }

    public static void heapSort(int[] arr){
        int len = arr.length;

        // max heap 초기화
        for (int i= len/2-1; i>=0; i--){
            heapify(arr, len, i);
        }

        System.out.println(Arrays.toString(arr));


        // heap에서 오름차순으로 arr 변경
        for(int i= len-1; i>0; i--){
            swap(arr, 0, i);
            heapify(arr, i, 0);
        }
    }

    public static void heapify(int[] arr, int len, int idx){
        int maxIdx = idx;
        int left = idx*2+1;
        int right = idx*2+2;

        if(left < len && arr[maxIdx] < arr[left]){
            maxIdx = left;
        }
        if(right<len && arr[maxIdx] < arr[right]){
            maxIdx = right;
        }

        // 자식 노드가 더 크다면
        if(idx != maxIdx){
            swap(arr, idx, maxIdx);

            heapify(arr, len, maxIdx);
        }
    }

    private static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}