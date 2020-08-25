package programmers;
import java.util.*;

public class 외벽_점검 {
    private int[] weak, dist;
    private int wallCnt, friendsCnt,n, answer = -1;
    private boolean STOP = false;
    public int solution(int n, int[] weak, int[] dist) {
        this.dist = dist;
        this.weak = weak;
        this.n=n;
        wallCnt = weak.length;
        friendsCnt = dist.length;

        Arrays.stream(dist).boxed().sorted(Collections.reverseOrder()).mapToInt(Integer::intValue).toArray();
        for(int i=1; i<=dist.length; i++){
            if(STOP) break;
            permutation(new int[i],0,i);
        }
        return answer;
    }

    public void permutation(int[] order, int picked, int toPick){
        if(STOP) return;
        if(toPick == picked){
            // System.out.println(Arrays.toString(order));
            checkWall(order, toPick);
            return;
        }

        boolean[] check = new boolean[dist.length];
        for(int i=0; i<picked; i++){
            check[order[i]] = true;
        }

        for(int i=0; i<dist.length; i++){
            if(!check[i]){
                order[picked] = i;
                permutation(order, picked+1, toPick);
            }
        }
    }

    public void checkWall(int[] order, int toPick){
        int[] friends = new int[toPick];
        for(int i=0; i<toPick; i++){
            int idx = order[i];
            friends[i] = dist[idx];
        }

        loop:for(int start=0; start<weak.length; start++){
            int cnt = 0;

            for(int friend: friends){
                int max = start+cnt < wallCnt ? weak[start+cnt]+friend : weak[start+cnt-wallCnt]+n+friend;
                while(cnt<weak.length){
                    int wall = start+cnt < wallCnt ? weak[start+cnt] : weak[start+cnt-wallCnt]+n;
                    if(wall <= max) cnt++;
                    else break;
                }

                if(cnt == wallCnt){
                    System.out.println(toPick);
                    answer = toPick;
                    STOP = true;
                    break loop;
                }
            }
        }

    }

    public int solution_A(int n, int[] weak, int[] dist) {

            int answer = 0;
            Arrays.sort(dist);
            int distMax = dist[dist.length-1];
            int[][] graph = new int[weak.length][weak.length];
            boolean[] visited = new boolean[weak.length];
            int min_len = Integer.MAX_VALUE;
            int[] point = new int[2];

            if(weak.length==1){
                for(int d: dist){
                    if(weak[0] <= d) return 1;
                }
            }

            for(int i=weak.length-1; i>=1; i--){
                for(int j=i-1; j>=0; j--){
                    int min =  Math.min(weak[i]-weak[j], n+weak[j]-weak[i]);
                    graph[i][j] = min;
                    graph[j][i] = min;
                    if(min_len > min){
                        min_len = min;
                        point[0] = i;
                        point[1] = j;
                    }
                }
            }

            HashSet<Integer> set = new HashSet<>();
            HashSet<Integer> count = new HashSet<>();
            Queue<Edge>q = new LinkedList<>();

            if(min_len <= distMax){
                q.add(new Edge(min_len, point[0], point[1]));
                set.add(point[0]);
                set.add(point[1]);
                count.add(point[0]);
                count.add(point[1]);
            }

            int acc = 0;
            while(!q.isEmpty() && count.size() <= weak.length){
                Edge e = q.poll();
                if(count.size() == weak.length) {
                    answer++;
                    break;
                }
                if(visited[e.v1] && visited[e.v2]) continue;
                if(!(set.contains(e.v1) || set.contains(e.v2))){
                    // System.out.printf("--- %d %d %d\n", e.cost, e.v1, e.v2);
                    for(int i=0; i<dist.length; i++){
                        if(dist[i] >= acc){
                            dist[i] = 0;
                            answer++;
                            break;
                        }
                    }
                    acc = 0;
                    set = new HashSet<>();
                }



                if(acc + e.cost <= dist[dist.length-1]){
                    // System.out.printf("%d %d %d\n", e.cost, e.v1, e.v2);
                    acc += e.cost;
                    visited[e.v1] = true;
                    visited[e.v2] = true;
                    set.add(e.v1);
                    set.add(e.v2);
                    count.add(e.v1);
                    count.add(e.v2);
                }


                for(int i=0; i<graph.length; i++){
                    if(!visited[i]){
                        if(e.v1 != i && graph[e.v1][i] <= distMax) q.add(new Edge(graph[e.v1][i], e.v1, i));
                        if(e.v2 != i&& graph[e.v2][i] <= distMax)q.add(new Edge(graph[e.v2][i], e.v2, i));
                    }
                }
            }

            if(count.size() != weak.length) answer = -1;
            return answer;
        }
    }

    class Edge{
        int cost, v1, v2;
        Edge(int cost, int v1, int v2){
            this.cost = cost;
            this.v1 = v1;
            this.v2 = v2;
        }

}
