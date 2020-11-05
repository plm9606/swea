package baekjoon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_2638 {
    static int arr[][], dy[] = {0, 1, 0, -1}, dx[] = {1, 0, -1, 0}, n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("res/boj2638.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        boolean flag = true;
        int[] initial = new int[2];
        Queue<int[]> cheeses = new LinkedList<>();
        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 1) {
                    cheeses.add(new int[]{i, j});
                }

                if (flag && arr[i][j] == 0) {
                    initial = new int[]{i, j};
                    flag = false;
                }
            }
        }

        // initialize
        Queue<int[]> q = new LinkedList<>();
        q.add(initial);
        arr[initial[0]][initial[1]] = -1;
        while (!q.isEmpty()) {
            int[] point = q.poll();
            for (int d = 0; d < 4; d++) {
                int yy = point[0] + dy[d];
                int xx = point[1] + dx[d];
                if (yy < 0 || yy >= n || xx < 0 || xx >= m || arr[yy][xx] != 0) continue;
                arr[yy][xx] = -1;
                q.add(new int[]{yy, xx});
            }
        }


        int cnt = 0;
        while (true) {
            cnt++;
            LinkedList<int[]> next = new LinkedList<>();
            LinkedList<int[]> removed = new LinkedList<>();
            while (!cheeses.isEmpty()) {
                int[] cheese = cheeses.poll();

                int air = 0;
                for (int d = 0; d < 4; d++) {
                    if (air == 2) break;
                    int yy = cheese[0] + dy[d];
                    int xx = cheese[1] + dx[d];
                    if (yy < 0 || yy >= n || xx < 0 || xx >= m || arr[yy][xx] != -1) continue;
                    air++;
                }

                if (air == 2) {
                    removed.add(cheese);
                } else {
                    next.add(cheese);
                }
            }
            cheeses = next;

            bfs(removed);
            if (cheeses.isEmpty()) break;
        }

        System.out.println(cnt);
    }


    static void bfs(Queue<int[]> q) {
//        Queue<int[]> q = points;
        while (!q.isEmpty()) {
            int[] p = q.poll();
            arr[p[0]][p[1]] = -1;
            for (int d = 0; d < 4; d++) {
                int yy = p[0] + dy[d];
                int xx = p[1] + dx[d];
                if (yy < 0 || yy >= n || xx < 0 || xx >= m || arr[yy][xx] != 0) continue;
                arr[yy][xx] = -1;
                q.add(new int[]{yy, xx});
            }
        }
    }
}
