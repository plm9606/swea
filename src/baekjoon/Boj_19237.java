package baekjoon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Boj_19237 {
    static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("res/boj19237.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        Smell[][] arr = new Smell[n][n];
        Shark3[] sharks = new Shark3[m];
        int[][][] sharkDir = new int[m][4][4];


        int time = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num != 0) {
//                    sharkPoint.add(new Shark3(num, i, j));
                    sharks[num - 1] = new Shark3(num, i, j);
                    arr[i][j] = new Smell(num, k);
                } else arr[i][j] = new Smell(0, 0);
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            sharks[i].dir = Integer.parseInt(st.nextToken()) - 1;
        }

        time++;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < 4; j++) {
                st = new StringTokenizer(br.readLine());
                for (int l = 0; l < 4; l++)
                    sharkDir[i][j][l] = Integer.parseInt(st.nextToken()) - 1;
            }
        }

        LinkedList<Shark3> sharkPoint = new LinkedList<>(Arrays.asList(sharks));


        while (sharkPoint.size() > 1) {
            if (time > 1000) {
                System.out.println(-1);
                System.exit(0);
            }
            HashSet<Integer> removeList = new HashSet<>();
            ArrayList<int[]> move = new ArrayList<>();

            loop:
            for (Shark3 shark : sharkPoint) {
                int[] priority = sharkDir[shark.idx - 1][shark.dir];
                int[] point = new int[2];
                int moveDir = 0;
                boolean save = false;
                for (int i = 0; i < 4; i++) {
                    int d = priority[i];
                    int yy = shark.y + dy[d];
                    int xx = shark.x + dx[d];

                    if (yy < 0 || yy >= n || xx < 0 || xx >= n) continue;
                    // 냄새 없는 칸
                    if (arr[yy][xx].time < time) {
                        //go
                        point[0] = yy;
                        point[1] = xx;
                        moveDir = d;

                        break;
                    }
                    // 자기 자신의 냄새 임시 저장
                    if (!save && arr[yy][xx].shark == shark.idx) {
                        point[0] = yy;
                        point[1] = xx;
                        moveDir = d;
                        save = true;
                    }
                }
                shark.y = point[0];
                shark.x = point[1];
                shark.dir = moveDir;

                move.add(new int[]{shark.idx, point[0], point[1]});
            }

            // 모두 움직인 후 같은 위치에 있는 상어 제거 및 arr 업데이트
            // 미리 arr값을 조정하면 빈칸으로 취급되지 않기 때문에 모두 움직인 후에 값 업데이트해줘야 한다.
            // idx, yy, xx
            for (int[] query : move) {
                if (arr[query[1]][query[2]].time == (time + k)) {
                    // 현재 상어 번호가 더 클 경우
                    if (arr[query[1]][query[2]].shark < query[0]) {
                        removeList.add(query[0]);
                        continue;
                    }
                    removeList.add(arr[query[1]][query[2]].shark);
                }
                arr[query[1]][query[2]].shark = query[0];
                arr[query[1]][query[2]].time = time + k;
            }

            for (Iterator<Shark3> itt = sharkPoint.iterator(); itt.hasNext(); ) {
                Shark3 s = itt.next();
                if (removeList.contains(s.idx)) {
                    removeList.remove(s.idx);
                    itt.remove();
                }
            }

            time++;
        }

        System.out.println(time - 1);
    }
}

class Shark3 {
    int idx, y, x, dir;

    public Shark3(int idx, int y, int x) {
        this.idx = idx;
        this.y = y;
        this.x = x;
    }
}

class Smell {
    int shark, time;

    public Smell(int shark, int time) {
        this.shark = shark;
        this.time = time;
    }
}