package baekjoon;

import java.util.Scanner;

/**
 * 1. change 만큼 길이의 배열 memo를 만든다. MAXVAL로 초기화한다.
 * 2. 주어진 동전의 작은 단위부터 최소개수를 구한다.
 * 2-1. 가장 작은 단위는 %를 이용해 해당 동전만으로 금액을 내는 경우를 만든다.
 * 3. 그다음 동전(coins[i] = c)부터는 memo를 계속 업데이트 한다.
 * 3-1. memo[c]전까지는 기존 값을 사용한다.
 * 3-2. 그 이후 인덱스(I)부터는 coins[I]와 coins[I-c]+1중 min값을 저장한다.
 */
public class Boj_11047 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        int change = sc.nextInt();
//        int[] coins = new int[n];
//        for (int i=0; i<n; i++){
//            coins[i] = sc.nextInt();
//        }

        int change = 4200;//8;
        int[] coins = {1,
                5,
                10,
                50,
                100,
                500,
                1000,
                5000,
                10000,
                50000};//{1,4,6};

        int[] memo = new int[change+1];
        for(int i=0; i<=change; i++){
            memo[i] = i%coins[0]==0 ? i/coins[0]: Integer.MAX_VALUE;
        }

        for(int i=1; i<coins.length; i++){
            int coin = coins[i];
            for (int j=coin; j<=change; j++){
             memo[j] = Math.min(memo[j], memo[j-coin] +1);
            }
        }

        System.out.println(memo[change]);

    }

}
