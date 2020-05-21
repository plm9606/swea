package programmers.level2;

public class 피보나치 {
        public int solution(int n) {
            int answer = 0;
            int[] memo = new int[n + 1];

            memo[0] = 0;
            memo[1] = 1;


            for (int i = 2; i <= n; i++) {
                memo[i] = (memo[i - 1] + memo[i - 2]) % 1234567;
            }

            return memo[n];
        }
}
