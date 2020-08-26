package baekjoon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Boj_1167 {
    static LinkedList<int[]>[] tree;
    static int maxNode, maxDist;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("res/boj1167.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        tree = new LinkedList[N];
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            tree[i] = new LinkedList<>();
        }

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            while (true) {
                int v2 = Integer.parseInt(st.nextToken());
                if (v2 == -1) break;
                int cost = Integer.parseInt(st.nextToken());
                tree[v1 - 1].add(new int[]{v2 - 1, cost});
                tree[v2 - 1].add(new int[]{v1 - 1, cost});
            }
        }
//
        dfs(0, 0);
        int v = maxNode;
        maxDist = 0;
        Arrays.fill(visited, false);
        dfs(v, 0);
        System.out.println(maxDist);
    }


    static void dfs(int node, int acc) {
        if (visited[node]) return;

        if (acc > maxDist) {
            maxDist = acc;
            maxNode = node;
        }
        visited[node] = true;

        for (int[] n : tree[node]) {
            dfs(n[0], n[1] + acc);
        }
    }
}
