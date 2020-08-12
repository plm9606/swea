package baekjoon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_1976 {
    static int[] cities;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("res/boj1976.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
         cities = new int[N+1];
        for(int i=1; i<=N; i++){
            cities[i] = i;
        }

        StringTokenizer st;
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++){
                int v1 = Integer.parseInt(st.nextToken());
                if(v1 == 1){
                    join(i,j);
                }
            }
        }

//        System.out.println(Arrays.toString(cities));
        st = new StringTokenizer(br.readLine());
        int first = find(Integer.parseInt(st.nextToken()));
        while (st.hasMoreTokens()){
            int city = Integer.parseInt(st.nextToken());
            if(find(city) != first){
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");
    }

    public static void join(int a, int b){
        a = find(a);
        b = find(b);
        cities[b] = a;
    }

    public static int find(int a){
        if(a == cities[a]) return a;
        cities[a] = find(cities[a]);
        return cities[a];
    }
}
