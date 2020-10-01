package baekjoon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj_17837 {
    static int WHITE = 0, RED = 1, BLUE = 2;
    static int[] dy = {0, 0, -1, 1}, dx = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("res/boj17837.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] board = new int[n][n];
        int[][][] acc = new int[n][n][3];
        ArrayList<int[]> chessmen = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        chessmen.add(new int[4]);
        for (int i = 1; i <= k; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            chessmen.add(new int[]{y - 1, x - 1, d - 1, 0});
            acc[y - 1][x - 1][0] = i;
        }


        int answer = 1;
        while (answer <= 1000) {
            for (int i = 1; i < chessmen.size(); i++) {
                int[] chessman = chessmen.get(i);
                int y = chessman[0], x = chessman[1], d = chessman[2], accIdx = chessman[3];
                int yy = y + dy[d];
                int xx = x + dx[d];

                // 밖에 벗어날 경우 or blue
                if (yy < 0 || yy >= n || xx < 0 || xx >= n || board[yy][xx] == BLUE) {
                    d = reverseDir(d);
                    // 방향 바뀐거 업데이트 해줘야 한다.
                    chessman[2] = d;
                    yy = y + dy[d];
                    xx = x + dx[d];
                    if (yy < 0 || yy >= n || xx < 0 || xx >= n || board[yy][xx] == BLUE) continue;

                }


                ArrayList<Integer> temp = new ArrayList<>(3);

                // 현재 말이 쌓여있으면 temp에 저장
                for (int idx = accIdx; idx < 3; idx++) {
                    if (acc[y][x][idx] == 0) break;
                    temp.add(acc[y][x][idx]);
                    acc[y][x][idx] = 0;
                }

                // 4개 이상 쌓이면 종료
                int nextAccIdx = 3;
                for (int idx = 0; idx < 3; idx++) {
                    if (acc[yy][xx][idx] == 0) {
                        nextAccIdx = idx;
                        break;
                    }
                }
                if (nextAccIdx + temp.size() > 3) {
                    System.out.println(answer);
                    System.exit(0);
                }

                if (board[yy][xx] == WHITE) {

                    // 그대로 쌓는다.
                    for (int chessmanIdx : temp) {
                        chessmen.get(chessmanIdx)[0] = yy;
                        chessmen.get(chessmanIdx)[1] = xx;
                        chessmen.get(chessmanIdx)[3] = nextAccIdx;
                        acc[yy][xx][nextAccIdx] = chessmanIdx;
                        nextAccIdx++;
                    }
                } else if (board[yy][xx] == RED) {
                    for (int j = temp.size() - 1; j >= 0; j--) {
                        int chessmanIdx = temp.get(j);
                        chessmen.get(chessmanIdx)[0] = yy;
                        chessmen.get(chessmanIdx)[1] = xx;
                        chessmen.get(chessmanIdx)[3] = nextAccIdx;
                        acc[yy][xx][nextAccIdx] = chessmanIdx;
                        nextAccIdx++;
                    }
                }
            }
            answer++;
        }
        System.out.println(-1);
    }

    public static int reverseDir(int d) {
        if (d == 0) return 1;
        if (d == 1) return 0;
        if (d == 2) return 3;
        return 2;
    }
}
