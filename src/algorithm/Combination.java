package algorithm;

import java.util.ArrayList;

public class Combination {
    static int[][] sum = new int[30][30];

    public static int c(int n, int r) {
        if (sum[n][r] != 0) return sum[n][r];
        if (n == r || r == 0) return 1;
        else {
            sum[n][r] = c(n - 1, r - 1) + c(n - 1, r);
            return sum[n][r];
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};

        ArrayList<Integer>[] lists = new ArrayList[10];

        // for(int i=1; i<=arr.length; i++)
        boolean[] b = new boolean[arr.length];
        combination(arr, b, 2, 0);

        System.out.println(c(6, 2));
    }

    public static void combination(int[] arr, boolean[] bit, int toChoice, int target) {
        if (toChoice == 0) {
            print(arr, bit);
            return;
        }

        for (int i = target; i < arr.length; i++) {
            bit[i] = true;
            combination(arr, bit, toChoice - 1, i + 1);
            bit[i] = false;
        }
    }

    public static void print(int[] arr, boolean[] bit) {
        for (int i = 0; i < arr.length; i++) {
            boolean b = bit[i];
            if (b) {
                System.out.print(arr[i] + " ");
            }
        }
        System.out.println();
    }
}
