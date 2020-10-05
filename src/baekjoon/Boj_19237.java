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


        while (sharkPoint.size() > 1 || time > 1000) {
            HashSet<Integer> removeList = new HashSet<>();
            loop:
            for (Shark3 shark : sharkPoint) {
                int[] priority = sharkDir[shark.idx - 1][shark.dir];
                int[] point = new int[2];
                int moveDir = 0;
                for (int i = 0; i < 4; i++) {
                    moveDir = priority[i];
                    int yy = shark.y + dy[moveDir];
                    int xx = shark.x + dx[moveDir];

                    if (yy < 0 || yy >= n || xx < 0 || xx >= n) continue;
                    // 냄새 없는 칸
                    if (arr[yy][xx].time < time) {
                        //go
                        point[0] = yy;
                        point[1] = xx;
                        break;
                    }
                    // 지금 회차에 뿌려진 냄새라면 인덱스 비교
                    if (arr[yy][xx].time == (time + k)) {
                        // 현재 상어 번호가 더 클 경우
                        if (arr[yy][xx].shark < shark.idx) {
                            removeList.add(shark.idx);
                            continue loop;
                        }
                        removeList.add(arr[yy][xx].shark);
                        point[0] = yy;
                        point[1] = xx;
                        break;
                    }
                    // 자기 자신의 냄새 임시 저장
                    if (arr[yy][xx].shark == shark.idx) {
                        point[0] = yy;
                        point[1] = xx;
                    }
                }

                arr[point[0]][point[1]].shark = shark.idx;
                arr[point[0]][point[1]].time = time + k;
                shark.y = point[0];
                shark.x = point[1];
                shark.dir = moveDir;
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

        System.out.println(time);
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