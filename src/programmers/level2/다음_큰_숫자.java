package programmers.level2;

public class 다음_큰_숫자 {
    public static int solution(int n){
        int answer = 0;
        int count = Integer.bitCount(n);
        for(int i = n+1; ; i++){
            if(count == Integer.bitCount(i)){
                answer = i;
                break;
            }
        }
        return answer;
    }
}
