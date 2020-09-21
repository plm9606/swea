package baekjoon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Boj_15686 {
    static int[][] city;
    static int homeCnt, min = Integer.MAX_VALUE, n;
    static int[] dy = {0, 1, 0, -1}, dx = {1, 0, -1, 0};
    static ArrayList<int[]> homes, store;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("res/boj15686.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        city = new int[n][n];

        homes = new ArrayList<>();
        store = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int t = Integer.parseInt(st.nextToken());
                if (t == 1) {
                    city[i][j] = 1;
                    homes.add(new int[]{i, j});
                } else if (t == 2) store.add(new int[]{i, j});
            }
        }

        homeCnt = homes.size();

        combination(new boolean[store.size()], m, 0);

        System.out.println(min);
    }

    public static void combination(boolean[] bit, int toChoice, int target) {
        if (toChoice == 0) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < bit.length; i++) {
                if (bit[i]) {
                    list.add(i);
                }
            }

            find(list);
            return;
        }

        for (int i = target; i < bit.length; i++) {
            bit[i] = true;
            combination(bit, toChoice - 1, i + 1);
            bit[i] = false;
        }
    }

    public static void find(ArrayList<Integer> list) {
        Queue<Chicken> q = new LinkedList<>();
        int cnt = 0;
        int[][] cpy = new int[city.length][city.length];
        for (int i = 0; i < city.length; i++) {
            cpy[i] = Arrays.copyOf(city[i], city.length);
        }

        for (int s : list) {
            int[] storePoint = store.get(s);

            for (int i = 0; i < 4; i++) {
                int yy = storePoint[0] + dy[i];
                int xx = storePoint[1] + dx[i];
                if (yy >= 0 && yy < n && xx >= 0 && xx < n) {
                    q.add(new Chicken(storePoint[0], storePoint[1], yy, xx));
                }
            }
        }
        int sum = 0;
        while (!q.isEmpty()) {
            if (sum >= min) break;
            if (cnt == homeCnt) {
                min = Math.min(sum, min);
                break;
            }
            Chicken c = q.poll();
            if (cpy[c.y][c.x] == 5) continue;
            if (cpy[c.y][c.x] == 1) {
                sum += (Math.abs(c.y - c.from_y) + Math.abs(c.x - c.from_x));
                cnt++;
            }
            cpy[c.y][c.x] = 5;

            for (int i = 0; i < 4; i++) {
                int yy = c.y + dy[i];
                int xx = c.x + dx[i];
                if (yy >= 0 && yy < n && xx >= 0 && xx < n && cpy[yy][xx] != 5) {
                    q.add(new Chicken(c.from_y, c.from_x, yy, xx));
                }
            }
        }
    }
}

class Chicken {
    int from_x, from_y, x, y;

    public Chicken(int from_y, int from_x, int y, int x) {
        this.from_x = from_x;
        this.from_y = from_y;
        this.x = x;
        this.y = y;
    }
}
