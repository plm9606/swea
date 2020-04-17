package programmers.level2;

public class 문자열_압축 {
    public static void main(String[] args) {
        System.out.println(solution("a"));
    }
    public static int solution(String s) {
        int answer = Integer.MAX_VALUE;

        for(int chunk=1; chunk<=s.length()/2; chunk++){
            String cur = s.substring(0, chunk);
            String pre = "";
            String curAnswer = "";
            int count = 1;

            for(int i=chunk; i<=s.length(); i+=chunk){
                if(i+chunk < s.length()) {
                    pre = s.substring(i, i + chunk);
                }else {
                    pre = s.substring(i);
                }

                if(cur.equals(pre)){
                    count++;
                }else {
                    curAnswer += ((count >1 ? Integer.toString(count) : "") + cur);
                    count =1;
                    cur = pre;
                }
            }

            // chunk단위로 문자열이 떨어질 경우 마지막 문자열 처리해주어야함

            // 마지막 chunk가 cur과 같은 경우 중복처리를 해준다
            // abcabcabc
            if(count >1){
                    curAnswer += (count + cur);
            }else {
                // abcabcadd
                curAnswer += pre;
            }

            if(answer > curAnswer.length()){
                answer = curAnswer.length();
            }
        }

        // string이 1자리일 경우 처리
        answer  = answer == Integer.MAX_VALUE ? 1 : answer;

        return answer;
    }
}
