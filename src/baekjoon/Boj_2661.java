package baekjoon;

import java.io.IOException;

public class Boj_2661 {
    static int n;
    static boolean STOP = false;

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        n = Integer.parseInt(br.readLine());

        n = 10;
        make(10, new int[]{2, 1, 2, 3, 1, 2, 1, 2, 3, 1, 0});
    }

    public static void make(int level, int[] num) {
        if (STOP) return;
        if (level >= 2) {
            int last = level - 1;
            int idx = level - 2;
            loop:
            while (idx >= 0) {
                if (num[idx] == num[last]) {
                    int diff = last - idx;
                    boolean matched = true;
                    for (int i = last - 1; i > idx; i--) {
                        if (i - diff < 0) break loop;
                        if (num[i] != num[i - diff]) {
                            matched = false;
                            break;
                        }
                    }
                    if (matched) {
                        return;
                    }

                }
                idx--;
            }
        }
        if (level == n) {
//            System.out.println(Arrays.toString(num));
            for (int i : num) {
                System.out.print(i);
            }

            STOP = true;
            return;
        }


        num[level] = 1;
        make(level + 1, num);


        num[level] = 2;
        make(level + 1, num);

        num[level] = 3;
        make(level + 1, num);

    }
}
