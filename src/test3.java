import java.util.Arrays;
import java.util.LinkedList;

public class test3 {
    static boolean[] visited;
    static LinkedList<Integer> list[];
    static int maxNode, maxDist;

    public int solution(int n, int[][] edges) {
        int answer = 0;
        visited = new boolean[n];
        list = new LinkedList[n];

        for (int i = 0; i < n; i++)
            list[i] = new LinkedList<>();

        for (int[] e : edges) {
            list[e[0] - 1].add(e[1] - 1);
            list[e[1] - 1].add(e[0] - 1);
        }

        dfs(0, 0);
        System.out.println(maxNode + " " + maxDist);

        Arrays.fill(visited, false);
        int v = maxNode;
        maxDist = 0;
        dfs(v, 0);
        System.out.println(maxNode + " " + maxDist);
        int d1 = maxDist;

        int b = maxNode;
        Arrays.fill(visited, false);
        visited[b] = true;
        maxDist = 0;
        dfs(v, 0);
        System.out.println(maxNode + " " + maxDist);

        System.out.println(d1 - (d1 - maxDist));
        return Math.max(d1 - maxDist, maxDist);
    }

    void dfs(int node, int acc) {
        if (visited[node]) return;

        if (acc > maxDist) {
            maxDist = acc;
            maxNode = node;
        }
        visited[node] = true;

        for (int n : list[node]) {
            dfs(n, 1 + acc);
        }
    }
}
