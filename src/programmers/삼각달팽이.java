package programmers;

import java.util.Arrays;

public class 삼각달팽이 {
    public static void main(String[] args) {
        new 삼각달팽이().solution(6);
    }

    public int[] solution(int n) {
        int[] answer = new int[n * (n + 1) / 2];
        int[][] arr = new int[n][n];

        int mod = 0, y = -1, x = 0, num = 1;
        for (int i = n; i > 0; i--) {
            mod %= 3;
            int cnt = i;
            if (mod == 0) {
                while (cnt > 0) {
                    arr[++y][x] = num++;
                    cnt--;
                }
            } else if (mod == 1) {
                while (cnt > 0) {
                    arr[y][++x] = num++;
                    cnt--;
                }
            } else {
                while (cnt > 0) {
                    arr[--y][--x] = num++;
                    cnt--;
                }
            }
            mod++;
        }

        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == 0) break;
                answer[idx++] = arr[i][j];
            }
        }
        System.out.println(Arrays.toString(answer));
        return answer;
    }
}
