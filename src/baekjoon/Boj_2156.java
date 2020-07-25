package baekjoon;

import java.util.Scanner;

public class Boj_2156 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = 7;//sc.nextInt();
        int[] wine = new int[N];
        int answer = -1;

//        for(int i=0; i<N; i++){
//            wine[i] = sc.nextInt();
//        }
        wine = new int[]{6,999,999,1,1,999,999};
        int[][] dp = new int[N+1][5];

        for(int i=1; i<=N; i++){
            int cur = wine[i-1];
            dp[i][0] = Math.max(Math.max(dp[i-1][1]+cur, dp[i-1][2]+cur),dp[i-1][4]+cur);
            dp[i][1] = Math.max(Math.max(dp[i-1][1], dp[i-1][2]),dp[i-1][4]);
            dp[i][2] = dp[i-1][3];
            dp[i][3] = dp[i-1][0]+cur;
            dp[i][4] = dp[i-1][0];
        }

        for(int i=0; i<5;i++){
            if(dp[N][i] > answer) answer = dp[N][i];
        }

        System.out.println(answer);
    }
}
