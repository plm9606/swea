package baekjoon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Boj_11052 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("res/boj11052.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());// 한줄씩 읽는다. "\n", "\r"을 만날때 까지 읽어온다.
        int[] card = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            card[i] = Integer.parseInt(st.nextToken());
        }
        int[][] dp = new int[N+1][N+1];

       for(int i=1; i<=N; i++){
           dp[1][i] = dp[1][i-1]+card[1];
       }

        for(int i=2; i<=N; i++){
            for(int j=1; j<=N; j++){
                if(j<i){
                    dp[i][j] = dp[i-1][j];
                }else if(i==j){
                    dp[i][j] = Math.max(dp[i-1][j], card[i]);
                }else {
                    dp[i][j] = Math.max(dp[i-1][j], card[i]+dp[i][j-i]);
                    if(j%i==0){
                        dp[i][j] = Math.max(dp[i][j], card[i]*(j/i));
                    }
                }
            }
        }

        System.out.println(dp[N][N]);
    }
}
