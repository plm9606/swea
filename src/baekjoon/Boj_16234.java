package baekjoon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_16234 {
    static int[] p, dy = {1, 0, -1, 0}, dx = {0, 1, 0, -1};
    static int N, l, r, answer = 0;
    static int[][] land;
    static boolean[][] visited;
    static boolean reArrange;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("res/boj16234.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        p = new int[N * N];
        for (int i = 0; i < N * N; i++) {
            p[i] = i;
        }

        land = new int[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                land[i][j] = Integer.parseInt(st.nextToken());
        }

        boolean retry = false;
        do {
            retry = false;
            visited = new boolean[N][N];
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x++) {
                    if (!visited[y][x])
                        if (move(y, x)) retry = true;
                }
            }
            if (retry) answer++;
        } while (retry);


//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < N; j++) {
//                int cur = land[i][j];
//                if (j + 1 < N) {
//                    int rightDiff = Math.abs(land[i][j + 1] - cur);
//                    if (rightDiff >= l && rightDiff <= r) {
//                        union(getIdx(i, j), getIdx(i, j + 1));
//                    }
//
//                }
//                if (i + 1 < N) {
//                    int downDiff = Math.abs(land[i + 1][j] - cur);
//                    if (downDiff >= l && downDiff <= r) {
//                        union(getIdx(i, j), getIdx(i + 1, j));
//                    }
//                }
//            }
//        }

        System.out.println(answer);
    }

    static private boolean move(int y, int x) {
        reArrange = false;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{y, x});
        visited[y][x] = true;

        int acc = land[y][x];
        int unionCnt = 1;
        ArrayList<int[]> union = new ArrayList<>();
        union.add(new int[]{y, x});

        while (!q.isEmpty()) {
            int[] p = q.poll();
            int i = p[0], j = p[1];
            int cur = land[i][j];
            visited[i][j] = true;
            for (int d = 0; d < 4; d++) {
                int yy = i + dy[d];
                int xx = j + dx[d];
                if (yy >= 0 && yy < N && xx >= 0 && xx < N && !visited[yy][xx]) {
                    int diff = Math.abs(land[yy][xx] - cur);
                    if (diff >= l && diff <= r) {
                        q.add(new int[]{yy, xx});
                        visited[yy][xx] = true;
                        acc += land[yy][xx];
                        unionCnt++;
                        union.add(new int[]{yy, xx});
                        reArrange = true;
                    }
                }
            }
        }

        if (reArrange) {
            int movedPopulation = acc / unionCnt;
            for (int[] nation : union) {
                land[nation[0]][nation[1]] = movedPopulation;
            }
            return true;
        }
        return false;
    }

    static private int getIdx(int y, int x) {
        return y * N + x;
    }

    static private void union(int a, int b) {
        a = find(a);
        b = find(b);

        p[a] = b;
    }

    static private int find(int a) {
        if (p[a] == a) return a;
        p[a] = find(p[a]);
        return p[a];
    }
}
