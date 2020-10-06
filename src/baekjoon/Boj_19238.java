package baekjoon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_19238 {
    static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1}, taxi;
    static int[][] arr, destination;
    static int n, m, gas, d;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("res/boj19238.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        gas = Integer.parseInt(st.nextToken());
        arr = new int[n][n];
        destination = new int[m + 1][2];
        taxi = new int[2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 1) arr[i][j] = -1;
            }

        }

        st = new StringTokenizer(br.readLine());
        taxi[0] = Integer.parseInt(st.nextToken()) - 1;
        taxi[1] = Integer.parseInt(st.nextToken()) - 1;

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int rr = Integer.parseInt(st.nextToken()) - 1;
            int cc = Integer.parseInt(st.nextToken()) - 1;
            arr[r][c] = i + 1;
            destination[i + 1] = new int[]{rr, cc};
        }

        for (int i = 0; i < m; i++) {
            passenger();
            dest();
            System.out.println(gas);

        }
        System.out.println(gas);
    }

    static public void passenger() {
        boolean[][] visited = new boolean[n][n];
        Queue<Path2> q = new LinkedList<>();

        int[] passenger = {100, 100};
        int minDist = Integer.MAX_VALUE;

        q.add(new Path2(taxi[0], taxi[1], 0));
        while (!q.isEmpty()) {
            Path2 point = q.poll();
            if (point.dist > minDist) continue;
            // 승객이 있는 좌표라면
            if (arr[point.y][point.x] > 0) {
                // 최단 거리가 같을 경우
                if (minDist == point.dist) {
                    if (point.y == passenger[0]) {
                        if (point.x < passenger[1]) {
                            passenger[0] = point.y;
                            passenger[1] = point.x;
                        }
                    } else if (point.y < passenger[0]) {
                        passenger[0] = point.y;
                        passenger[1] = point.x;
                    }
                } else if (minDist > point.dist) {
                    passenger[0] = point.y;
                    passenger[1] = point.x;
                    minDist = point.dist;
                }
                continue;
            }
            for (int i = 0; i < 4; i++) {
                int yy = point.y + dy[i];
                int xx = point.x + dx[i];
                if (yy < 0 || yy >= n || xx < 0 || xx >= n || visited[yy][xx] || arr[yy][xx] == -1) continue;
                q.add(new Path2(yy, xx, point.dist + 1));
                visited[yy][xx] = true;
            }
        }

        if (gas < minDist) {
            System.out.println(-1);
            System.exit(0);
        }
        System.out.println("goto: " + arr[passenger[0]][passenger[1]]);
        gas -= minDist;
        taxi[0] = passenger[0];
        taxi[1] = passenger[1];
        d = arr[passenger[0]][passenger[1]];
        arr[passenger[0]][passenger[1]] = 0;
    }

    static public void dest() {
        boolean[][] visited = new boolean[n][n];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{taxi[0], taxi[1], 0});
        boolean find = false;
        while (!q.isEmpty()) {
            int[] point = q.poll();
            if (point[0] == destination[d][0] && point[1] == destination[d][1]) {
                if (gas < point[2]) {
                    System.out.println(-1);
                    System.exit(0);
                }
                find = true;
                gas += point[2];
                taxi[0] = point[0];
                taxi[1] = point[1];
                break;
            }
            for (int i = 0; i < 4; i++) {
                int yy = point[0] + dy[i];
                int xx = point[1] + dx[i];
                if (yy < 0 || yy >= n || xx < 0 || xx >= n || visited[yy][xx] || arr[yy][xx] == -1) continue;
                q.add(new int[]{yy, xx, point[2] + 1});
                visited[yy][xx] = true;
            }
        }

        // 목적지에 갈 수 없는 경우
        if (!find) {
            System.out.println(-1);
            System.exit(0);
        }
    }
}

class Path2 implements Comparable<Path2> {
    int y, x, dist;

    public Path2(int y, int x, int dist) {
        this.y = y;
        this.x = x;
        this.dist = dist;
    }

    @Override
    public int compareTo(Path2 o) {
        if (this.dist == o.dist) {
            if (this.y == o.y) {
                return Integer.compare(this.x, o.x);
            }
            return Integer.compare(this.y, o.y);
        }
        return Integer.compare(this.dist, o.dist);
    }
}