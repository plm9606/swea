package baekjoon;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Boj_1717 {
    static int[] parent;
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("Input.txt"));

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        parent = new int[n+1];

        for(int i=0; i<=n; i++){
            parent[i] = i;
        }

        for(int i=0; i<m; i++){
            int query = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();

            if(query == 0){
                union(a,b);
            }else{
                if(find(a) == find(b)){
                    System.out.println("YES");
                }else System.out.println("NO");
            }
        }
    }

    static void union(int a, int b){
        a = find(a);
        b = find(b);
        parent[b] = a;
    }

    static int find(int x){
        if(parent[x] == x) return x;

        parent[x] = find(parent[x]);
        return parent[x];
    }
}
