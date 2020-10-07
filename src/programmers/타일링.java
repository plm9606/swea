package programmers;

import java.util.Arrays;

public class 타일링 {
    public static void main(String[] args) {
        int[][] dp = new int[2][2];

        System.out.println(316290802 % 1000000007);
    }

    // 처음에 점화식을 세우려고 규칙을 찾고 이렇게 세웠는데 숫자가 커지면 오답이 나왔다
    // dp[i][j] =  가로2인 직사각형 두개를 사용한 정사각형 j개를 사용해 가로가 i인 직사각형 만든 경우
    public int solution(int n) {
        int answer = 0;
        int[][] dp = new int[n + 1][n / 2 + 1];
        int mod = 1000000007;
        dp[1][0] = 1;
        dp[2][0] = 1;
        dp[2][1] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= i / 2; j++) {
                dp[i][j] = (dp[i - 1][j] + dp[i - 2][j - 1]) % mod;
                dp[i][j] %= mod;
            }
        }


        return Arrays.stream(dp[n]).sum() % mod;
    }

    // 그냥 피보나치였음..
    public int solution2(int n) {
        int answer = 0;
        int mod = 1000000007;
        int[] dp = new int[n + 1];

        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % mod;
        }
        return dp[n];
    }
}
