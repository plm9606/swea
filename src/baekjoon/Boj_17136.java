package baekjoon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj_17136 {
    static int[][] paper;
    static int[] slice;
    static int min = Integer.MAX_VALUE, toFind;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("res/boj17136.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        paper = new int[10][10];
        ArrayList<int[]> list = new ArrayList<>();
        slice = new int[]{0, 5, 5, 5, 5, 5};
        toFind = 0;
        StringTokenizer st;
        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
                if (paper[i][j] == 1) {
                    list.add(new int[]{i, j});
                    toFind++;
                }
            }
        }

        if (toFind == 0) {
            System.out.println(0);
            return;
        } else if (toFind == 100) {
            System.out.println(4);
            return;
        }

        dfs(0, 0, 0, 0);

        if (min == Integer.MAX_VALUE) min = -1;
        System.out.println(min);
    }

    static private void dfs(int y, int x, int cnt, int pasted) {
        if (x == 10) {
            dfs(y + 1, 0, cnt, pasted);
            return;
        }
        if (y == 10) {
            if (pasted == toFind && min > cnt) min = cnt;
            return;
        }
        if (paper[y][x] == 0) {
            dfs(y, x + 1, cnt, pasted);
        }

        if (cnt > min) return;

        for (int width = 5; width >= 1; width--) {
            // 색종이를 다 쓴 경우
            if (slice[width] == 0) continue;
            // 색종이가 범위 벗어날 경우
            if (y + width > 10 || x + width > 10) continue;

            boolean flag = true;
            loop:
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < width; j++) {
                    if (paper[i + y][j + x] == 0) {
                        flag = false;
                        break loop;
                    }
                }
            }
            // 색종이를 붙일 수 있다면
            if (flag) {
                // 색종이 부착
                for (int i = 0; i < width; i++) {
                    for (int j = 0; j < width; j++) {
                        paper[i + y][j + x] = 0;
                    }
                }
                slice[width] -= 1;

                dfs(y, x + width, cnt + 1, pasted + (width * width));

                // 색종이 떼기
                for (int i = 0; i < width; i++) {
                    for (int j = 0; j < width; j++) {
                        paper[i + y][j + x] = 1;
                    }
                }
                slice[width] += 1;
            }
        }
    }

}
