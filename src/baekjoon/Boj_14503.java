package baekjoon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Boj_14503 {
    static int CLEAN = -1, n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("res/boj14503.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int answer = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        loop:
        while (true) {
            if (arr[r][c] == 0) {
                arr[r][c] = CLEAN;
                answer++;
            }

            int initialDir = d;
            int cnt = 4;
            while (cnt > 0) {
                int[] left = getLeft(r, c, d);
                d = turnLeft(d);
                if (isValid(left) && arr[left[0]][left[1]] == 0) {
                    r = left[0];
                    c = left[1];
                    continue loop;
                }
                cnt--;
            }

            d = initialDir;
            int[] back = goBack(r, c, d);
            if (isValid(back) && arr[back[0]][back[1]] != 1) {
                r = back[0];
                c = back[1];
            } else break;

        }

        System.out.println(answer);
    }

    public static int[] goBack(int y, int x, int dir) {
        if (dir == 0) {
            return new int[]{y + 1, x};
        } else if (dir == 1) {
            return new int[]{y, x - 1};
        } else if (dir == 2) {
            return new int[]{y - 1, x};
        } else {
            return new int[]{y, x + 1};
        }
    }

    public static int turnLeft(int dir) {
        if (dir == 0) {
            return 3;
        } else if (dir == 1) {
            return 0;
        } else if (dir == 2) {
            return 1;
        } else {
            return 2;
        }
    }

    public static boolean isValid(int[] point) {

        if (point[0] < 0 || point[0] >= n || point[1] < 0 || point[1] >= m) return false;
        else return true;
    }

    public static int[] getLeft(int y, int x, int dir) {
        if (dir == 0) {
            return new int[]{y, x - 1};
        } else if (dir == 1) {
            return new int[]{y - 1, x};
        } else if (dir == 2) {
            return new int[]{y, x + 1};
        } else {
            return new int[]{y + 1, x};
        }
    }
}
