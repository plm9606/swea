package baekjoon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_14500 {
    static int[][] board;
    static ArrayList<int[][]> blocks = new ArrayList<>();
    static int max, n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("res/boj14500.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        max = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++)
                board[i][j] = Integer.parseInt(st.nextToken());
        }
        br.close();
        blocks.add(new int[][]{{0, 0}, {0, 1}, {0, 2}, {0, 3}}); //-
        blocks.add(new int[][]{{0, 0}, {0, 1}, {1, 0}, {1, 1}}); // ㅁ
        blocks.add(new int[][]{{0, 0}, {1, 0}, {2, 0}, {2, 1}});  // ㄴ
        blocks.add(new int[][]{{0, 0}, {1, 0}, {1, 1}, {2, 1}}); //ㄹ
        blocks.add(new int[][]{{0, 0}, {0, 1}, {1, 1}, {0, 2}}); //ㅜ

        for (int rotate = 1; rotate <= 4; rotate++) {
            int[][] copy;
            copy = new int[board[0].length][board.length];

            int row = copy.length, col = copy[0].length;
            for (int y = 0; y < board.length; y++) {
                for (int x = 0; x < board[1].length; x++) {
                    copy[x][board.length - 1 - y] = board[y][x];
                }
            }
            board = copy;

            for (int r = 0; r < row; r++) {
                System.out.println(Arrays.toString(board[r]));
            }

            System.out.println();
            find(board);

            int[][] copy2 = new int[row][col];

            //y축 대칭
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    copy2[i][col - 1 - j] = copy[i][j];
                }
            }

            find(copy2);
            // x축 대칭

            for (int y = 0; y < row; y++) {
                for (int x = 0; x < col; x++) {
                    copy2[row - 1 - y][x] = copy[y][x];
                }
            }

            find(copy2);

        }

        System.out.println(max);
    }

    public static void find(int[][] arr) {
        int row = arr.length, col = arr[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                for (int[][] block : blocks) {
                    boolean suitable = true;
                    int acc = 0;
                    for (int[] point : block) {
                        int yy = point[0] + i;
                        int xx = point[1] + j;
                        if (!check(yy, xx, row, col)) {
                            suitable = false;
                            break;
                        }
                        acc += arr[yy][xx];
                    }
                    if (suitable && acc > max) {
                        max = acc;
                    }
                }
            }
        }
    }

    public static boolean check(int y, int x, int row, int col) {
        if (y < 0 || y >= row || x < 0 || x >= col) return false;
        return true;
    }
}
