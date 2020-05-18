package baekjoon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Boj_2294 {
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        int change = sc.nextInt();
//        Set<Integer> set = new HashSet<>();
//        for (int i=0; i<n; i++){
//            set.add(sc.nextInt());
//        }
//        Integer[] coins = new Integer[set.size()];

        int change = 13;//8;
        Integer[] coins = {12, 5,5,5,12};//{1,4,6};
        Set<Integer> set = new HashSet<>();
        for(int coin: coins){
            set.add(coin);
        }

        coins = new Integer[set.size()];
        set.toArray(coins);
        Arrays.sort(coins);
        int[] memo = new int[change+1];
        for(int i=0; i<=change; i++){
            memo[i] = i%coins[0]==0 ? i/coins[0]: 10001;
        }

        for(int i=1; i<coins.length; i++){
            int coin = coins[i];
            for (int j=coin; j<=change; j++){
                memo[j] = Math.min(memo[j], memo[j-coin] +1);
            }
        }

        if(memo[change]>=10001) System.out.println(-1);
        else System.out.println(memo[change]);

    }
}
