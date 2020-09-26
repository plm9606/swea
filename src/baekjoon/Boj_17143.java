package baekjoon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Boj_17143 {
    static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("res/boj17143.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int sum = 0;
        int[][] arr = new int[R][C];
        ArrayList<Shark2> sharks = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken());
            sharks.add(new Shark2(r, c, s, d, z));
            arr[r][c] = z;
        }

        for (int col = 0; col < C; col++) {
            for (int y = 0; y < R; y++) {
                if (arr[y][col] > 0) {
                    sum += arr[y][col];
                    arr[y][col] = 0;
                    break;
                }
            }

            // 상어 이동
            for (Shark2 shark : sharks) {
                // 잡힌 상어
                if (arr[shark.y][shark.x] != shark.size) {
                    shark.alive = false;
                    continue;
                }

                int yy = shark.y;
                int xx = shark.x;
                for (int sec = 0; sec < shark.speed; sec++) {
                    yy += dy[shark.d];
                    xx += dx[shark.d];
                    // 밖을 벗어나면
                    if (yy < 0 || yy >= R || xx < 0 || xx >= C) {
                        yy -= dy[shark.d];
                        xx -= dx[shark.d];
                        shark.d = convertDir(shark.d);
                        yy += dy[shark.d];
                        xx += dx[shark.d];
                    }
                }

                // 원래 있던 곳 지운다.
                arr[shark.y][shark.x] = 0;
                shark.y = yy;
                shark.x = xx;
            }

            for (Iterator<Shark2> itt = sharks.iterator(); itt.hasNext(); ) {
                Shark2 s = itt.next();
                // 죽었거나 이미 움직인 상어보다 몸집이 작으면
                if (!s.alive || arr[s.y][s.x] > s.size) {
                    itt.remove();
                } else {
                    arr[s.y][s.x] = s.size;
                }
            }

        }

        System.out.println(sum);
    }

    static public int convertDir(int d) {
        if (d == 1) return 0;
        else if (d == 0) return 1;
        else if (d == 2) return 3;
        return 2;
    }
}

class Shark2 {
    int y, x, speed, d, size;
    boolean alive;

    public Shark2(int y, int x, int speed, int d, int size) {
        this.y = y;
        this.x = x;
        this.speed = speed;
        this.d = d;
        this.size = size;
        this.alive = true;
    }
}
