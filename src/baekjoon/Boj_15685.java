package baekjoon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj_15685 {
    static int[] dy = {0, -1, 0, 1}, dx = {1, 0, -1, 0};
    static int[][] arr = new int[101][101];
    static double rad = 90 * (float) (Math.PI / 180);

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("res/boj15685.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            arr[y][x] = 1;
            int yy = y + dy[d];
            int xx = x + dx[d];
            arr[yy][xx] = 1;
            ArrayList<int[]> list = new ArrayList<>();
            list.add(new int[]{x, y});
            list.add(new int[]{xx, yy});
            makeCurve(xx, yy, d, g, list);
        }

        int cnt = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (arr[i][j] == 1 && arr[i + 1][j] == 1 && arr[i][j + 1] == 1 && arr[i + 1][j + 1] == 1) cnt++;
            }
        }

        System.out.println(cnt);
    }

    private static int[] transformation(int cx, int cy, int px, int py) {
        double rx = (px - cx) * Math.cos(rad) - (py - cy) * Math.sin(rad) + cx;
        double ry = (px - cx) * Math.sin(rad) + (py - cy) * Math.cos(rad) + cy;
        return new int[]{(int) Math.round(rx), (int) Math.round(ry)};
    }

    static void makeCurve(int x, int y, int d, int g, ArrayList<int[]> curves) {
        if (g == 0) {
            return;
        }
        int max = -1;
        int[] end = new int[2];
        int curDragonCurve = curves.size();
        for (int i = curDragonCurve - 1; i >= 0; i--) {
            int[] point = curves.get(i);
            if (point[0] == x && point[1] == y) continue;
            int[] curved = transformation(x, y, point[0], point[1]);
            curves.add(curved);
            arr[curved[1]][curved[0]] = 1;
            end[0] = curved[0];
            end[1] = curved[1];
        }

        makeCurve(end[0], end[1], d, g - 1, curves);

    }
}
