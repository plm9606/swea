package baekjoon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Boj_1707 {
    static ArrayList<LinkedList<Integer>> graph;
    static boolean STOP;
    static boolean[] visited;
    static int[] types;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("res/boj1707.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < k; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            graph = new ArrayList<>();
            types = new int[v];
            visited = new boolean[v];
            STOP = false;
            for (int i = 0; i < v; i++) {
                graph.add(new LinkedList<>());

            }

            for (int i = 0; i < e; i++) {
                st = new StringTokenizer(br.readLine());
                int v1 = Integer.parseInt(st.nextToken()) - 1;
                int v2 = Integer.parseInt(st.nextToken()) - 1;
                graph.get(v1).add(v2);
                graph.get(v2).add(v1);
            }

            types[0] = 1;
            for (int i = 0; i < v; i++) {
                if (STOP) break;
                if (!visited[i]) dfs(i);
            }
            if (!STOP) System.out.println("YES");
            else System.out.println("NO");
        }
    }


    public static void dfs(int idx) {
        if (STOP) return;
        visited[idx] = true;

        for (int n : graph.get(idx)) {
            if (!visited[n]) {
                if (types[n] == 0) {
                    types[n] = getType(types[idx]);
                    dfs(n);
                } else {
                    if (types[n] == types[idx]) {

                        STOP = true;
                    } else {
                        dfs(n);
                    }
                }

            } else {
                if (types[n] == types[idx]) {

                    STOP = true;
                }
            }
        }
//        visited[idx] = false;
    }

    public static int getType(int parent) {
        return parent == 1 ? 2 : 1;
    }
}
