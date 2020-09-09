package baekjoon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Boj_9466 {
    static int[] students;
    static boolean visit[], cycle[];
    static int c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("res/boj9466.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int tc = 0; tc < t; tc++) {
            int n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            students = new int[n];
            visit = new boolean[n];
            cycle = new boolean[n];
            c = 0;
            for (int i = 0; i < n; i++) {
                students[i] = Integer.parseInt(st.nextToken()) - 1;
            }

            for (int i = 0; i < n; i++) {
//                if (students[i] == i) visit[i] = true;
                if (!visit[i]) {
//                    System.out.print(i + ": ");
                    dfs(i);
//                    System.out.println();
                }
//                if (visit[i]) c++;
            }

            System.out.println("ans: " + (n - c));
        }

    }

    static private void dfs(int idx) {
//        System.out.print(idx + " ");
        int next = students[idx];
        visit[idx] = true;
        if (!visit[next]) dfs(next);
        else {
            if (!cycle[next]) {
                for (int i = next; i != idx; i = students[i]) c++;
                c++;
            }
        }
        cycle[idx] = true;
    }

    static private boolean dfs(int node, int target) {
        System.out.print(node + " ");
        if (visit[node]) return false;
        visit[node] = true;
        if (students[node] == target) {
            return true;
        }
        if (students[node] == node) {
            if (node != target) {
                return false;
            }
            return true;
        }
        visit[node] = dfs(students[node], target);
//        dfs(students[node], target);
        return visit[node];
    }
}
