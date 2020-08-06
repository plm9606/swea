package baekjoon;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Boj_11438 {
    static int[][] tree;
    static int N;
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("res/boj11438.txt"));
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        tree = new int[N+1][N+1];

        for(int i=0; i<N-1; i++){
            int from = sc.nextInt();
            int to = sc.nextInt();
            tree[from][to] = 1;
            tree[to][from] = 1;
        }

//        List<int[]> rootConnect = Arrays.stream(arrN).filter(point->point[0]==1).collect(Collectors.toList());

        int M = sc.nextInt();
        for(int i=0; i<M; i++){
            int node1 = sc.nextInt();
            int node2 =sc.nextInt();
            String path1 = bfs(node1);
            String path2 = bfs(node2);

            char parent=' ';
            int idx1=0, idx2 = 0;
            while (idx1 < path1.length() && idx2 <path2.length()){
                if(path1.charAt(idx1) == path2.charAt(idx2)){
                    parent = path1.charAt(idx1);
                }else break;
                idx1++;
                idx2++;
            }

            System.out.println(parent);
        }
    }

    static String bfs(int target){
        boolean[] visited = new boolean[N+1];
        Queue<Path> q = new LinkedList<>();

        Path intial = new Path(1);
        intial.curPath = "1";
        visited[1] = true;
        q.add(intial);

        while (!q.isEmpty()){
            Path path = q.poll();
            if(path.idx == target){
                return path.curPath;
            }
            for(int i=1; i<=N; i++){
                if(!visited[i] && tree[path.idx][i] == 1){
                    Path next = new Path(i);
                    visited[i]=true;
                    next.curPath = path.curPath + i;
                    q.add(next);
                }
            }
        }
        return " ";
    }
}
 class Path{
    int idx;
    String curPath;

     public Path(int idx) {
         this.idx = idx;
         this.curPath = "";
     }
 }