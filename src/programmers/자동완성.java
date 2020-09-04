package programmers;

import java.util.Arrays;

public class 자동완성 {
    static int[] LCP;

    public static void main(String[] args) {
        solution(new String[]{"go", "gone", "guild"});
    }

    public static int solution(String[] words) {
        int n = words.length;
        Arrays.sort(words);
        LCP = new int[n];
        //LCP[0] = lcp(words[0], words[1])>0?-1:0;
        LCP[0] = lcp(words[0], words[1]);
        LCP[n - 1] = lcp(words[n - 1], words[n - 2]);
        for (int i = 1; i < n - 1; i++) {
            // LCP[i] = Math.max(lcp(words[i], words[i-1]), lcp(words[i], words[i+1]));
            // LCP[i] = LCP[i]==words[i].length()? -1:LCP[i];
            LCP[i] = lcp(words[i - 1], words[i], words[i + 1]);
        }

        System.out.println(Arrays.toString(LCP));
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (LCP[i] == 0) {
                ans += 1;
            } else if (LCP[i] == -1) {
                ans += words[i].length();
            } else {
                ans += (LCP[i] + 1);
            }
        }
        return ans;
    }

    public static int lcp(String x, String y) {
        int len = 0;
        int min = Math.min(y.length(), x.length());
        // while(len<min && x.charAt(len)==y.charAt(len)){
        while (len < min && x.substring(0, len).equals(y.substring(0, len))) {
            len++;
        }
        return len;
    }

    public static int lcp(String x, String y, String z) {
        for (int i = 1; i < x.length() + 1; i++) {
            if (i == x.length()) return x.length();
            if (x.charAt(i) != y.charAt(i) && y.charAt(i) != z.charAt(i)) return i;
        }

        return y.length();
    }
}


class 자동완성2 {
    static int[] LCP;

    public static int solution(String[] words) {
        int n = words.length;
        Arrays.sort(words);
        LCP = new int[n];
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                LCP[i] = lcp(words[i], words[i + 1]);
            } else if (i == n - 1) {
                LCP[i] = lcp(words[i], words[i - 1]);
            } else
                LCP[i] = Math.max(lcp(words[i], words[i - 1]), lcp(words[i], words[i + 1]));
            LCP[i] = (LCP[i] > 0 && LCP[i] == words[i].length()) ? LCP[i] : LCP[i] + 1;
        }

        return Arrays.stream(LCP).sum();
    }

    public static int lcp(String x, String y) {
        int len = 0;
        int min = Math.min(y.length(), x.length());
        while (len < min && x.charAt(len) == y.charAt(len)) {
            len++;
        }
        return len;
    }
}