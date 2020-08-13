package baekjoon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Boj_2531 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("res/boj2531.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), d = Integer.parseInt(st.nextToken()), k= Integer.parseInt(st.nextToken()), c=Integer.parseInt(st.nextToken());
        int[] foods = new int[N];

        for(int i=0; i<N; i++){
            foods[i]= Integer.parseInt(br.readLine());
        }

        int max = 0;
        int end = 0;
        int cnt = 1;
        ArrayList<Integer> line = new ArrayList<>();
        for(int start = 0;start<N; start++){
            while (end-start < k){
                int idx = end;
                if(idx >= N){
                    idx -=N;
                }
                if(!line.contains(foods[idx]) && foods[idx] != c){
                    cnt++;
                }
                line.add(foods[idx]);
                end++;
            }
            if(cnt > max){
                System.out.println(line);
                max = cnt;
            }
            int first = line.get(0);
            line.remove(0);
            if(!line.contains(first) && first != c){
                cnt--;
            }
        }

        System.out.println(max);
    }
}
