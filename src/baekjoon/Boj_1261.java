package baekjoon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj_1261 {
    static int[] dy = {0, 1, 0, -1}, dx = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("res/boj1261.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            String row = br.readLine();
            for (int j = 0; j < m; j++)
                arr[i][j] = row.charAt(j) - '0';
        }


        int[][] dist = new int[n][m];
        for (int[] row : dist)
            Arrays.fill(row, Integer.MAX_VALUE);

        boolean[][] visited = new boolean[n][m];
        PriorityQueue<Algo> q = new PriorityQueue<>();
        q.add(new Algo(0, 0));
        dist[0][0] = 0;
        visited[0][0] = true;
        while (!q.isEmpty()) {
            Algo a = q.poll();

            if (a.y == n - 1 && a.x == m - 1) {
                continue;
            }
            for (int d = 0; d < 4; d++) {
                int yy = a.y + dy[d];
                int xx = a.x + dx[d];

                if (yy < 0 || yy >= n || xx < 0 || xx >= m) continue;

                if (dist[yy][xx] > a.cnt + arr[yy][xx]) {
                    visited[yy][xx] = true;
                    dist[yy][xx] = a.cnt + arr[yy][xx];
                    q.add(new Algo(yy, xx, a.cnt + arr[yy][xx]));
                }
            }

        }

        System.out.println(dist[n - 1][m - 1]);
    }
}

class Algo implements Comparable<Algo> {
    int y, x, cnt;

    public Algo(int y, int x) {
        this.y = y;
        this.x = x;
        this.cnt = 0;
    }

    public Algo(int y, int x, int cnt) {
        this.y = y;
        this.x = x;
        this.cnt = cnt;
    }

    @Override
    public int compareTo(Algo o) {
        return Integer.compare(this.cnt, o.cnt);
    }
}
