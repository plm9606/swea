package programmers.level1;

public class 가운데_글자_가져오기 {
    public String solution(String s) {
        String answer = "";
        int len = s.length();

        if(s.length()%2 ==0){
            int mid = s.length()/2;
            answer = s.substring(mid-1, mid+1);
        }else {
            int mid = (s.length()-1)/2;
            answer = s.substring(mid, mid+1);
        }
        return answer;
    }
}
