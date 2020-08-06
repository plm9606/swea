package baekjoon;

import java.io.*;
import java.util.*;

public class Boj_11438_B {
    static int N;
    // parent[y][x] = 노드y의 2^x번째 조상을 의미함
    static int[][] parent;
    static boolean[] visited;
    static int[] depth;
    static List<Integer>[] adj;
    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("res/boj11438.txt"));
        N = Integer.parseInt(br.readLine());
        adj= new ArrayList[N+1];
        for (int i = 1; i<=N; i++) {
            adj[i] = new ArrayList<>();
        }
        parent = new int[N+1][21];
        depth = new int[N+1];
        visited = new boolean[N+1];

        StringTokenizer st;
        for(int i=0; i<N-1; i++){
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            adj[v1].add(v2);
            adj[v2].add(v1);
        }

        makeDepth();
        makeParent();
//        List<int[]> rootConnect = Arrays.stream(edge).filter(point->point[0]==1 || point[1]==1).collect(Collectors.toList());


        int M = Integer.parseInt(br.readLine());
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            System.out.println(lca(node1, node2));
        }
    }

    // bfs로 tree를 순회하며 level정보와 부모정보 만들어준다
    static void makeDepth(){
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        visited[1] = true;
        int level = 0;

        while (!q.isEmpty()){
            level++;
            int size = q.size();
            for(int i=0; i<size; i++) {
                int vertex = q.poll();
                // 자식 노드가 없는 경우
                if (adj[vertex] == null) continue;

                for (int j = 0; j < adj[vertex].size(); j++) {
                    // 양방향으로 연결되어있는 경우 무한루프
                    int child = adj[vertex].get(j);
                    if(!visited[child]){
                        // vertex의 자손에게 부모(vertex)정보 기입
                        parent[child][0] = vertex;
                        q.offer(child);
                        visited[child] = true;
                        depth[child] = level;
                    }
                }
            }
        }
    }

    static void makeParent(){
        for(int i=1; i<=20; i++){
            for(int j=1; j<=N; j++){
                parent[j][i] = parent[parent[j][i-1]][i-1];
            }
        }
    }

    static int lca(int v1, int v2){
        // level이 더 깊은게 v1이 되도록
        if(depth[v1] < depth[v2]){
            int temp = v1;
            v1 = v2;
            v2 = temp;
        }

//        System.out.printf("[%d]:%d, [%d]:%d diff:%d\n",v1, depth[v1], v2, depth[v2], (depth[v1] - depth[v2]));

        // 더 깊은 v1을 2승씩 점프하여 두 노드의 level을 맞춘다
        for(int i=20; i>=0; i--){
            if(Math.pow(2,i) <= depth[v1] - depth[v2]){
                v1 = parent[v1][i];
            }
        }

        //높이 맞췄는데 두 수가 같음
        if(v1==v2) return v1;

        //두 노드를 같이 2승씩 점프시키며 공통부모 바로 아래까지 올림
        for(int i=20; i>=0; i--){
            if(parent[v1][i] != parent[v2][i]){
                v1 = parent[v1][i];
                v2 = parent[v2][i];
            }
        }

        return parent[v1][0];
    }
}