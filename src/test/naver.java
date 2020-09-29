import java.util.Arrays;

public class naver {
    static int[][] B;

    public static void main(String[] args) {
        new naver().solution(new int[][]{{0, 92}, {1, 20}, {2, 11}, {1, -81}, {3, 98}});
    }

    public void find(int level, int idx) {
        // 왼쪽 탐색
        if (level - 1 >= 0 && idx - 1 >= 0 && B[level][idx - 1] == -200) {
            B[level][idx - 1] = B[level - 1][idx - 1] - B[level][idx];
            find(level, idx - 1);
        }
        if (level - 1 >= 0 && idx + 1 <= level && B[level][idx + 1] == -200) {
            B[level][idx + 1] = B[level - 1][idx] - B[level][idx];
            find(level, idx + 1);
        }
    }

    public int[] solution(int[][] blocks) {
        int level = blocks.length;
        int[] answer = new int[level * (level + 1) / 2];

        B = new int[blocks.length][blocks.length];
        int check = level * (level + 1) / 2 - level;

        for (int[] arr : B) {
            Arrays.fill(arr, -200);
        }

        for (int i = 0; i < blocks.length; i++) {
            int a = blocks[i][0];
            int b = blocks[i][1];
            B[i][a] = b;
        }

        int idx = 0;
        for (int i = 0; i < blocks.length; i++) {
            int start = blocks[i][0];
            find(i, start);
            for (int j = 0; j <= i; j++) {
                answer[idx++] = B[i][j];
            }
        }

        System.out.println(Arrays.toString(answer));
//        while (check > 0) {
//            for (int i = 0; i < blocks.length; i++) {
//                for (int j = 0; j <= i; j++) {
//                    if (B[i][j] == -200) {
//                        if (i - 1 >= 0 && j - 1 >= 0) {
//                            if (B[i][j - 1] != -200 && B[i - 1][j - 1] != 200) {
//                                B[i][j] = B[i - 1][j - 1] - B[i][j - 1];
//                                check--;
//                                continue;
//                            }
//                        }
//                        if (i + 1 < blocks.length && j + 1 <= i) {
//                            if (B[i + 1][j] != -200 && B[j + 1][j + 1] != -200) {
//                                B[i][j] = B[i + 1][j] + B[i + 1][j];
//                                check--;
//                                continue;
//                            }
//                        }
//                        if (i - 1 >= 0 && j + 1 <= i) {
//                            if (B[i][j + 1] != -200 && B[i - 1][j] != -200) {
//                                B[i][j] = B[i - 1][j] - B[i][j + 1];
//                                check--;
//                                continue;
//                            }
//                        }
//
//
//                    }
//                }
//            }
//        }
        return answer;
    }
}