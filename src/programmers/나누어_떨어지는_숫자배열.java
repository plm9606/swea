package programmers;

import java.util.Arrays;

public class 나누어_떨어지는_숫자배열 {
    public int[] solution(int[] arr, int devisor){
        return Arrays.stream(arr).filter(i-> i%devisor==0).sorted().toArray();
    }
}
