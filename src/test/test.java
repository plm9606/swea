package test;

public class test {
    public static void main(String[] args) {
        String s = "aabab";

        int cnt = 0, acc = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == 'a') {
                if (i - 1 >= 0 && s.charAt(i - 1) == 'a' && s.charAt(i + 1) == 'a') {
                    System.out.println(-1);
                    return;
                }
                if (s.charAt(i + 1) == 'a') {
                    if (acc == 0)
                        acc++;
                } else {
                    if (acc == 0) cnt++;
                }
                continue;
            }
            acc = 0;

            if (s.charAt(i + 1) == 'a') continue;
            cnt += 2;


        }

        //ab
        if (!s.startsWith("aa") && s.startsWith("a")) {
            cnt++;
        } else if (!s.startsWith("aa")) {
            cnt += 2;
        }

        if (!s.endsWith("aa") && s.endsWith("a")) {
            cnt++;
        } else if (!s.endsWith("aa")) {
            cnt += 2;
        }
        System.out.println(cnt);
    }
}
