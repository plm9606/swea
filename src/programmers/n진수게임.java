package programmers;

public class n진수게임 {
    public String solution(int n, int t, int m, int p) {
        String answer = "0";
        String res = "";
        int num = 1;
        while (answer.length() <= (t * m)) {
            answer += convert(num, n);
            num++;
        }
        // System.out.println(answer);
        for (int i = 0; i < t; i++) {
            // System.out.println((i*m-1+p));
            res += (answer.charAt(i * m - 1 + p) + "");
        }
        return res;
    }

    public String convert(int num, int n) {
        String s = "";
        while (num > 0) {
            int res = num % n;
            if (res >= 10) {
                if (res == 10) s += "A";
                else if (res == 11) s += "B";
                else if (res == 12) s += "C";
                else if (res == 13) s += "D";
                else if (res == 14) s += "E";
                else if (res == 15) s += "F";
            } else s += res;
            num = num / n;
        }
        return new StringBuilder(s).reverse().toString();
    }
}
