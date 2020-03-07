package baekjoon;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Boj_9252 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        String first = sc.next();
//        String second = sc.next();
        String first = "ACAYKP";
        String second = "CAPCK";
        int firstLen = first.length();
        int secLen = second.length();
        int[][] lcs = new int[firstLen + 1][secLen + 1];
        boolean[][] bridge = new boolean[firstLen + 1][secLen + 1];
        String answer = "";

        for (int y = 1; y <= firstLen; y++) {
            for (int x = 1; x <= secLen; x++) {
                if (first.charAt(y - 1) == second.charAt(x - 1)) {
                    lcs[y][x] = lcs[y - 1][x - 1] + 1;
                    bridge[y - 1][x - 1] = true;
                } else {
                    lcs[y][x] = Math.max(lcs[y - 1][x], lcs[y][x - 1]);
                }
            }
        }

        Stack<List<Integer>> stack = new Stack<>();
        stack.push(Arrays.asList(firstLen, secLen));
        while (stack.size() > 0) {
            List<Integer> point = stack.pop();
            int y = point.get(0);
            int x = point.get(1);
            int value = lcs[y][x];

            if (x == 0 || y == 0) break;
            if (lcs[y - 1][x] == value || lcs[y][x - 1] == value) {
                if (lcs[y - 1][x] == value) stack.push(Arrays.asList(y - 1, x));
                if (lcs[y][x - 1] == value) stack.push(Arrays.asList(y, x - 1));
            } else {
                if (bridge[y - 1][x - 1]) {
                    stack.push(Arrays.asList(y - 1, x - 1));
                    answer = first.charAt(y - 1) + "" + answer;
                }
            }
        }

        System.out.println(lcs[firstLen][secLen] + ", " + answer);
    }
}
