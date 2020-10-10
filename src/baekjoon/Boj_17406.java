package baekjoon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Boj_17406 {
    static int[][] A, queries;
    static int n, m, k, min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("res/boj17406.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        A = new int[n + 1][m + 1];
        queries = new int[k][3];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++)
                queries[i][j] = Integer.parseInt(st.nextToken());
        }

        int queryCnt = queries.length;
        permutation(new int[queryCnt], 0, new boolean[queryCnt]);

        System.out.println(min);
    }

    public static void permutation(int[] order, int picked, boolean[] check) {
        if (order.length == picked) {
            int[][] arr = new int[n + 1][m + 1];
            for (int i = 1; i <= n; i++)
                for (int j = 1; j <= m; j++)
                    arr[i][j] = A[i][j];

            for (int i = 0; i < order.length; i++) {
                move(queries[order[i]][0], queries[order[i]][1], queries[order[i]][2], arr);
            }

            for (int i = 1; i <= n; i++) {
                int acc = 0;
                for (int j = 1; j <= m; j++)
                    acc += arr[i][j];
                min = Math.min(min, acc);
            }
            return;
        }

        for (int i = 0; i < order.length; i++) {
            if (!check[i]) {
                order[picked] = i;
                check[i] = true;
                permutation(order, picked + 1, check);
                check[i] = false;
            }
        }
    }

    static void move(int r, int c, int s, int[][] arr) {

        while (s > 0) {
            int a = arr[r - s][c - s];

            // 왼쪽변
            for (int i = r - s; i < r + s; i++) {
                arr[i][c - s] = arr[i + 1][c - s];
            }
            // 밑변
            for (int i = c - s; i < c + s; i++) {
                arr[r + s][i] = arr[r + s][i + 1];
            }
            for (int i = r + s; i > r - s; i--) {
                arr[i][c + s] = arr[i - 1][c + s];
            }
            for (int i = c + s; i > c - s; i--) {
                arr[r - s][i] = arr[r - s][i - 1];
            }
            arr[r - s][c - s + 1] = a;
            s--;
        }

//        for (int[] row : arr)
//            System.out.println(Arrays.toString(row));
    }
}
