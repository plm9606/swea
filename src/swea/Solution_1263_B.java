package swea;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution_1263_B {
    static int[][] arr, memo;
    static int N;
    public static void main(String[] args)throws FileNotFoundException {
        System.setIn(new FileInputStream("res/input1263.txt"));


        Scanner sc = new Scanner(System.in);

        int tc = sc.nextInt();
        for(int test_case = 1; test_case <= tc; test_case++){
            int answer = Integer.MAX_VALUE;
            N = sc.nextInt();
            arr = new int[N][N];
            memo = new int[N][N];

            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    arr[i][j] = sc.nextInt();
                    if(i!=j) memo[i][j] = -1;
                }
            }


            for(int k=0; k<N; k++){
                sol(k);
                int cc=0;
                for(int i=0; i<N; i++){
                    if(memo[k][i] != -1) cc+= memo[k][i];
                }
                if(answer > cc) answer = cc;
            }

            System.out.printf("#%d %d\n", test_case, answer);

        }
    }

    public static void sol(int k){
        Queue<Network> q = new LinkedList<>();
        for(int i=0; i<N; i++){
            if(arr[k][i] == 1){
                memo[k][i] = 1;
                q.add(new Network(k, i, 2));
            }
        }

        while (!q.isEmpty()){
            Network network = q.poll();
            for(int i=0; i<N; i++){
                if(arr[network.node][i] == 1 && memo[network.from][i] == -1){
                    memo[network.from][i] = network.dist;
                    q.add(new Network(network.from, i, network.dist+1));
                }
            }
        }
    }
}

class Network{
    int node,dist, from;

    Network(int  from, int node, int dist){
        this.from = from;
        this.node = node;
        this.dist = dist;
    }
}