package baekjoon;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_11967 {
    static int arr[][], n, dy[] = {0, 1, 0, -1}, dx[] = {1, 0, -1, 0}, cnt = 1;
    static Queue<Point> q = new LinkedList<>();
    static HashMap<Point, LinkedList<Point>> map;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        map = new HashMap<>();
        arr = new int[n][n];
        arr[0][0] = 1;

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            Point from = new Point(x, y);
            Point to = new Point(b, a);

            if (map.containsKey(from)) {
                map.get(from).add(to);
            } else {
                LinkedList<Point> list = new LinkedList<>();
                list.add(to);
                map.put(from, list);
            }
        }

        int before_cnt = 0;
        // 더이상 새로 킬 불이 없을 때 까지
        do {
            before_cnt = cnt;
            bfs();
        } while (cnt != before_cnt);

        System.out.println(cnt);
    }

    static void turnOnLight(Point p) {
        if (!map.containsKey(p)) return;
        for (Point next : map.get(p)) {
            if (arr[next.y][next.x] == 0) {
                arr[next.y][next.x] = 1;
                cnt++;
            }
        }
    }

    static void bfs() {
        boolean[][] visited = new boolean[n][n];
        visited[0][0] = true;
        q.add(new Point(0, 0));
        while (!q.isEmpty()) {
            Point p = q.poll();
            turnOnLight(p);

            for (int d = 0; d < 4; d++) {
                int yy = p.y + dy[d];
                int xx = p.x + dx[d];
                if (yy < 0 || yy >= n || xx < 0 || xx >= n || visited[yy][xx] || arr[yy][xx] != 1) continue;
                visited[yy][xx] = true;
                q.add(new Point(xx, yy));
            }
        }
    }
}
