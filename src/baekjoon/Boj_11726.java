package baekjoon;

import java.util.Scanner;

public class Boj_11726 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n= 2;//sc.nextInt();
        if(n==1){
            System.out.println(1);
            return;
        }
        int[] memo = new int[n+1];
        memo[0] = 1;
        memo[1] = 1;

        for (int i=2; i<=n; i++){
            memo[i] = (memo[i-2]+memo[i-1])%1007;
        }

        System.out.println(memo[n]);
    }
}
