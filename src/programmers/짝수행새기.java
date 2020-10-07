package programmers;

public class 짝수행새기 {
    static int r, c;
    static long[][] combi;
    static long MOD = (long) (1e7 + 19);

    public static void main(String[] args) {
        new 짝수행새기().solution(new int[][]{{0, 1, 0}, {1, 1, 1}, {1, 1, 0}, {0, 1, 1}});
    }

    public int solution(int[][] a) {
        int r = a.length;
        int c = a[0].length;
        combi = new long[301][301];
        int[] oneCnts = new int[c + 1];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                oneCnts[j + 1] += a[i][j];
            }
        }
        long dp[][] = new long[c + 1][r + 1];
        dp[1][r - oneCnts[1]] = getCombination(r, oneCnts[1]);

        for (int col = 1; col <= c; col++) {
            int oneCnt = oneCnts[col];
            for (int row = 0; row <= r; row++) {
                // 기존에 있던 행중에 짝수행에 1을 세팅하고자하는 개수
                for (int k = 0; k <= oneCnt; k++) {
                    // 기존 행 중에 홀수행에 1을 세팅하고자 하는 개수
                    // 세팅해야 하는 개수에서 짝수에 할당 한 수를 뺀 나머지
                    int setOnOdd = oneCnt - k;

                    // 세팅 후 짝수행의 개수 = 짝수행 중 1을 세팅하지 않은 행 + 1을 세팅한 홀수행
                    int evenRow = (row - k) + setOnOdd;
                    if (setOnOdd < 0 || evenRow > r || evenRow < 0) continue;

                    // 경우의 수 = 짝수행에 1을 세팅하는 경우의 수 * 홀수행에 1을 세팅하는 경우의 수 % MOD
                    long probability = (long) ((getCombination(row, k) * getCombination(r - row, oneCnt - k)) % MOD);
                    dp[col][evenRow] += dp[col - 1][row] * probability % MOD;
                    dp[col][evenRow] %= MOD;
                }
            }
        }

        System.out.println(dp[c][r]);
        return (int) dp[c][r];
    }

    public long getCombination(int n, int r) {
        if (n < r) return 0;
        if (combi[n][r] != 0) return combi[n][r];
        if (n == r || r == 0) return 1;
        else {
            combi[n][r] = getCombination(n - 1, r - 1) + getCombination(n - 1, r);
            return combi[n][r];
        }
    }

}
