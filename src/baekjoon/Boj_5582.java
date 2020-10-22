package baekjoon;

import java.util.Scanner;

public class Boj_5582 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
//        String first = sc.next();
//        String second = sc.next();
        String first = "ABRACADABRA";
        String second = "ECADADABRBCRDARA";
        int firstLen = first.length();
        int secondLen = second.length();
        int[][] lcs = new int[firstLen + 1][secondLen + 1];
        int max = 0;

        for (int y = 1; y <= firstLen; y++) {
            for (int x = 1; x <= secondLen; x++) {
                if (first.charAt(y - 1) == second.charAt(x - 1)) {
                    lcs[y][x] = lcs[y - 1][x - 1] + 1;
                    max = Math.max(max, lcs[y][x]);
                }
            }
        }

        System.out.println(max);

        solution(first, second);
    }

    static void solution(String s, String ss) {
        //        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        String s = br.readLine(), ss = br.readLine();

        s = "aaaaafdfegwbh";
        ss = "dejjtu";

        // dp[i][j] = s의 i번째 문자와 ss의 j번째 문자를 끝으로 하는 부분 문자열의 최대 길이
        int[][] dp = new int[s.length()][ss.length()];
        int max = 0;
        for (int j = 0; j < ss.length(); j++) {
            if (s.charAt(0) == ss.charAt(j)) dp[0][j] = 1;
        }

        for (int i = 1; i < s.length(); i++) {
            for (int j = 0; j < ss.length(); j++) {
                if (s.charAt(i) == ss.charAt(j)) {
                    if (j == 0) dp[i][j] = 1;
                    else dp[i][j] = 1 + dp[i - 1][j - 1];

                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        System.out.println(max);

    }
}
