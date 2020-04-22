package programmers;

import java.util.Arrays;

public class Test1 {
    public static void main(String[] args) {
        System.out.println(solution("-1234"));

        System.out.println(Arrays.toString(solution(12345)));
    }

    public static int solution(String s) {
        int answer = 0;
        if(s.substring(0,1).equals("+")){
            answer = Integer.parseInt(s.substring(1));
        }else if(s.substring(0,1).equals("-")){
            answer = Integer.parseInt(s.substring(1)) *(-1);
        }else {
            answer = Integer.parseInt(s);
        }
        return answer;
    }

    public static int[] solution(long n){
        String str = Long.toString(n);
        int len  = str.length();
        int[] answer = new int[len];

        for(int i=str.length()-1; i>=0; i--){
            answer[len-1-i] = Integer.parseInt(str.substring(i, i+1));
        }
        return answer;
    }
}
