package baekjoon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Boj_13460 {
    static int n, m, GOAL = 100, RED = -101, BLUE = -102, WALL = -1,
            min = Integer.MAX_VALUE;
    // 우하좌상
    static int[] dy = {0, 1, 0, -1}, dx = {1, 0, -1, 0};
    static int[] blue, red, goal;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("res/boj13460.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb;
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];

        for (int i = 0; i < n; i++) {
            sb = new StringBuffer(br.readLine());
            for (int j = 0; j < m; j++) {
                char input = sb.charAt(j);
                if (input == '#') {
                    board[i][j] = WALL;
                } else if (input == 'O') {
                    board[i][j] = GOAL;
                    goal = new int[]{i, j};
                } else if (input == 'B') {
                    board[i][j] = BLUE;
                    blue = new int[]{i, j};
                } else if (input == 'R') {
                    board[i][j] = RED;
                    red = new int[]{i, j};
                } else {
                    board[i][j] = 0;
                }
            }
        }

        find(0, red, blue);

        min = min == Integer.MAX_VALUE ? -1 : min;
        System.out.println(min);
    }

    static private void find(int cnt, int[] r, int[] b) {

        // 파란 구슬이 빠지거나 최소 이동횟수보다 클 경우
        if (isInGoal(b) || min < cnt) {
            return;
        }
        // 빨강 구슬이 빠질 경우
        if (isInGoal(r)) {
            min = Math.min(min, cnt);
            return;
        }

        // 원래는 이 코드가 함수 최상단에 있었음.
        // cnt==9일 때 구슬을 최종 10번째로 움직인 후에 함수를 재귀호출하기 때문에 구슬이 골에 들어갔는지를 먼저 검사한 후에
        // cnt가 10일 경우 더이상 움직일 수 없으므로 break를 해주는 순서가 맞는 것.
        // 10번 초과로 움직일 경우
        if (cnt == 10) {
            return;
        }

        for (int i = 0; i < 4; i++) {
            // 먼저 움직여야 할 구슬을 정한다.
            int firstDrop = findFirstDrop(r, b, i);
            // 구슬이 움직인 좌표 ({red[], blue[]})
            int[][] movedBeads = move(r, b, i, firstDrop);
            find(cnt + 1, movedBeads[0], movedBeads[1]);
            board[movedBeads[0][0]][movedBeads[0][1]] = 0;
            board[r[0]][r[1]] = RED;
            board[movedBeads[1][0]][movedBeads[1][1]] = 0;
            board[b[0]][b[1]] = BLUE;
        }
    }

    static private int findFirstDrop(int[] r, int[] b, int dir) {
        if (dir == 3) {
            if (r[0] > b[0]) return BLUE;
            else return RED;
        } else if (dir == 0) {
            if (r[1] > b[1]) return RED;
            else return BLUE;
        } else if (dir == 1) {
            if (r[0] > b[0]) return RED;
            else return BLUE;
        } else {
            if (r[1] > b[1]) return BLUE;
            else return RED;
        }
    }

    static private int[][] move(int[] r, int[] b, int dir, int first) {
        if (first == RED) {
            int[] movedRed = move(r[0], r[1], dir);
            board[r[0]][r[1]] = 0;
            board[movedRed[0]][movedRed[1]] = RED;
            int[] movedBlue = move(b[0], b[1], dir);
            board[b[0]][b[1]] = 0;
            board[movedBlue[0]][movedBlue[1]] = BLUE;
            return new int[][]{movedRed, movedBlue};
        }

        int[] movedBlue = move(b[0], b[1], dir);
        board[b[0]][b[1]] = 0;
        board[movedBlue[0]][movedBlue[1]] = BLUE;
        int[] movedRed = move(r[0], r[1], dir);
        board[r[0]][r[1]] = 0;
        board[movedRed[0]][movedRed[1]] = RED;
        return new int[][]{movedRed, movedBlue};
    }

    static private int[] move(int ry, int rx, int dir) {
        while (true) {
            ry += dy[dir];
            rx += dx[dir];
            /**
             * // 좌표가 board 안에 있지 않거나, 갈 수 있는칸(0)또는 goal이 아닌 경우
             *             // 먼저 다른 색의 구슬이 goal에 도착해서 뒤에 굴러오는 구슬은 goal에 들어갈 수 없도록 하기 위함
             * if (!isInBoard(ry, rx) || board[ry][rx] < 0) {
             *                 ry -= dy[dir];
             *                 rx -= dx[dir];
             *                 break;
             *             }
             *             // goal이라면
             *             if (isInGoal(new int[]{ry, rx})) break;
             */
            // goal이라면
            if (isInGoal(new int[]{ry, rx})) break;
            if (!isInBoard(ry, rx) || board[ry][rx] != 0) {
                ry -= dy[dir];
                rx -= dx[dir];
                break;
            }

        }
        return new int[]{ry, rx};
    }

    static private boolean isInBoard(int y, int x) {
        if (y < 0 || y >= n || x < 0 || x >= m) return false;
        if (board[y][x] == -1) return false;
        return true;
    }

    static private boolean isInGoal(int[] p) {
        return (goal[0] == p[0] && goal[1] == p[1]);
    }
}
