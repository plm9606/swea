package baekjoon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_12100 {
    static int N, max = -1;
    static int[][] board;
    static int[] dy = {-1, 0, 1, 0}, dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("res/boj12100.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        for (int d = 1; d <= 4; d++) {
            move(moveBoard(d, board), 0);
        }

        System.out.println(max);
    }

    public static void solve(int idx) {
        if (idx == 5) {
            int m = 0;
            for (int i = 0; i < N; i++)
                for (int j = 0; j < N; j++)
                    max = Math.max(max, board[i][j]);
        }

        for (int d = 0; d < 4; d++) {
            int copy[][] = new int[N][N];
            for (int i = 0; i < N; i++)
                for (int j = 0; j < N; j++)
                    copy[i][j] = board[i][j];
        }
    }

    public static void move(int[][] arr, int cnt) {
        if (cnt == 4) {
            return;
        }
        for (int i = 1; i <= 4; i++) {
            move(moveBoard(i, arr), cnt + 1);
        }
    }

    public static int[][] moveBoard(int dir, int[][] arr) {

        int[][] copy = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                copy[i][j] = arr[i][j];
            }
        }

        if (dir == 1) return up(copy);
        else if (dir == 2) return right(copy);
        else if (dir == 3) return down(copy);
        return left(copy);
    }

    public static int[][] up(int[][] arr) {
        int max = 0;
        Queue<Integer> q = new LinkedList<>();
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (arr[y][x] != 0) {
                    q.add(arr[y][x]);
                    arr[y][x] = 0;
                }
            }
            int yy = 0;
            while (!q.isEmpty()) {
                int n = q.poll();
                if (!q.isEmpty() && n == q.peek()) {
                    arr[yy++][x] = n * 2;
                    q.poll();
                } else arr[yy++][x] = n;
                if (max < arr[yy - 1][x]) max = arr[yy - 1][x];
            }
        }

        return arr;
    }

    public static int[][] down(int[][] arr) {
        Queue<Integer> q = new LinkedList<>();
        for (int x = 0; x < N; x++) {
            for (int y = N - 1; y >= 0; y--) {
                if (arr[y][x] != 0) {
                    q.add(arr[y][x]);
                    arr[y][x] = 0;
                }
            }
            int yy = N - 1;
            while (!q.isEmpty()) {
                int n = q.poll();
                if (!q.isEmpty() && n == q.peek()) {
                    arr[yy--][x] = n * 2;
                    q.poll();
                } else arr[yy--][x] = n;
                if (max < arr[yy + 1][x]) max = arr[yy + 1][x];
            }
        }

        return arr;
    }

    public static int[][] right(int[][] arr) {
        Queue<Integer> q = new LinkedList<>();
        for (int y = 0; y < N; y++) {
            for (int x = N - 1; x >= 0; x--) {
                if (arr[y][x] != 0) {
                    q.add(arr[y][x]);
                    arr[y][x] = 0;
                }
            }
            int xx = N - 1;

            while (!q.isEmpty()) {
                int n = q.poll();
                if (!q.isEmpty() && n == q.peek()) {
                    arr[y][xx--] = n * 2;
                    q.poll();
                } else arr[y][xx--] = n;
                if (max < arr[y][xx + 1]) max = arr[y][xx + 1];
            }
        }

        return arr;
    }

    public static int[][] left(int[][] arr) {
        Queue<Integer> q = new LinkedList<>();
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (arr[y][x] != 0) {
                    q.add(arr[y][x]);
                    arr[y][x] = 0;
                }
            }
            int xx = 0;
            while (!q.isEmpty()) {
                int n = q.poll();
                if (!q.isEmpty() && n == q.peek()) {
                    arr[y][xx++] = n * 2;
                    q.poll();
                } else arr[y][xx++] = n;
                if (max < arr[y][xx - 1]) max = arr[y][xx - 1];
            }
        }

        return arr;
    }

}
