package baekjoon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Boj_2003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("res/boj2003.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int arr[] = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0, end = 0;
        int sum = 0, answer = 0;
        for (int i = 0; i < n; i++) {
            while (sum <= m && end < n) {
                if (sum == m) {
//                    end++;
                    break;
                }
                sum += arr[end];
                end++;
            }

            if (sum == m) {
                System.out.println(start + " " + end);
                answer++;
            }
            sum -= arr[start];
            start++;
        }

        System.out.println(answer);
    }
}
