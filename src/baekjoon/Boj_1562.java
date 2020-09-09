package baekjoon;

import java.io.IOException;

public class Boj_1562 {
    public static int N, MOD = 1000000000,
    //2^10, 0~9까지 숫자의 사용여부를 체크하기 위해 비트마스크를 이용한다.
    VISIT = 1 << 10;
    // dp[i][j][k] = i자리 숫자중 j로 끝나면서 k에 마킹된 숫자를 쓴 계단수의 개수
    public static long dp[][][];

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        N = Integer.parseInt(br.readLine());
        N = 40;
        dp = new long[N + 1][10][VISIT];

        for (int i = 1; i < 10; i++) {
            dp[1][i][1 << i] = 1;
        }

        for (int i = 2; i <= N; i++) {
            for (int cur = 0; cur < 10; cur++) {
                for (int k = 0; k < VISIT; k++) {
                    // 만약 마지막 자리에 3이 추가되었다면, 00 0000 1000이 현재 상태 k에 추가되어야 하고 이것은 or연산으로 나타낼 수 있다.
                    int bit = k | (1 << cur);

                    if (0 < cur) dp[i][cur][bit] += (dp[i - 1][cur - 1][k]) % MOD;
                    if (cur < 9) dp[i][cur][bit] += (dp[i - 1][cur + 1][k]) % MOD;
                    dp[i][cur][bit] %= MOD;
                }
            }
        }

        long sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += dp[N][i][VISIT - 1];
        }
        System.out.println(sum % MOD);
    }

}
