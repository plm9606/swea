package baekjoon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_17070 {
    static int[][] arr;
    static int N, ans = 0;
    static int[] dy = {0, 1, 1}, dx = {1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("res/boj17070.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, 0, 1, 0);
        System.out.println(ans);
    }

    public static void dfs(int y1, int x1, int y2, int x2, int d) {
        if ((y1 == N - 1 && x1 == N - 1) || (y2 == N - 1 && x2 == N - 1)) {
            ans++;
            return;
        }
        LinkedList<Pipe2> list = move(new Pipe2(y1, x1, y2, x2, d));
        for (Pipe2 p : list) {
            if (check(p.y1, p.x1) && check(p.y2, p.x2)) {
                dfs(p.y1, p.x1, p.y2, p.x2, p.d);
            }
        }
    }

    public static void bfs() {
        Queue<Pipe2> q = new LinkedList<>();
        q.add(new Pipe2(0, 0, 0, 1, 0));

        while (!q.isEmpty()) {
            Pipe2 p = q.poll();
            if (!check(p.y1, p.x1) && !check(p.y2, p.x2)) {
                continue;
            }
            if ((p.y1 == N - 1 && p.x1 == N - 1) || (p.y2 == N - 1 && p.x2 == N - 1)) {
                ans++;
//                System.out.println(p.toString());
                continue;
            }

//            visited[p.y2][p.x2][p.d] = true;
            LinkedList<Pipe2> list = move(p);
            q.addAll(list);
        }

    }

    private static boolean check(int y, int x) {
        return (y >= 0 && y < N && x >= 0 && x < N);
    }

    private static boolean checkDir2(int y, int x) {
        for (int i = 0; i < 3; i++) {
            int yy = y + dy[i];
            int xx = x + dx[i];
            if (arr[yy][xx] != 0) return false;
        }
        return true;
    }

    public static LinkedList<Pipe2> move(Pipe2 p) {
        LinkedList<Pipe2> list = new LinkedList<>();

        if (p.d == 0) {
            if (p.x2 + 1 < N && arr[p.y2][p.x2 + 1] == 0) {
                list.add(new Pipe2(p.y2, p.x2, p.y2, p.x2 + 1, p.d));
            }
            if (p.x2 + 1 < N && p.y2 + 1 < N && checkDir2(p.y2, p.x2)) {
                list.add(new Pipe2(p.y2, p.x2, p.y2 + 1, p.x2 + 1, 2));
            }
        } else if (p.d == 1) {
            if (p.y2 + 1 < N && arr[p.y2 + 1][p.x2] == 0) {
                list.add(new Pipe2(p.y2, p.x2, p.y2 + 1, p.x2, p.d));
            }
            if (p.y2 + 1 < N && p.x2 + 1 < N && checkDir2(p.y2, p.x2)) {
                list.add(new Pipe2(p.y2, p.x2, p.y2 + 1, p.x2 + 1, 2));
            }
        } else {
            if (p.x2 + 1 < N && arr[p.y2][p.x2 + 1] == 0) {
                list.add(new Pipe2(p.y2, p.x2, p.y2, p.x2 + 1, 0));
            }
            if (p.y2 + 1 < N && arr[p.y2 + 1][p.x2] == 0) {
                list.add(new Pipe2(p.y2, p.x2, p.y2 + 1, p.x2, 1));
            }
            if (p.x2 + 1 < N && p.y2 + 1 < N && checkDir2(p.y2, p.x2)) {
                list.add(new Pipe2(p.y2, p.x2, p.y2 + 1, p.x2 + 1, p.d));
            }
        }

        return list;
    }
}

class Pipe2 {
    int y1, x1, y2, x2, d;

    public Pipe2(int y1, int x1, int y2, int x2, int d) {
        this.y1 = y1;
        this.x1 = x1;
        this.y2 = y2;
        this.x2 = x2;
        this.d = d;
    }

}