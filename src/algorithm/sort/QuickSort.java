package algorithm.sort;

import java.util.Arrays;

/**
 * 퀵정렬은 분할과 정복 기법을 이용해서 원소들을 정복한다. pivot이라는 값을 기준으로 왼쪽에는 작은 값, 오른쪽에는 큰 값이 오도록 '분할' 한다.
 * 좌우로 분할된 리스트를 재귀적으로 이 과정을 반복한다.
 *
 * Merge sort와 달리 배열을 비균등하게 분할한다.(pivot의 위치에 따라 달라짐)
 * 원소들 중에 같은 값이 있는 경우 같은 값들의 정렬 이후 순서가 초기 순서와 달라질 수 있어 불안정 정렬에 속한다.
 *
 * 배열에서 하나의 원소를 고른다.(pivot)
 * 피벗 앞에는 피벗보다 값이 작은 모든 원소들이 오고, 피벗 뒤에는 피벗보다 값이 큰 모든 원소들이 오도록(정복) 피벗을 기준으로 리스트를 둘로 나눈다.(분할)분할을 마친 뒤에 피벗은 더 이상 움직이지 않는다.
 * 분할된 두 개의 작은 리스트에 대해 재귀(Recursion)적으로 이 과정을 반복한다. 재귀는 리스트의 크기가 0이나 1이 될 때까지 반복된다.
 *
 * 시간복잡도: O(nlog2n) / 최악: O(n^2) (정렬하고자 하는 배열이 이미 정렬되어 있는 경우)
 * 주어진 배열 안에서 교환(swap)을 통해, 정렬이 수행되므로 O(n)이다.
 *
 * JAVA에서 Arrays.sort() 내부적으로도 Dual Pivot Quick Sort로 구현되어 있을 정도로 효율적인 알고리즘
 * 기존의 Quick Sort는 Pivot이 한개 였지만 Dual-Pivot Quick Sort는 Pivot을 2개를 써서
 * Pivot 1보다 낮은 그룹 / Pivot1 ~ Pivot2 사이의 그룹(Pivot1, Pivot2는 제외) / Pivot2 보다 큰 그룹
 * 이렇게 3가지 그룹을 왼쪽, 중간, 오른쪽의 부분 리스트로 나눈다.
 */
public class QuickSort {
    public static void main(String[] args) {
        int arr[] = {3,8,0,2,1,4};
        quick(arr, 0, 5);
        System.out.println(Arrays.toString(arr));
    }

    public static void quick(int[] arr, int left, int right){
        if(left>= right) return;
        int pivot = arr[left];
        int i = left;
        int j = right;

        while (i<j){
            while (pivot < arr[j]){
                j--;
            }
            while (i<j && pivot >= arr[i]){
                i++;
            }
            if(i<j){
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        arr[left] = arr[i];
        arr[i] = pivot;
        System.out.println("pivot: " + pivot + ", left: " + left + ", right: " + right + ", i: "+i+" " + Arrays.toString(arr));
        quick(arr, left, i-1);
        quick(arr, i+1, right);
    }
}
