package baekjoon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_17779 {
    static int min = Integer.MAX_VALUE, N;
    static int[][] arr, check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("res/boj17779.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1][N + 1];
        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int x = 1; x <= N; x++) {
            for (int y = 1; y <= N; y++) {
                for (int d1 = 1; d1 <= N; d1++) {
                    for (int d2 = 1; d2 <= N; d2++) {
                        if (x + d1 + d2 <= N && y - d1 >= 1 && y - d1 < y && y + d2 > y && y + d2 <= N) {
                            find(x, y, d1, d2);
                        }
                    }
                }
            }
        }

        System.out.println(min);
    }

    public static void find(int x, int y, int d1, int d2) {
        int[] section = new int[5];
        check = new int[N + 1][N + 1];

        int left = y, right = y, rr = x;
        boolean turnL = false, turnR = false;
        do {
            check[rr][left] = 5;
            check[rr][right] = 5;

            // y-d1+d2==x인 경우 처음부터 break되는 오류 있었다. rr>x를 추가
            if (rr > x && left == right && right == y - d1 + d2) {
                section[4] += arr[rr][left];
                break;
            }

            // 5구역의 한 row의 시작과 끝을 탐색하면서 계산
            for (int i = left; i <= right; i++) {
                section[4] += arr[rr][i];
                check[rr][i] = 5;
            }

            // 다음 row로
            rr++;

            if (turnL) {
                left++;
            } else {
                left--;
                // 왼쪽 포인터가 꼭지점에 다다르면 방향을 바꾼다.
                if (left == y - d1) turnL = true;
            }
            if (turnR) right--;
            else {
                right++;
                // 오른쪽 포인터가 꼭지점에 다다르면 방향을 바꾼다.
                if (right == y + d2) turnR = true;
            }
        } while (true);


        for (int i = 1; i < x + d1; i++) {
            for (int c = 1; c <= y; c++) {
                if (check[i][c] == 5) break;
                if (check[i][c] == 0) section[0] += arr[i][c];
            }
        }

        for (int r = 1; r <= x + d2; r++) {
            for (int c = y + 1; c <= N; c++) {
                if (check[r][c] == 5) continue;
                section[1] += arr[r][c];
            }
        }

        for (int r = x + d1; r <= N; r++) {
            for (int c = 1; c < y - d1 + d2; c++) {
                if (check[r][c] == 5) break;
                section[2] += arr[r][c];
            }
        }

        for (int r = x + d2 + 1; r <= N; r++) {
            for (int c = y - d1 + d2; c <= N; c++) {
                if (check[r][c] == 5) continue;
                section[3] += arr[r][c];
            }
        }

        Arrays.sort(section);
        if (min > (section[4] - section[0])) System.out.println(x + " " + y + " " + d1 + " " + d2);
        min = Math.min(min, (section[4] - section[0]));
    }
}
