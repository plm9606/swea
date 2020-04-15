package programmers.level1;

import java.util.ArrayList;
import java.util.Arrays;

public class 나누어_떨어지는_숫자배열 {
    public static void main(String[] args) {
        int[]arr = {5, 9, 7, 10};
        System.out.println(Arrays.toString(solution(arr,5)));
        }

    public static int[] solution(int[] arr, int divisor){
        int[] answer = {-1};
        int[] res =  Arrays.stream(arr).filter(i-> i%divisor==0).sorted().toArray();
        if(res.length==0){
            return answer;
        }
        return res;
    }
}
