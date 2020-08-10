package baekjoon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_2606 {
    static int[] computers;
    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("res/boj2606.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());// 한줄씩 읽는다. "\n", "\r"을 만날때 까지 읽어온다.
        int M = Integer.parseInt(br.readLine());
        computers = new int[N+1];

        for(int i=1; i<=N; i++){
            computers[i] = i;
        }

        StringTokenizer st;
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            union(v1, v2);
        }

        int ans = 0;
        int one = find(1);
        for(int i=2; i<=N; i++){
            if(find(i) == one) ans++;
        }

        System.out.println(ans);
        System.out.println(Arrays.toString(computers));
    }

    public static void union(int a, int b){
        a= find(a);
        b = find(b);
        // b의 부모의 부모는 a의 부모가 된다.
        computers[b] = a;
    }

    public static int find(int x){
        if(computers[x] == x) return x;
        computers[x] = find(computers[x]);
        return computers[x];
    }
}
