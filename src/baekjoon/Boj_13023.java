package baekjoon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Boj_13023 {
    static ArrayList<LinkedList<Integer>> friends;
    static boolean visit[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("res/boj13023.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        visit = new boolean[N];
        friends = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            friends.add(new LinkedList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            friends.get(v1).add(v2);
            friends.get(v2).add(v1);
        }

        for (int i = 0; i < N; i++) {
            dfs(0, i);
        }

        System.out.println("0");
    }

    public static void dfs(int level, int node) {
        visit[node] = true;
        if (level == 4) {
            System.out.println("1");
            System.exit(0);
        }
        for (int i : friends.get(node)) {
            if (!visit[i]) {
                dfs(level + 1, i);
            }
        }

        visit[node] = false;
    }

}
