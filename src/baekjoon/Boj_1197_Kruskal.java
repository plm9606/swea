package baekjoon;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;


public class Boj_1197_Kruskal {
    static int N,E;
    static PriorityQueue<Edge> pq;
    static List<Edge> subtrees;
    static List<Edge> mstPath;
    static int[] parent;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("res/boj1197.txt"));
         Scanner sc = new Scanner(System.in);
        N= sc.nextInt();
        E = sc.nextInt();

        pq = new PriorityQueue<>();
        subtrees = new ArrayList<>();
        mstPath = new ArrayList<>();
        parent = new int[N+1];

        for(int i=0; i<=N; i++){
            parent[i]= i;
        }

        for(int i=0; i<E;  i++){
            int from = sc.nextInt();
            int to = sc.nextInt();
            int weight = sc.nextInt();
            pq.add(new Edge(from, to, weight));
        }

        kruskal();
        int answer=0;
        for(Edge edge: mstPath){
            answer += edge.cost;
        }
        System.out.println(answer);
    }

    private static void kruskal(){

        while (mstPath.size()<N-1){
            Edge edge = pq.poll();
            int sub1 = find(edge.from);
            int sub2 = find(edge.to);

            if(sub1 == sub2) continue;

            merge(sub1, sub2);
            mstPath.add(edge);
        }
    }

    private static void merge(int sub1, int sub2){
        int root1 = find(sub1);
        int root2 = find(sub2);

        if(root1 != root2){
            parent[root1] = sub2;
        }else return;
    }


    private static int find(int vertex){
        if(vertex == parent[vertex]) return vertex;
        parent[vertex] = find(parent[vertex]);
        return parent[vertex];
    }
}

