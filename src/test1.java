import java.util.Arrays;

public class test1 {
    public static void main(String[] args) {
//        new test1().solution(new int[][]{{1, 1, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 1}, {1, 1, 1, 1}});
//        new test1().solution(new int[][]{{1, 1, 1, 1, 1, 1, 1, 1}, {0, 1, 1, 1, 1, 1, 1, 1}, {0, 0, 0, 0, 1, 1, 1, 1}, {0, 1, 0, 0, 1, 1, 1, 1}, {0, 0, 0, 0, 0, 0, 1, 1}, {0, 0, 0, 0, 0, 0, 0, 1}, {0, 0, 0, 0, 1, 0, 0, 1}, {0, 0, 0, 0, 1, 1, 1, 1}});
        new test1().solution(new int[][]{
                {1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},

        });

    }

    public int[] solution(int[][] arr) {

        int dfd = 8;
        Integer.bitCount(dfd);

        int[] answer = {};
        int len = arr.length;
        int[][][][] dp = new int[len / 2 + 1][len / 2 + 1][len / 2 + 1][2];
        int[] dy = {0, 0, 1, 1}, dx = {0, 1, 0, 1};
        for (int y = 0; y < len; y += 2) {
            for (int x = 0; x < len; x += 2) {
                int yy = y / 2, xx = x / 2;
                for (int i = 0; i < 4; i++) {
                    int n = arr[y + dy[i]][x + dx[i]];
                    if (n == 0) dp[yy][xx][1][0] += 1;
                    else dp[yy][xx][1][1] += 1;
                }
                // int res = arr[y][x]+arr[y+1][x]+arr[y][x+1]+arr[y+1][x+1];
                // if(res==4)res=1;
                // dp[y/2][x/2][1] = res;
                if (dp[yy][xx][1][0] == 4) dp[yy][xx][1][0] = 1;
                if (dp[yy][xx][1][1] == 4) dp[yy][xx][1][1] = 1;
            }
        }

        for (int i = 4; i <= len; i *= 2) {
            for (int y = 0; y < len; y += i) {
                for (int x = 0; x < len; x += i) {
                    int yy = y / 2, xx = x / 2, ii = (int) (Math.log10(i) / Math.log10(2));
                    int initial = 3;
                    for (int d = 0; d < 4; d++) {
                        int[] n = dp[yy + dy[d] * (i / 4)][xx + dx[d] * (i / 4)][ii - 1];
                        if (initial >= 0) {
                            if (n[0] == 1 && n[1] == 0) {
                                if (initial == 3 || initial == 0) initial = 0;
                                else initial = -1;
                            } else if (n[0] == 0 && n[1] == 1) {
                                if (initial == 3 || initial == 1) initial = 1;
                                else initial = -1;
                            } else initial = -1;
                        }
                        dp[yy][xx][ii][0] += n[0];
                        dp[yy][xx][ii][1] += n[1];
                    }
                    if (initial == 0) dp[yy][xx][ii][0] = 1;
                    else if (initial == 1) dp[yy][xx][ii][1] = 1;
                }
            }
        }
        System.out.println(Arrays.toString(dp[0][0][(int) (Math.log10(len) / Math.log10(2))]));
        return answer;
    }
}
