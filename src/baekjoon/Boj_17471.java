package baekjoon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_17471 {
    static int N, min = Integer.MAX_VALUE;
    static int[] populations;
    static LinkedList<Integer> map[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("res/boj17471.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        populations = new int[N];
        map = new LinkedList[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            map[i] = new LinkedList<>();
            populations[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            for (int j = 0; j < cnt; j++) {
                map[i].add(Integer.parseInt(st.nextToken()) - 1);
            }
        }

        for (int i = 1; i <= N / 2; i++) {
            c(new boolean[N], i, 0);
        }

        if (min == Integer.MAX_VALUE) min = -1;
        System.out.println(min);
    }

    static boolean check(LinkedList<Integer> list) {

        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[list.size()];
        q.add(list.peek());
        visited[0] = true;
        int cnt = list.size() - 1;
        while (!q.isEmpty()) {
            if (cnt == 0) break;
            int n = q.poll();
            for (int nextNode : map[n]) {
                if (list.contains(nextNode) && !visited[list.indexOf(nextNode)]) {
                    visited[list.indexOf(nextNode)] = true;
                    q.add(nextNode);
                    cnt--;
                }
            }
        }

        if (cnt == 0) return true;
        else return false;
    }

    public static void c(boolean[] bit, int toPick, int target) {
        if (toPick == 0) {
            LinkedList<Integer> set = new LinkedList<>();
            LinkedList<Integer> b = new LinkedList<>();
            int acc1 = 0, acc2 = 0;
            for (int i = 0; i < bit.length; i++) {
                if (bit[i]) {
                    acc1 += populations[i];
                    set.add(i);
                } else {
                    acc2 += populations[i];
                    b.add(i);
                }
            }

            boolean res = check(set);
            boolean res2 = check(b);

            if (res && res2) {
                min = Math.min(min, Math.abs(acc1 - acc2));
            }
            return;
        }

        for (int i = target; i < bit.length; i++) {
            bit[i] = true;
            c(bit, toPick - 1, i + 1);
            bit[i] = false;
        }
    }
}
