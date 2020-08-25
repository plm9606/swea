package baekjoon;

import java.util.Scanner;

public class Boj_9095 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int test_case = sc.nextInt();
        for(int tc = 0; tc<test_case; tc++){
            int n = 10;//sc.nextInt();
            int[] memo = new int[n+1];

            if(n<3){
                System.out.println(n);
                continue;
            }
            memo[0]=1;
            memo[1]=1;
            memo[2] = 2;
            for(int i=3; i<=n; i++){
                memo[i] = (memo[i-1]+memo[i-2]+memo[i-3])%1000000009;
            }
            System.out.println(memo[n]);
        }
    }
}
