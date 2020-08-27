package baekjoon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_12015 {
    static int[] sort;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("res/boj12015.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        sort = new int[N+1];
        int max = -1;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        sort[0] = -1;
        int idx = 0;
        for(int i=0; i<N; i++){
            if(arr[i] > sort[idx]){
                sort[idx+1] = arr[i];
                idx++;
            }else if(arr[i] == sort[idx]){
                continue;
            }
            else {
                int res = search(1, idx, arr[i]);
                if(sort[res] == arr[i]){
//                    Arrays.fill(sort, res+1, idx+1, 0);
//                    idx=res;
                }
                else sort[res] = arr[i];
            }
            if(max < idx){
                max = idx;
            }
        }

        System.out.println(max);
    }

    public static int search(int left, int right, int target){
        int answer = -1;
        while (left <= right){
            int mid = (left+right)/2;
            if(target <= sort[mid]){
                answer = mid;
                right = mid-1;
            }else left = mid+1;
        }
        return answer;
    }
}
