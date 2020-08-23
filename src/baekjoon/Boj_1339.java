package baekjoon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Boj_1339 {
    static HashMap<Integer, Integer> map = new HashMap<>();
    static int max = 0;
    static char[][] numbers;
    static boolean[] visited = new boolean[10];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("res/boj1339.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        numbers = new char[N][];
        visited = new boolean[10];
        int idx = -1;
        for (int i = 0; i < N; i++) {
            numbers[i] = br.readLine().toCharArray();
            for (Character c : numbers[i]) {
                if (!map.containsKey(c - 65)) {
                    map.put(c - 65, idx + 1);
                    idx++;
                }
            }
        }
        solution(0, new int[map.size()]);
        System.out.println(max);
    }

    public static void solution(int level, int[] nums) {
        if (level == map.size()) {
            //수식 구하기
            int res = calculate(nums);
            if (max < res) max = res;
            return;
        }

        for (int i = 9; i >= 0; i--) {
            if (!visited[i]) {
                visited[i] = true;
                nums[level] = i;
                solution(level + 1, nums);
                visited[i] = false;
                nums[level] = -1;
            }
        }

    }

    public static int calculate(int[] nums) {
        int res = 0;
        for (char[] chars : numbers) {
            int value = 0;
            for (int i = 0; i < chars.length; i++) {
                value *= 10;
                value += nums[map.get(chars[i] - 65)];
            }
            res += value;
        }
        return res;
    }

}
