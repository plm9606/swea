package swea;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

class Nodes{
    int idx, depth;

    public Nodes(int idx, int depth) {
        this.idx = idx;
        this.depth = depth;
    }
}

public class Solution_1263 {
    static int[][] graph, dist;
    static int n;

    public static void main(String[] args)throws FileNotFoundException {
        System.setIn(new FileInputStream("res/input1263.txt"));


        Scanner sc = new Scanner(System.in);

        int tc = sc.nextInt();
        for(int test_case = 1; test_case <= tc; test_case++){
            int answer = Integer.MAX_VALUE;
            n = sc.nextInt();
            graph = new int[n][n];
            dist = new int[n][n];

            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    graph[i][j] = sc.nextInt();
                }
            }

            for(int i=0; i<n; i++){
                boolean[] v = new boolean[n];
                v[i] = true;
                dfs(i, i, 1, v);
            }

            for(int i=0; i<n; i++){
                int cc = 0;
                for(int j=0; j<n; j++){
                    cc += dist[i][j];
                }
                if(answer > cc){
                    answer = cc;
                }
            }
            System.out.printf("#%d %d\n", test_case, answer);

        }
    }

    public static void dfs(int root, int cur, int depth, boolean[] visited){
//        if(!containsFalse(visited)) return;
//        for( int i=0; i<n; i++){
//            int child = graph[cur][i];
//            if(!visited[i] && child !=0){
//                visited[i] = true;
//                dist[root][i] = depth;
//                dfs(root, i, depth+1, visited);
//            }
//        }

        Queue<Nodes> q = new LinkedList<>();
        q.add(new Nodes(root, 0));

        while (!q.isEmpty()){
           Nodes idx = q.poll();
            for(int i=0; i<n ;i++){
                if(!visited[i] && graph[idx.idx][i] !=0) {
                    q.add(new Nodes(i, idx.depth+1));
                    visited[i] = true;
                    dist[root][i] = idx.depth+1;
                }
            }
        }
    }


    public static boolean containsFalse(boolean[] arr){
        boolean answer = false;
        for(boolean b: arr){
            if(!b){
                answer = true;
                break;
            }
        }
        return answer;
    }
}
