package swea;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution_1245 {
    static int[] M;
    static int[] D;
    static int n;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("res/input1245.txt"));


        Scanner sc = new Scanner(System.in);
        sc.nextInt();

        for (int test_case = 1; test_case <= 10; test_case++) {
            int answer = 0;
            n = sc.nextInt();
            M = new int[n];
            D = new int[n];
            for (int i = 0; i < n; i++) {
                D[i] = sc.nextInt();
            }
            for (int i = 0; i < n; i++) {
                M[i] = sc.nextInt();
            }

            System.out.printf("#%d ", test_case);
            for (int i = 0; i < n - 1; i++) {
                System.out.printf("%.10f", solve(i, 0, (D[i] + D[i + 1]) / 2.0, D[i], D[i + 1]));
            }
            System.out.printf("\n");
        }
    }

    public static double solve(int idx, int depth, double cur, double left, double right) {
        if (depth == 100) return cur;
        double f = 0.0;
        double result = 0.0;

        for (int i = 0; i <= idx; i++) {
            f += (double) M[i] / Math.pow(cur - D[i], 2.0);
        }
        for (int i = idx + 1; i < n; i++) {
            f -= (double) M[i] / Math.pow(D[i] - cur, 2.0);
        }

        if (f < 0) {
            result = solve(idx, 1 + depth, (left + cur) / 2.0, left, cur);
        } else if (f > 0) {
            result = solve(idx, 1 + depth, (cur + right) / 2.0, cur, right);
        } else {
            result = cur;
        }
        return result;
    }
}
