package baekjoon;

import java.io.IOException;

public class Boj_10844 {
    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int N = Integer.parseInt(br.readLine());
        int MUL = 1000000000;
        int N = 3;
        int dp[][] = new int[N + 1][10];

        if (N == 1) {
            System.out.println(9);
            return;
        }

        for (int i = 0; i < 10; i++) {
            dp[1][i] = 1;
        }

        long acc = 0;
        for (int i = 2; i <= N; i++) {
            for (int j = 0; j < 10; j++) {
                if (j - 1 < 0) dp[i][j] = dp[i - 1][j + 1] % MUL;
                else if (j + 1 > 9) dp[i][j] = dp[i - 1][j - 1] % MUL;
                else dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % MUL;

                if (i == N) acc += dp[i][j];
            }
        }

        System.out.println((acc - dp[N][0]) % MUL);

    }
}
