package baekjoon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Boj_15990 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("res/boj15990.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[] tc = new int[T];
        int max = 0;
        for (int i = 0; i < T; i++) {
            tc[i] = Integer.parseInt(br.readLine());
            if (max < tc[i]) max = tc[i];
        }

        // dp[n][i] : n을 만드는 수식 중 i로 끝나는 수식
        long[][] dp = new long[max + 1][4];

        dp[1][1] = 1;
        dp[2][2] = 1;
        dp[3][1] = 1;
        dp[3][2] = 1;
        dp[3][3] = 1;

        for (int n = 4; n <= max; n++) {
            dp[n][1] = (dp[n - 1][2] + dp[n - 1][3]) % 1000000009;
            dp[n][2] = (dp[n - 2][1] + dp[n - 2][3]) % 1000000009;
            dp[n][3] = (dp[n - 3][1] + dp[n - 3][2]) % 1000000009;
        }

        for (int n : tc) {
            System.out.println((dp[n][1] + dp[n][2] + dp[n][3]) % 1000000009);
        }
    }
}
