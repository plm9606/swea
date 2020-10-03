package baekjoon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Boj_19236 {
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1}, dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int max = 0, SHARK = 17;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("res/boj19236.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] arr = new int[4][4];
        Fish[] fishes = new Fish[18];
        StringTokenizer st;
        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                fishes[a] = new Fish(i, j, b - 1);
                arr[i][j] = a;
            }
        }


        int initialCount = arr[0][0];
        fishes[initialCount].alive = false;
        Fish initial = fishes[initialCount];

        Fish shark = new Fish(0, 0, initial.d);
        max += arr[0][0];
        arr[0][0] = SHARK;
        fishes[SHARK] = shark;

        solution(initialCount, arr, fishes);
        System.out.println(max);
    }

    public static void solution(int count, int[][] arr, Fish[] fishes) {
        // 물고기 이동
        for (int i = 1; i <= 16; i++) {
            if (!fishes[i].alive) continue;
            move(fishes[i].d, fishes[i].y, fishes[i].x, i, 0, arr, fishes);
        }

        // 상어가 물고기를 먹을 수 있는지 체쿠
        boolean flag = false;

        int yy = fishes[SHARK].y + dy[fishes[SHARK].d];
        int xx = fishes[SHARK].x + dx[fishes[SHARK].d];

        while (check(yy, xx)) {
            // 빈칸이거나 자기 자신으로 이동한다면
            if (arr[yy][xx] == 0 || arr[yy][xx] == SHARK) {
                yy += dy[fishes[SHARK].d];
                xx += dx[fishes[SHARK].d];
                continue;
            }

            flag = true;
            int food = arr[yy][xx];
//            Fish temp = fishes[SHARK];  불가
            // 레퍼런스복사 주의!
            int originX = fishes[SHARK].x, originY = fishes[SHARK].y, originD = fishes[SHARK].d;
            // 먹이 죽음
            fishes[food].alive = false;

            // 먹이의 방향 갖는다.
            fishes[SHARK].d = fishes[food].d;
            arr[originY][originX] = 0;
            arr[yy][xx] = SHARK;
            fishes[SHARK].y = yy;
            fishes[SHARK].x = xx;

            int[][] cpy = new int[4][4];
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    cpy[i][j] = arr[i][j];
                }
            }
            Fish[] cp_fishes = new Fish[18];
            for (int i = 1; i < 18; i++) {

                // cp_fishes[i]=fishes[i]로 하게되면 레퍼런스 복사가 되어서 복사하는 의미 없음.
                // fish의 alive값을 함께 복사해주지 않아서 문제를 계속 틀렸음
                cp_fishes[i] = new Fish(fishes[i].y, fishes[i].x, fishes[i].d, fishes[i].alive);
            }
            solution(count + food, cpy, cp_fishes);

            fishes[food].alive = true;
            arr[originY][originX] = SHARK;
            arr[yy][xx] = food;
            fishes[SHARK].d = originD;
            fishes[SHARK].y = originY;
            fishes[SHARK].x = originX;

            yy += dy[fishes[SHARK].d];
            xx += dx[fishes[SHARK].d];
        }

        // 더이상 먹을 물고기가 없으면
        if (!flag) {
            max = Math.max(max, count);
            return;
        }
    }

    public static boolean check(int y, int x) {
        return y >= 0 && y < 4 && x >= 0 && x < 4;
    }

    public static void move(int d, int y, int x, int fishIdx, int initialDir, int[][] arr, Fish[] fishes) {
        // 모든 방향 적용해도 안될경우 멈춘다.
        if (8 == initialDir) return;

        int yy = y + dy[d];
        int xx = x + dx[d];

        // 범위 벗어나거나 상어인 경우
        if (!check(yy, xx) || arr[yy][xx] == SHARK) {
            move((d + 1) % 8, y, x, fishIdx, initialDir + 1, arr, fishes);
        } else {
            fishes[fishIdx].d = d;
            // 빈칸일경우
            if (arr[yy][xx] == 0) {
                arr[yy][xx] = fishIdx;
                arr[y][x] = 0;
                fishes[fishIdx].y = yy;
                fishes[fishIdx].x = xx;
            } else {
                int changeIdx = arr[yy][xx];
                arr[y][x] = changeIdx;
                fishes[changeIdx].y = y;
                fishes[changeIdx].x = x;

                arr[yy][xx] = fishIdx;
                fishes[fishIdx].y = yy;
                fishes[fishIdx].x = xx;
            }
        }
    }
}

class Fish {
    int y, x, d;
    boolean alive;

    public Fish(int y, int x, int d) {
        this.y = y;
        this.x = x;
        this.d = d;
        alive = true;
    }

    public Fish(int y, int x, int d, boolean alive) {
        this.y = y;
        this.x = x;
        this.d = d;
        this.alive = alive;
    }
}
