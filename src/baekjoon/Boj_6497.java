package baekjoon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj_6497 {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("res/boj6497.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            if (m == n && m == 0) break;
            int sum = 0;
            parent = new int[m];
            for (int i = 0; i < m; i++) {
                parent[i] = i;
            }
            PriorityQueue<Edge6497> pq = new PriorityQueue<>();
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());
                sum += z;
                pq.add(new Edge6497(x, y, z));
            }

            int w = 0;
            while (!pq.isEmpty()) {
                Edge6497 e = pq.poll();
                if (sameParent(e.v1, e.v2)) continue;
                union(e.v1, e.v2);
                w += e.w;
            }
            System.out.println(sum - w);
        }
    }


    static boolean sameParent(int a, int b) {
        return find(a) == find(b);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        parent[a] = b;
    }

    static int find(int a) {
        if (parent[a] == a) return a;
        parent[a] = find(parent[a]);
        return parent[a];
    }
}

class Edge6497 implements Comparable<Edge6497> {
    int v1, v2, w;

    public Edge6497(int v1, int v2, int w) {
        this.v1 = v1;
        this.v2 = v2;
        this.w = w;
    }

    @Override
    public int compareTo(Edge6497 o) {
        return Integer.compare(this.w, o.w);
    }
}