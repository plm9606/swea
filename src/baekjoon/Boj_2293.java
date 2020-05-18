package baekjoon;

import java.util.*;

/**
 * 1. 거스름돈 길이 만큼의 배열 arr를 생성
 * 2. 첫번째 동전을 이용해 배열을 초기화한다. (1)
 * 3. 그 다음 동전부터는 해당 동전 값 이상의 인덱스부터 검사한다.
 * 4. coins[i]=c이고, I>=i일때, arr[I] = arr[I]+arr[I-c]
 */
public class Boj_2293 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        int change = sc.nextInt();
//        int[] coins = new int[n];
//        for (int i=0; i<n; i++){
//            coins[i] = sc.nextInt();
//        }

        int change = 5;
        Integer[] coins = {1,1,2,1,1,2,5};

        Set<Integer> set = new HashSet<>();
        for(int coin: coins){
            set.add(coin);
        }

        coins = new Integer[set.size()];
        set.toArray(coins);
        Arrays.sort(coins);

        int[] memo = new int[change+1];
        for(int i=0; i<memo.length; i++){
            memo[i] = 1;
        }

        for(int i=1; i<coins.length;i++){
            for(int j=coins[i]; j<=change; j++){
                memo[j] += memo[j-coins[i]];
            }
        }

        System.out.println(memo[change]);
    }
}
