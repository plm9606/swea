package baekjoon;

import java.util.Scanner;

public class Boj_11727 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 12;//sc.nextInt();
        if(n==1){
            System.out.println(1);
            return;
        }
        int[] memo = new int[n+1];
        memo[0] = 1;
        memo[1] = 1;

        for(int i=2; i<=n; i++){
            memo[i] = (memo[i-1]+memo[i-2]*2)%10007;
        }

        System.out.println(memo[n]);
    }
}
