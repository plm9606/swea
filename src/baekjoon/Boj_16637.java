package baekjoon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Boj_16637 {
    static ArrayList<Long> nums = new ArrayList<>();
    static ArrayList<Character> symbols = new ArrayList<>();
    static long PLUS = 1l << 32, MINUS = 1l << 32 - 1, MUL = 1l << 32 - 2, max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("res/boj16637.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String expression = br.readLine();
        for (int i = 0; i < N; i++) {
            if (i % 2 == 0) {
                nums.add(Long.parseLong(expression.charAt(i) + ""));
            } else {
                symbols.add(expression.charAt(i));
            }
        }

        max = calc(expression);
        for (int i = 1; i < N / 2; i++)
            combination(new boolean[nums.size()], i, 0);

        System.out.println(max);
    }

    public static void combination(boolean[] bit, int toChoice, int target) {
        if (toChoice == 0) {
//            System.out.println(Arrays.toString(bit));
            Queue<Long> q = new LinkedList<>();
            String s = "";
            int symbolIdx = 0;
            for (int i = 0; i < bit.length; i++) {
                if (bit[i]) {
                    s += ("(" + nums.get(i) + symbols.get(symbolIdx++) + nums.get(++i) + ")");
                    if (symbolIdx < symbols.size()) s += symbols.get(symbolIdx++);
                } else {
                    if (symbolIdx < symbols.size()) s += ("" + nums.get(i) + symbols.get(symbolIdx++));
                    else s += nums.get(i);
                }
            }
//            System.out.println(s);
            long res = calc(s);
            max = Math.max(max, res);
            return;
        }

        for (int i = target; i < bit.length; i++) {
            if (i - 1 < 0) {
                bit[i] = true;
                combination(bit, toChoice - 1, i + 1);
                bit[i] = false;
            } else {
                if (bit[i - 1] == false && i + 1 < bit.length) {
                    bit[i] = true;
                    combination(bit, toChoice - 1, i + 1);
                    bit[i] = false;
                }
            }
        }
    }

    public static long calc(String s) {
        char[] chars = s.toCharArray();
        Queue<Long> q = new LinkedList<>();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                Long acc = Long.parseLong(chars[++i] + "");
                char symbol = chars[++i];
                if (symbol == '+') acc += Long.parseLong(chars[++i] + "");
                else if (symbol == '-') acc -= Long.parseLong(chars[++i] + "");
                else acc *= Long.parseLong(chars[++i] + "");
                q.add(acc);
                i++;
            } else if (chars[i] == '+') {
                q.add(PLUS);
            } else if (chars[i] == '-') {
                q.add(MINUS);
            } else if (chars[i] == '*') {
                q.add(MUL);
            } else {
                q.add(Long.parseLong(chars[i] + ""));
            }
        }

        long acc = q.poll();
        while (!q.isEmpty()) {
            Long item = q.poll();
            if (item == PLUS) {
                acc += q.poll();
            } else if (item == MINUS) {
                acc -= q.poll();
            } else if (item == MUL) {
                acc *= q.poll();
            }
        }

//        System.out.println(acc);
        return acc;
    }
}

class Boj_16637_B {
    static ArrayList<Integer> nums = new ArrayList<>();
    static ArrayList<Character> symbols = new ArrayList<>();
    static int N;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("res/boj16637.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        String expression = br.readLine();
        for (int i = 0; i < N; i++) {
            if (i % 2 == 0) {
                nums.add(Integer.parseInt(expression.charAt(i) + ""));
            } else {
                symbols.add(expression.charAt(i));
            }
        }
        dfs(0, nums.get(0));
        System.out.println(max);
    }

    public static void dfs(int idx, int res) {
        // 모든 연산자를 사용했으면
        if (idx >= N / 2) {
            max = Math.max(max, res);
            return;
        }
        // 괄호를 치지 않는 경우 이전 값이랑 연산
        int res1 = calc(res, symbols.get(idx), nums.get(idx + 1));
        dfs(idx + 1, res1);

        // 괄호를 치는 경우
        if (idx + 1 < N / 2) {
            // 다음 연산자에 괄호를 쳐서 이전 값과 연산
            int res2 = calc(res, symbols.get(idx), calc(nums.get(idx + 1), symbols.get(idx + 1), nums.get(idx + 2)));
            dfs(idx + 2, res2);
        }
    }


    private static int calc(int a, char s, int b) {
        if (s == '+') {
            return a + b;
        } else if (s == '-') {
            return a - b;
        }
        return a * b;
    }
}
