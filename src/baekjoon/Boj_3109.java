package baekjoon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Boj_3109 {
    static int[][] arr;
    static int r, c, max = 0;
    static int[] dy = {-1, 0, 1}, dx = {1, 1, 1};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("res/boj3109"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        arr = new int[r][c];
        visited = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            String row = br.readLine();
            for (int j = 0; j < c; j++) {
                if (row.charAt(j) == 'x') arr[i][j] = 1;
            }
        }

        for (int i = 0; i < r; i++)
            dfs(i, 0, 0);
        System.out.println(max);
    }

    static boolean dfs(int y, int x, int cnt) {
        // 남의 빵집에 다다른 경우
        if (x == c - 1) {
            max++;
            return true;
        }

        for (int d = 0; d < 3; d++) {
            int yy = y + dy[d];
            int xx = x + dx[d];
            if (yy < 0 || yy >= r || xx < 0 || xx >= c || visited[yy][xx] || arr[yy][xx] == 1) continue;
            visited[yy][xx] = true;
            if (dfs(yy, xx, cnt)) return true;
        }
        return false;
    }
}
