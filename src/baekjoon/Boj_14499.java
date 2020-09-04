package baekjoon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Boj_14499 {
    static int[][] board;
    static int EAST = 1, WEST = 2, NORTH = 3, SOUTH = 4, x, y, m, n;
    static int[] dice = new int[6];
    static int[] dx = {0, 1, -1, 0, 0}, dy = {0, 0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("res/boj14499.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            move(Integer.parseInt(st.nextToken()));
        }
    }

    public static void move(int dir) {
        if (y + dy[dir] < 0 || y + dy[dir] >= n || x + dx[dir] < 0 || x + dx[dir] >= m) return;
        y += dy[dir];
        x += dx[dir];
        if (dir == EAST) {
            int temp = dice[0];
            dice[0] = dice[3];
            dice[3] = dice[5];
            dice[5] = dice[2];
            dice[2] = temp;
        } else if (dir == WEST) {
            int temp = dice[0];
            dice[0] = dice[2];
            dice[2] = dice[5];
            dice[5] = dice[3];
            dice[3] = temp;
        } else if (dir == NORTH) {
            int temp = dice[0];
            dice[0] = dice[4];
            dice[4] = dice[5];
            dice[5] = dice[1];
            dice[1] = temp;
        } else {
            int temp = dice[0];
            dice[0] = dice[1];
            dice[1] = dice[5];
            dice[5] = dice[4];
            dice[4] = temp;
        }

        if (board[y][x] == 0) {
            board[y][x] = dice[5];
        } else {
            dice[5] = board[y][x];
            board[y][x] = 0;
        }
        System.out.println(dice[0]);
    }
}
