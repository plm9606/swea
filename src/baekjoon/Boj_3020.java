package baekjoon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_3020 {
    static int[] top, bottom ;
    static int h;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("res/boj3020.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        top = new int[n/2]; bottom = new int[n/2];
        for(int i=1; i<=n; i++){
            if(i%2==0){
                top[(i/2)-1] = Integer.parseInt(br.readLine());
            }else {
                bottom[i/2] = Integer.parseInt(br.readLine());
            }
        }

        Arrays.sort(top);
        Arrays.sort(bottom);

        int min = n;
        int levelCnt = 0;

        for(int level=1; level<=h; level++){
            int b = findBottom(0, bottom.length-1, level);
            int t = findTop(0, top.length-1, level);

            int cnt = 0;
            if(b == -1){
                cnt+=bottom.length;
            }else {
                cnt +=( bottom.length-1-b);
            }

            if(t == -1){
                cnt+=bottom.length;
            }else {
                cnt +=( bottom.length-1-t);
            }

            if(cnt < min){
                min = cnt;
                levelCnt = 1;
            }else if(cnt == min){
                levelCnt++;
            }
        }
        System.out.println(min + " " + levelCnt);

    }

    public static int findBottom(int low, int high, int level){
        int cnt = -1;
        while (low <= high){
            int mid = (low+high)/2;
            // 장애물에 걸리지 않는 경우
            if(level > bottom[mid]){
                low = mid+1;
                cnt = mid;
            }else {
                high = mid-1;
            }
        }
        return cnt;
    }

    public static int findTop(int low, int high, int level){
        int idx = -1;
        while (low <= high){
            int mid = (low+high)/2;
            // 장애물에 걸리지 않는 경우
            if(level <= (h- top[mid])){
                low = mid+1;
                idx = mid;
            }else {
                high = mid-1;
            }
        }
        return idx;
    }
}
