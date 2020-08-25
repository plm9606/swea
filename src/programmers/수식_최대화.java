package programmers;

import java.util.ArrayList;
import java.util.Arrays;

public class 수식_최대화 {

    static long PLUS = Long.MAX_VALUE, MINUS = Long.MAX_VALUE - 1, MUL = Long.MAX_VALUE - 2;
    static long[] gihos = new long[]{PLUS, MINUS, MUL};
    static ArrayList<Long> list;
    static long max;
    static int[] gihoCount;

    public static void main(String[] args) {
        String expression = "100-200*300-500+20";
        int acc = 0;
        gihoCount = new int[3];
        list = new ArrayList<>(100);
        max = 0;

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '-' || c == '+' || c == '*') {
                list.add((long) Integer.parseInt(expression.substring(acc, i)));
                acc = i + 1;
                if (c == '-') {
                    gihoCount[1] += 1;
                    list.add(MINUS);
                } else if (c == '+') {
                    gihoCount[0] += 1;
                    list.add(PLUS);
                } else if (c == '*') {
                    gihoCount[2] += 1;
                    list.add(MUL);
                }
            }
        }
        list.add((long) Integer.parseInt(expression.substring(acc, expression.length())));

//            System.out.println(Arrays.toString(gihoCount));
//            System.out.println(list.toString());

        permutation(new int[3], 0, 3);
        System.out.println(max);
    }

    public static void permutation(int[] order, int picked, int toPick) {
        if (toPick == picked) {
            System.out.println(Arrays.toString(order));
            ArrayList<Long> cp = new ArrayList<>();
            cp.addAll(list);
            int i = 0;
            int[] count = new int[3];
            System.arraycopy(gihoCount, 0, count, 0, gihoCount.length);
            while (cp.size() > 1) {
                if (cp.indexOf(gihos[order[i]]) >= 0) {
                    int idx = cp.indexOf(gihos[order[i]]);
                    long left = cp.get(idx - 1);
                    long operator = cp.get(idx);
                    long right = cp.get(idx + 1);
                    cp.remove(idx);
                    cp.remove(idx);
                    cp.set(idx - 1, calc(left, operator, right));
                    count[order[i]]--;
                }
                if (count[order[i]] == 0) {
                    i++;
                }
            }
            if (max < Math.abs(cp.get(0))) {
                max = Math.abs(cp.get(0));
            }
            return;
        }

        boolean[] check = new boolean[toPick];
        for (int i = 0; i < picked; i++) {
            check[order[i]] = true;
        }

        for (int i = 0; i < toPick; i++) {
            if (!check[i]) {
                order[picked] = i;
                permutation(order, picked + 1, toPick);
            }
        }
    }

    public static long calc(long left, long operator, long right) {
        if (operator == PLUS) {
            return left + right;
        } else if (operator == MINUS) {
            return left - right;
        } else {
            return left * right;
        }
    }

}
