package swea;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution_1247 {
    static Edge company ;
    static Edge home;
    static List<Edge> customers;
    static int answer;
    static int[][] edges;

    public static void main(String[] args)throws FileNotFoundException {
        System.setIn(new FileInputStream("res/input1247.txt"));

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        for(int test_case = 1; test_case <= N; test_case++){
            answer = Integer.MAX_VALUE;
            int customerCount = sc.nextInt();
             company = new Edge(sc.nextInt(), sc.nextInt());
             home = new Edge(sc.nextInt(), sc.nextInt());
             customers = new ArrayList<>();
            edges = new int[customerCount][customerCount];

            int[] visited = new int[customerCount];

            for(int i=0; i<customerCount; i++){
                customers.add(new Edge(sc.nextInt(), sc.nextInt()));
            }

            permutation(customerCount,0, visited, customerCount);
            System.out.printf("#%d %d\n", test_case, answer);

        }
    }

    private static void permutation(int toPick, int picked, int[] order, int n){
        if(toPick == picked){
//            System.out.println(Arrays.toString(order));
            int distance = clac(order);
//            System.out.println("distance: " + distance);
            if(answer > distance) answer = distance;
            return;
        }

        boolean[] visited = new boolean[n];
        for(int i=0; i<picked; i++){
            visited[order[i]] = true;
        }

        for(int i=0; i<n; i++){
            if(!visited[i]){
                order[picked] = i;
                // weight 계산
                permutation(toPick, picked+1, order, n);
            }
        }

    }

    private static int clac(int[] order){
        int weight = 0;
        for(int i=0; i<order.length-1; i++){
            int from = order[i];
            int to = order[i+1];
            if(edges[from][to] != 0){
                weight += edges[from][to];
            }else {
                int distance  = getDistance(customers.get(from), customers.get(to));
                edges[from][to] = distance;
                edges[to][from] = distance;
                weight += distance;
            }
        }

        weight += getDistance(company, customers.get(order[0]));
        weight += getDistance(home, customers.get(order[order.length-1]));
        return weight;
    }

    private static int getDistance(Edge e1, Edge e2){
        return Math.abs(e1.from-e2.from) + Math.abs(e1.to-e2.to);
    }
}

class Edge{
    int from, to;

    public Edge(int from, int to) {
        this.from = from;
        this.to = to;
    }
}