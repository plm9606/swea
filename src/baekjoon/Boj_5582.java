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
    }
}
