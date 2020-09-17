package baekjoon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj_14889 {
    static int min, N;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("res/boj14889.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        min = Integer.MAX_VALUE;
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        dfs(N / 2, 0, 0, 0, new int[N / 2], new int[N / 2]);
        System.out.println(min);

        bitmask();
    }

    // 상태공간트리 이용
    private static void dfs(int toChoice, int idx, int startIdx, int linkIdx, int[] start, int[] link) {

        if (startIdx == N / 2 && linkIdx == N / 2) {
            int res = Math.abs((sum(start) - sum(link)));
            min = Math.min(min, res);
            return;
        }

        // 순서대로 i번이 start팀이 되는 경우, link팀이 되는 경우를 구한다.
        if (startIdx < N / 2) {
            start[startIdx] = idx;
            dfs(toChoice, idx + 1, startIdx + 1, linkIdx, start, link);
        }
        if (linkIdx < N / 2) {
            link[linkIdx] = idx;
            dfs(toChoice, idx + 1, startIdx, linkIdx + 1, start, link);
        }
    }

    private static void bitmask() {
        int cnt = N / 2;

        ArrayList<Integer> s = new ArrayList<>(), l = new ArrayList<>();
        // i = 2^(N/2+1)-1(1을 N/2+1번); i<2^N
        // 0111 ~ 1111 까지
        for (int i = (1 << (N / 2 + 1)) - 1; i < (1 << N); i++) {
            System.out.println(Integer.toBinaryString(i));
            s.clear();
            l.clear();
            // n개의 비트에 대해 검사
            for (int j = 0; j < N; j++) {
                //S & (1 << i): s가 i에 포함되는지 체크
                // &(AND): 두 비트가 모두 1일때 1을 반환
                // 비트 j가 0이면 start팀에 넣고
                if ((i & (1 << j)) != 0) s.add(j);
                    // 1이면 list팀에 넣는다
                else l.add(j);
            }

            if (s.size() == N / 2 && l.size() == N / 2) {
                min = Math.min(min, Math.abs((sum(s) - sum(l))));
            }
        }

    }

    public static int sum(int[] team) {
        int sum = 0;
        for (int i = 0; i < team.length - 1; i++) {
            for (int j = i + 1; j < team.length; j++) {
                sum += arr[team[i]][team[j]] + arr[team[j]][team[i]];
            }
        }
        return sum;
    }

    public static int sum(List<Integer> team) {
        int sum = 0;
        for (int i = 0; i < team.size() - 1; i++) {
            for (int j = i + 1; j < team.size(); j++) {
                int i1 = team.get(i), i2 = team.get(j);
                sum += arr[i1][i2] + arr[i2][i1];
            }
        }
        return sum;
    }

}
