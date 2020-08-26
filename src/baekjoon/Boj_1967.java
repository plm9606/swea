package baekjoon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Boj_1967 {
    static int N;
    static boolean[] visited;
    static LinkedList<int[]> tree[];
    static int maxNode = 1, maxDist = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("res/boj1967.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N];
        StringTokenizer st;
        tree = new LinkedList[N];
        for (int i = 0; i < N; i++) {
            tree[i] = new LinkedList<>();
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken()) - 1;
            int child = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            tree[parent].add(new int[]{child, cost});
            tree[child].add(new int[]{parent, cost});
        }

        dfs(0, 0);
        int v1 = maxNode;
        maxDist = 0;
        Arrays.fill(visited, false);
        System.out.println("--------------------------");
        dfs(0, v1);
        System.out.println(maxDist);


    }

    private static void dfs(int acc, int node) {
        if (visited[node]) return;
        if (acc > maxDist) {
            System.out.println(acc + " " + node);
            maxDist = acc;
            maxNode = node;
        }
        visited[node] = true;

        for (int[] child : tree[node]) {
            dfs(acc + child[1], child[0]);
        }
    }
}
