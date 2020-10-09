package baekjoon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Boj_17135 {
    static LinkedList<int[]> enemies;
    static int N, M, D, max = Integer.MIN_VALUE;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("res/boj17135.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        enemies = new LinkedList<>();
        arr = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 1) {
                    enemies.add(new int[]{i, j, 1});
                }
            }
        }

        combination(new boolean[M], 3, 0);
        System.out.println(max);
    }

    public static void combination(boolean[] bit, int toChoice, int target) {
        if (toChoice == 0) {
            int[] archers = new int[3];
            int idx = 0;
            for (int i = 0; i < bit.length; i++) {
                if (bit[i]) archers[idx++] = i + 1;
            }
            battle(archers);
            return;
        }

        for (int i = target; i < bit.length; i++) {
            bit[i] = true;
            combination(bit, toChoice - 1, i + 1);
            bit[i] = false;
        }
    }

    public static void battle(int[] archers) {
        int cnt = 0;
        LinkedList<int[]> cpy = new LinkedList<>();
        for (int[] e : enemies) {
            cpy.add(new int[]{e[0], e[1], e[2]});
        }

        while (cpy.size() > 0) {
            int[][] target = {{-1, -1, Integer.MAX_VALUE}, {-1, -1, Integer.MAX_VALUE}, {-1, -1, Integer.MAX_VALUE}};

            // 남아있는 적들과 궁수와의 거리 구한다.
            for (int i = 0; i < cpy.size(); i++) {
                // y, x, alive
                int[] enemy = cpy.get(i);
                for (int a = 0; a < 3; a++) {
                    int len = Math.abs(enemy[0] - (N + 1)) + Math.abs(enemy[1] - archers[a]);
                    // 거리가 d 이하
                    if (len <= D) {
                        // 거리가 저장한 거리보다 작거나
                        // 같다면 더 왼쪽에 있는 경우
                        if (len < target[a][2] || (len == target[a][2] && enemy[1] < target[a][1])) {
                            target[a][0] = i;
                            target[a][1] = enemy[1];
                            target[a][2] = len;
                        }
                    }
                }
            }

            for (int i = 0; i < 3; i++) {
                if (target[i][0] == -1) continue;
                cpy.get(target[i][0])[2] = 0;
            }

            // 죽은 병사 혹은 성에 침투한 병사 지운다
            for (Iterator<int[]> itt = cpy.iterator(); itt.hasNext(); ) {
                int[] e = itt.next();
                if (e[2] == 0) {
                    itt.remove();
                    cnt++;
                } else {
                    e[0] += 1;
                    if (e[0] == N + 1) itt.remove();
                }
            }
        }

        max = Math.max(max, cnt);
    }
}