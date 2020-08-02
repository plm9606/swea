package baekjoon;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Boj_9370 {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("res/boj9370.txt"));
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int tc=0; tc<T; tc++){
            int n = sc.nextInt(), m = sc.nextInt(), t = sc.nextInt(), s = sc.nextInt(), g = sc.nextInt(), h = sc.nextInt();
            int[][] arr = new int[n+1][n+1];
            int[] candidate = new int[t];
            int[] shortest = new int[n+1];
            // 특정 경로를 지나가는지를 저장
            boolean contains[] = new boolean[n+1];

            for(int i=0; i<m; i++){
                int a = sc.nextInt();
                int b = sc.nextInt();
                int d = sc.nextInt();
                arr[a][b] = d;
                arr[b][a] = d;
            }

            for(int i=0; i<t; i++){
                candidate[i]=sc.nextInt();
            }

            Arrays.sort(candidate);

            for(int i=0; i<n+1; i++){
                shortest[i] = Integer.MAX_VALUE;
            }

            PriorityQueue<Vertex> pq = new PriorityQueue<>();
            pq.add(new Vertex(s,0));
            shortest[s] = 0;

            while(!pq.isEmpty()){
                Vertex v = pq.poll();
                if(shortest[v.num] < v.cost) continue;
                for(int i=1; i<=n; i++){
                    // 현재 경로의 비용이 더 짧다면
                    if(arr[v.num][i] != 0 && shortest[v.num]+ arr[v.num][i] < shortest[i]){
                        shortest[i] = shortest[v.num]+ arr[v.num][i];
                        pq.add(new Vertex(i, shortest[i]));
                        // 현재 경로 v.num -> i 가 (g,h) 이거나
                        // s에서 v.num까지의 최소 경로가 이미 (g,h)경로를 포함하고 있다면
                        if((v.num==g && i==h )|| (v.num==h && i==g ) ||contains[v.num]){
                            contains[i] = true;
                        }else contains[i]=false;
                    }
                    // 현재 경로의 비용과 최소 비용이 같은 경우 현재 경로가 (g,h)를 포함할 수 있으므로 contains만 update할 수 있도록 한다.
                    else if(arr[v.num][i] != 0 && shortest[v.num]+ arr[v.num][i] == shortest[i]){
                        if((v.num==g && i==h )|| (v.num==h && i==g ) ||contains[v.num]){
                            contains[i] = true;
                        }
                    }
                }
            }


            for(int i=0; i<candidate.length; i++){
                if(contains[candidate[i]]) System.out.print(candidate[i] +" ");
            }
            System.out.println();
        }
    }
}

class Vertex implements Comparable<Vertex> {
    int num, cost;

    public Vertex(int num, int cost) {
        this.num = num;
        this.cost = cost;
    }

    @Override
    public int compareTo(Vertex o) {
        return this.cost-o.cost;
    }
}
