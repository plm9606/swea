package baekjoon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Boj_1748 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("res/boj11438.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int N = Integer.parseInt(br.readLine());
        int N = 100;
        int cnt = 1;
        long answer = 0;
        while (true) {
            if (N >= Math.pow(10, cnt)) {
                answer += (Math.pow(10, cnt) - Math.pow(10, cnt - 1)) * cnt;
            } else {
                answer += (N - Math.pow(10, cnt - 1) + 1) * cnt;
                break;
            }
            cnt++;
        }
        System.out.println(answer);
    }
}
