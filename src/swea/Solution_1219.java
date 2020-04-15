package swea;

import java.io.*;
import java.util.*;

public class Solution_1219 {
    int len;
    int[][] adj = new int[100][2];
    int answer;

    public void solution() throws IOException {
        System.setIn(new FileInputStream("res/input1219.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in)); //선언


        for(int test_case = 1; test_case <= 10; test_case++){
            for(int i=0; i<100; i++){
                for(int j=0; j<2; j++){
                    adj[i][j] = -1;
                }
            }
            answer = 0;
            String s = bf.readLine();
            StringTokenizer st = new StringTokenizer(s);
            st.nextToken();
            len = Integer.parseInt(st.nextToken());

            s=bf.readLine();
            st = new StringTokenizer(s);

            while (st.hasMoreTokens()){
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                if(adj[from][0] == -1){
                    adj[from][0] = to;
                }else adj[from][1] = to;
            }

            dfs();
            System.out.printf("#%d %d\n", test_case, answer);

        }
    }

    public void dfs(){
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[100];
        stack.push(0);
        visited[0] = true;
        while (stack.size()>0){
            int node = stack.pop();
            if(node==99){
                answer=1;
                break;
            }

            for(int i=0; i<2; i++){
                if(adj[node][i] != -1 && visited[adj[node][i]] == false){
                    stack.push(adj[node][i]);
                    visited[adj[node][i]] = true;
                }
            }
        }

    }


}
