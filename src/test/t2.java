package test;

import java.util.Arrays;
import java.util.HashMap;

public class t2 {
    public static void main(String[] args) {
//        String[] S = {"abc", "bca", "dbe"};
//        String[] S = {"zzzz", "ferz", "zdsr", "fgtd"};
        String[] S = {"gr", "sd", "rg"};
        HashMap<Character, Integer> map = new HashMap<>();

        int N = S.length;
        int M = S[0].length();
        for (int i = 0; i < M; i++) {
            map.clear();
            for (int s = 0; s < N; s++) {
                char c = S[s].charAt(i);
                if (map.containsKey(c)) {
                    System.out.println(Arrays.toString(new int[]{map.get(c), s, i}));
                    return;
                }
                map.put(c, s);
            }
        }
    }
}
