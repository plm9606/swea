package baekjoon;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;


class Edge implements Comparable<Edge> {
    int from, to, cost;

    public Edge(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return this.cost - o.cost;
    }

}

public class Boj_1197 {

    static List<List<Edge>> adjList;
    static boolean[] visited;
    static List<Edge> mstPath;
    static int N,E;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("res/boj1197.txt"));


        Scanner sc = new Scanner(System.in);
        N= sc.nextInt();
        E = sc.nextInt();

        adjList = new ArrayList<>();
        mstPath = new ArrayList<>();
        visited = new boolean[N+1]; // 1부터 카운트

        adjList.add(new <Edge> ArrayList());
        for(int i=1; i<=N; i++){
            adjList.add(new <Edge> ArrayList());
        }

        while (E-- > 0){
            int from = sc.nextInt();
            int to = sc.nextInt();
            int weight = sc.nextInt();
            adjList.get(from).add(new Edge(from, to, weight));
            adjList.get(to).add(new Edge(to, from, weight));
        }
        prim();
    }

    private static void prim(){
        PriorityQueue<Edge> pq =new PriorityQueue<>();
        // 방문 대기 정점
        Queue<Integer> queue = new LinkedList<>();
        // 임의의 시작 노드 추가
        queue.add(1);

        while (!queue.isEmpty()){
            // 출발 정점을 시작으로 가중치가 낮은 간선과 연결된 정점 도출
            int cur= queue.poll();
            visited[cur] = true;

            // 방문한 정점과 연결된 간선 정보를 우선순위 큐에 넣는다
            for(Edge edge: adjList.get(cur)){
                if(!visited[edge.to]){
                    // 가중치가 작은 노드가 앞으로 온다
                    pq.add(edge);
                }
            }

            while (!pq.isEmpty()){
                Edge edge = pq.poll();
                if(!visited[edge.to]){
                    queue.add(edge.to);
                    visited[edge.to] = true;
                    mstPath.add(edge);
                    break;
                }
            }
        }
        int answer=0;
        for(Edge edge: mstPath){
            answer += edge.cost;
        }
        System.out.println(answer);
    }
}

