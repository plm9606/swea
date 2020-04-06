package algorithm.search;

import java.util.Arrays;
import java.util.Optional;

/**
 * 이분탐색은 일단 탐색할 배열이 정렬되어있어야 합니다.
 * left와 right로 mid값을 결정합니다.
 * mid와 구하고자 하는 값을 비교 후, mid보다 작으면 mid의 왼쪽을, 크면 오른쪽을 다시 탐색합니다.
 *
 * 처음부터 끝까지 돌면서 탐색하는 것보다 훨씬 빠른 장점이 있다.
 * 시간복잡도: O(logN)
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {2,3,5,7,10,15,20,22,29,50,33,39};
        Integer answer = binary(arr, 39);
        if(answer == null){
            System.out.println("cannot find");
        }else
        System.out.println("find in " + answer);
    }

    public static Integer binary(int[] arr, int find){
        int start = 0;
        int end = arr.length-1;
        Integer answer = null;

        while (start<=end){
            int mid = (start+end)/2;
            if(arr[mid] == find){
                answer = mid;
                break;
            }
            if(find > arr[mid]){
                start = mid+1;
            }else {
                end = mid-1;
            }
        }
        return answer;
    }
}