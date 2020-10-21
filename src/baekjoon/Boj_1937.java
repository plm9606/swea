package baekjoon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Boj_1937 {
    static int[][] arr, dp;
    static int[] dy = {0, 1, 0, -1}, dx = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("res/boj1937.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[N][N];

        int k = 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (dp[i][j] != 0) continue;
                dp[i][j] = Math.max(dp[i][j], go(i, j, N));
                k = Math.max(k, dp[i][j]);
            }
        }

        System.out.println(k);
    }

    static int go(int y, int x, int N) {
        if (dp[y][x] > 0) return dp[y][x];

        dp[y][x] = 1;

        for (int d = 0; d < 4; d++) {
            int yy = y + dy[d];
            int xx = x + dx[d];
            if (yy < 0 || yy >= N || xx < 0 || xx >= N) continue;
            if (arr[y][x] >= arr[yy][xx]) continue;
            dp[y][x] = Math.max(dp[y][x], go(yy, xx, N) + 1);
        }

        return dp[y][x];
    }
}