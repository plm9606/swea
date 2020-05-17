package programmers.level3;

import java.util.Arrays;

public class 거스름돈 {
    public static void main(String[] args) {
        int[] money = {1,2,5};
        System.out.println(solution(5, money));
    }
    public static int solution (int change, int[] money){
        Arrays.sort(money);

        int answer = 0;
        int[] memo = new int[change+1];

        for(int i=0; i<=change; i++){
            memo[i] = (i%money[0] == 0)?1:0;
        }

        for(int i=1; i<money.length; i++){
            int coin = money[i];
            for(int k=coin; k<=change; k++){
                memo[k] += memo[k-coin];
            }
        }
        answer = memo[change];
        return answer;
    }
}
