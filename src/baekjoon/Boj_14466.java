package baekjoon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_14466 {
    static int[] dy = {-1, 0, 1, 0}, dx = {0, 1, 0, -1};
    static boolean arr[][][], visited[][];
    static int n, cows[][];
    static Queue<int[]> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("res/boj14466.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int cnt = 0;

        arr = new boolean[n][n][4];
        cows = new int[k][2];
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int[] v1 = {Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1};
            int[] v2 = {Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1};
            for (int d = 0; d < 4; d++) {
                int yy = v1[0] + dy[d];
                int xx = v1[1] + dx[d];
                if (yy == v2[0] && xx == v2[1]) {
                    arr[v1[0]][v1[1]][d] = true;
                    arr[v2[0]][v2[1]][(d + 2) % 4] = true;
                    break;
                }
            }
        }

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            cows[i][0] = Integer.parseInt(st.nextToken()) - 1;
            cows[i][1] = Integer.parseInt(st.nextToken()) - 1;
        }

        for (int i = 0; i < k - 1; i++) {
            bfs(cows[i]);
            for (int j = i + 1; j < k; j++) {
                if (!visited[cows[j][0]][cows[j][1]]) {
                    cnt++;
                }
            }
        }

        System.out.println(cnt);
    }

    static void bfs(int[] from) {
        visited = new boolean[n][n];
        q = new LinkedList<>();
        q.add(from);
        visited[from[0]][from[1]] = true;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int d = 0; d < 4; d++) {
                int yy = cur[0] + dy[d];
                int xx = cur[1] + dx[d];
                if (yy < 0 || yy >= n || xx < 0 || xx >= n || visited[yy][xx] || arr[cur[0]][cur[1]][d]) continue;
                visited[yy][xx] = true;
                q.add(new int[]{yy, xx});
            }
        }
    }
}
