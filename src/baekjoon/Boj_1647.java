package baekjoon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj_1647 {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("res/boj1647.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        HashSet<Integer> set = new HashSet<>();
        parent = new int[H + 1];

        PriorityQueue<Road> pq = new PriorityQueue<>();

        for (int i = 1; i <= R; i++) {
            if (i <= H) parent[i] = i;
            st = new StringTokenizer(br.readLine());
            pq.add(new Road(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        int acc = 0;
        int cnt = 0;

        while (cnt < H - 2) {
            Road r = pq.poll();
            if (union(r.v1, r.v2)) {
                acc += r.cost;
                cnt++;
            }
        }

        System.out.println(acc);
    }

    static boolean union(int a, int b) {
        a = find(a);
        b = find(b);

        // a와 b가 다른 트리의 원소인지를 확인한다
        if (a == b) return false;
        parent[b] = a;
        return true;
    }

    static int find(int x) {
        if (parent[x] == x) return x;

        parent[x] = find(parent[x]);
        return parent[x];
    }
}

class Road implements Comparable<Road> {
    int v1, v2, cost;

    public Road(int v1, int v2, int cost) {
        this.v1 = v1;
        this.v2 = v2;
        this.cost = cost;
    }

    @Override
    public int compareTo(Road road) {
        return Integer.compare(this.cost, road.cost);
    }
}