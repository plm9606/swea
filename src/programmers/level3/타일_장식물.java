package programmers.level3;

public class 타일_장식물 {
    public static void main(String[] args) {
        System.out.println(solution(6));
    }
    public static long solution(int n) {
        long[] memo = new long[n];

        memo[0] = 1;
        memo[1] = 1;

        for(int i=2; i<n; i++){
            memo[i] = memo[i-1] + memo[i-2];
        }


        return (memo[n-1]*2 + memo[n-2])*2;
    }
}
