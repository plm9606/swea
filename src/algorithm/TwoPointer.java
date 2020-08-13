package algorithm;

/**
 * 문제에서 "연속된" 데이터 구간을 처리하기를 원한다면?
 *
 * N개의 자연수로 구성된 수열이 있습니다
 * 합이 M인 부분 연속 수열의 개수를 구하세요
 * O(N)
 */
public class TwoPointer {
    public static void main(String[] args) {
        int[] arr = {1,2,3,2,5};
        int n = 5, m=5;
        int acc=0, end = 0, cnt=0;

        // start를 차례로 증가시키며 반복
        for(int start=0; start<n; start++){
            // end를 가능한 만큼 이동
            while (acc < m && end < n){
                acc += arr[end];
                end++;
            }
            if(acc == m){
                cnt++;
            }
            acc -= arr[start];
        }

        System.out.println(cnt);
    }

}
