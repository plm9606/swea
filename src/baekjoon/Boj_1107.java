package baekjoon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Boj_1107 {
    static HashMap<Integer, Boolean> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("res/boj1107.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int min = 0;
        map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map.put(i, true);
        }
        if (M == 0) {
            System.out.println(Math.min(Math.abs(100 - N), possible(N)));
            return;
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            map.replace(Integer.parseInt(st.nextToken()), false);
        }

        if (N == 100) {
            System.out.println(0);
            return;
        }

        min = Math.abs(N - 100);
        for (int i = 0; i < 1000000; i++) {
            int count = possible(i);
            if (count > 0) {
//                min = Math.min(min, count + Math.abs(N - i));
                if (min > (count + Math.abs(N - i))) {
                    min = count + Math.abs(N - i);
                }
            }
        }

        System.out.println(min);

    }

    public static int possible(int n) {
        if (map.get(0) && n == 0) return 1;
        int cnt = 0;
        while (n > 0) {
            if (!map.get(n % 10)) return 0;
            n /= 10;
            cnt++;
        }
        return cnt;
    }
}
