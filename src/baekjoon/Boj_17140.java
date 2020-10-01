package baekjoon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj_17140 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("res/boj17140.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken()) - 1;
        int c = Integer.parseInt(st.nextToken()) - 1;
        int k = Integer.parseInt(st.nextToken());
        int answer = 0;
        int arr[][] = new int[3][3];

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++)
                arr[i][j] = Integer.parseInt(st.nextToken());
        }

        int R = 3, C = 3;

        // 100 넘는 경우 -1
        while (answer <= 100) {
            if (r < R && c < C && arr[r][c] == k) {
                System.out.println(answer);
                System.exit(0);
            }

            // R연산
            if (R >= C) {
                LinkedList<LinkedList<Integer>> list = new LinkedList<>();
                int maxC = C;

                // 한 row씩 계산
                for (int y = 0; y < R; y++) {
                    int[] row = arr[y];
                    HashMap<Integer, Integer> map = new HashMap<>();

                    for (int item : row) {
                        if (item == 0) continue;
                        if (map.containsKey(item)) map.replace(item, map.get(item) + 1);
                        else map.put(item, 1);
                    }

                    PriorityQueue<Count> pq = new PriorityQueue<>();
                    for (int key : map.keySet()) {
                        pq.add(new Count(key, map.get(key)));
                    }

                    LinkedList<Integer> newRow = new LinkedList<>();
                    while (!pq.isEmpty()) {
                        Count count = pq.poll();
                        newRow.add(count.number);
                        newRow.add(count.count);
                    }

                    maxC = Math.max(maxC, newRow.size());
                    list.add(newRow);
                }

                // 행 또는 열의 크기가 100이상인 경우 처음 100까지만
                maxC = maxC > 100 ? 100 : maxC;


                int[][] newArr = new int[R][maxC];
                for (int y = 0; y < R; y++) {
                    LinkedList<Integer> newRow = list.get(y);
                    for (int x = 0; x < maxC; x++) {
                        if (x >= newRow.size()) break;
                        else newArr[y][x] = newRow.get(x);
                    }
                }
                C = maxC;
                arr = newArr;

            } else {
                LinkedList<LinkedList<Integer>> list = new LinkedList<>();
                int maxR = R;
                for (int x = 0; x < C; x++) {
//                    int[] n = new int[101];
                    HashMap<Integer, Integer> map = new HashMap<>();

                    for (int y = 0; y < R; y++) {
                        int item = arr[y][x];
                        if (item == 0) continue;
                        if (map.containsKey(item)) map.replace(item, map.get(item) + 1);
                        else map.put(item, 1);
                    }

                    PriorityQueue<Count> pq = new PriorityQueue<>();
                    for (int key : map.keySet()) {
                        pq.add(new Count(key, map.get(key)));
                    }

                    LinkedList<Integer> newCol = new LinkedList<>();
                    while (!pq.isEmpty()) {
                        Count count = pq.poll();
                        newCol.add(count.number);
                        newCol.add(count.count);
                    }


                    maxR = Math.max(maxR, newCol.size());
                    list.add(newCol);
                }

                maxR = maxR > 100 ? 100 : maxR;

                int[][] newArr = new int[maxR][C];

                for (int i = 0; i < list.size(); i++) {
                    LinkedList<Integer> col = list.get(i);
                    for (int j = 0; j < col.size(); j++) {
                        if (j >= col.size()) break;
                        newArr[j][i] = col.get(j);
                    }
                }

                R = maxR;
                arr = newArr;
            }
            answer++;
        }
        System.out.println(-1);
    }
}

class Count implements Comparable<Count> {
    int number, count;

    public Count(int number, int count) {
        this.number = number;
        this.count = count;
    }

    @Override
    public int compareTo(Count o) {
        if (this.count > o.count) return 1;
        else if (this.count < o.count) return -1;
        else {
            return Integer.compare(this.number, o.number);
        }
    }
}
