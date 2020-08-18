package baekjoon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Boj_13458 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("res/boj13458.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());// 한줄씩 읽는다. "\n", "\r"을 만날때 까지 읽어온다.
        int[] examinee = new int[n];
        HashMap<Long, Long> map = new HashMap<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            examinee[i] = Integer.parseInt(st.nextToken());
        }
         st = new StringTokenizer(br.readLine());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        long min = n;

        for(int i=0; i<n; i++){
            if(examinee[i]-b <= 0 ) continue;
            if(!map.containsKey((long)examinee[i])){
                map.put((long)examinee[i], (long)Math.ceil((examinee[i]-b)/1.0/c));
            }
            min += map.get((long)examinee[i]);
        }

        System.out.println(min);
    }
}
