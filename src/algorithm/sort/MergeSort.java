package algorithm.sort;

import java.util.Arrays;

/**
 * 병합정렬은 분할 정복 방법을 통해 정렬하는 알고리즘이다.
 * 요소를 쪼갤 수 있는 만큼 쪼갠 후, 다시 합병시키면서 정렬해나가는 방식이다.
 * 이미 합병의 대상이 되는 두 영역은 모두 정렬된 상태이기 때문에 두 배열을 순차적으로 비교하면서 정렬하면 된다.
 *
 * 퀵소트와의 차이점
 * 퀵정렬 : 우선 피벗을 통해 정렬(partition) → 영역을 쪼갬(quickSort)
 * 합병정렬 : 영역을 쪼갤 수 있을 만큼 쪼갬(mergeSort) → 정렬(merge)
 * 퀵소트와 반대로 안정 정렬에 속함
 *
 * 합병정렬은 순차적인 비교로 정렬을 진행하므로, LinkedList의 정렬이 필요할 때 사용하면 효율적이다
 * 퀵정렬은 순차접근이 아닌 임의접근이기때문에 성능이 좋지 못하다.
 * (LinkedList는 삽입, 삭제 연산에서 유용하지만 search 연산에서는 비효율적이기 때문)
 *
 * 시간복잡도: O(nlogn)
 */
public class MergeSort {
    public static void main(String[] args) {
        int arr[] = {3,7,1,19, 2, 10, 15};
        mergeSort(arr);

        System.out.println(Arrays.toString(arr));
    }

    public static void mergeSort(int[] arr) {
        devide(arr, 0, arr.length-1);
    }
    public static void devide(int[] arr, int left, int right){
        if(left >= right) return;

        int mid = (left+right)/2;
        devide(arr, left, mid);
        devide(arr, mid+1, right);
        merge(arr, left, mid, right);
    }

    public static void merge(int[] arr, int left, int mid, int right){
        int[] leftArr = Arrays.copyOfRange(arr, left, mid+1);
        int[] rightArr = Arrays.copyOfRange(arr, mid+1, right+1);
        int l=0,r = 0;
        int idx = left;
        while (l<leftArr.length && r<rightArr.length){
            if(leftArr[l] < rightArr[r]){
                arr[idx] = leftArr[l];
                l++;
            }else {
                arr[idx] = rightArr[r];
                r++;
            }
            idx++;
        }
        while (l<leftArr.length){
            arr[idx] = leftArr[l];
            idx++;
            l++;
        }
        while (l<rightArr.length){
            arr[idx] = rightArr[r];
            idx++;
            r++;
        }
    }
}
