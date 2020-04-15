package programmers.level2;

import java.util.HashMap;
import java.util.Map;

public class _124_나라의_숫자 {
    public static void main(String[] args) {
        solution(18);
    }
    static String answer = "";
    static String[] numbers = {"1","2","4"};

    public static String solution(int n){
        int len = 1;
        double acc = 0;
        while (true){
            acc += Math.pow(3, len);
            if(n <= acc){
                acc = acc -  Math.pow(3, len) + 1;
                break;
            }
            len++;
        }
        func((int)(n-acc), len);
        return answer;
    }

    public static void func(int remainder,int len){
        if(len == 1){
            answer = numbers[remainder]+answer;
            return;
        }

        int curQuotient = (int)(remainder/Math.pow(3, len-1));
        int curRemainder = (int)(remainder%Math.pow(3, len-1));
        func(curRemainder, --len);
        answer = numbers[curQuotient]+answer;
    }
}
