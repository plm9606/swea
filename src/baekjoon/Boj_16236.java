package baekjoon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_16236 {
    static int[][] sea;
    static boolean[][] visited;
    static int N, size, eat;
    static int[] baby, dy = {0, 1, 0, -1}, dx = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("res/boj16236.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        sea = new int[N][N];
        visited = new boolean[N][N];
        baby = new int[2];
        size = 2;
        eat = 0;

        int sec = 0;

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int v = Integer.parseInt(st.nextToken());
                if (v == 9) {
                    baby[0] = i;
                    baby[1] = j;
                } else {
                    sea[i][j] = v;
                }

            }
        }
        ArrayList<int[]> foods;
        do {
            Queue<Shark> q = new LinkedList<>();
            q.add(new Shark(baby[0], baby[1], size, 0, 0));
            foods = new ArrayList<>();
            visited = new boolean[N][N];
            visited[baby[0]][baby[1]] = true;
            // bfs로 상어가 먹을 수 있는 물고기 찾는다
            while (!q.isEmpty()) {
                Shark s = q.poll();
                for (int i = 0; i < 4; i++) {
                    int yy = s.y + dy[i];
                    int xx = s.x + dx[i];
                    // 이동할 수 있다면
                    if (yy >= 0 && yy < N && xx >= 0 && xx < N && !visited[yy][xx] && sea[yy][xx] <= size) {
                        q.add(new Shark(yy, xx, s.size, s.eat, s.distance + 1));
                        visited[yy][xx] = true;
                        // 먹을 수 있다면
                        if (sea[yy][xx] < s.size && sea[yy][xx] != 0) foods.add(new int[]{yy, xx, s.distance + 1});
                    }
                }
            }

            // 먹을 수 있는 물고기 없으면
            if (foods.size() == 0) {
                // 엄마 부른다.
                System.out.println(sec);
                System.exit(0);
            }

//            while (foods.size() > 0) {
            int mindY = Integer.MAX_VALUE, mindX = Integer.MAX_VALUE, min = 0;
            int minDistance = Integer.MAX_VALUE;
            // 먹을 수 있는 물고기중 가장 가까운것 찾는다.
            for (int i = 0; i < foods.size(); i++) {
                int[] food = foods.get(i);
                int dy = baby[0] - food[0];
                int dx = baby[1] - food[1];
                int distance = food[2];
                boolean changeMin = false;
                if (minDistance == distance) {
                    // 더 위에 있는 경우
                    if (mindY < dy) {
                        changeMin = true;
                    }
                    // 둘다 위에 있을 때 더 왼쪽에 있는 경우
                    else if (mindY == dy && mindX < dx) {
                        changeMin = true;
                    }
                }
                // 거리가 더 가까울 경우
                else if (minDistance > distance) {
                    changeMin = true;
                }

                if (changeMin) {
                    mindY = dy;
                    mindX = dx;
                    minDistance = distance;
                    min = i;
                }
            }
            // 먹이 좌표로 이동하고 먹이를 먹는다
            sec += minDistance;
            baby[0] = foods.get(min)[0];
            baby[1] = foods.get(min)[1];
            eat++;

            System.out.println("이동 y:" + baby[0] + ", x: " + baby[1] + ", size: " + size + ", eat: " + eat + ", 먹이: " + sea[baby[0]][baby[1]]);


            sea[baby[0]][baby[1]] = 0;
            foods.remove(min);


            // 1살 더 먹을 수 있으면
            if (size == eat) {
                eat = 0;
                size++;
            }
//            }
        } while (true);

    }

    public static void find(int y, int x, int sec) {
        for (int i = 0; i < 4; i++) {
            int yy = y + dy[i];
            int xx = x + dx[i];
            if (yy >= 0 && yy < N && xx >= 0 && xx < N && sea[yy][xx] < size) {

            }
        }
    }
}

class Shark {
    int y, x, size, eat, distance;

    public Shark(int y, int x, int size, int eat, int distance) {
        this.y = y;
        this.x = x;
        this.size = size;
        this.eat = eat;
        this.distance = distance;
    }
}
