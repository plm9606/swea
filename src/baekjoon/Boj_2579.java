package baekjoon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Boj_2579 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("res/boj.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] stairs = new int[N];
        // dp[i][j] = i번째 계단까지 최대 계단수, 연속 j회 한칸 이동(한칸 이동 연속 2번 불가능)
        int[][] dp = new int[N][2];

        for (int i = 0; i < N; i++) {
            stairs[i] = Integer.parseInt(br.readLine());

        }

        dp[0][0] = stairs[0];

        // n=1일떼 예외처리
        if (N > 1) {
            dp[1][0] = stairs[1];
            dp[1][1] = stairs[0] + stairs[1];

            for (int i = 2; i < N; i++) {
                dp[i][0] = stairs[i] + Math.max(dp[i - 2][0], dp[i - 2][1]);
                dp[i][1] = stairs[i] + dp[i - 1][0];
            }
        }
        System.out.println(Math.max(dp[N - 1][0], dp[N - 1][1]));
    }
}
