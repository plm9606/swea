package programmers.level1;

public class 이천십육년 {
    public static void main(String[] args) {
        System.out.println(solution(12,15));
    }
    public static String solution(int a, int b) {
        int answer=b;
        String[] days = {"SUN","MON","TUE","WED","THU","FRI","SAT"};
        int[] month = {0,31,29,31,30,31,30,31,31,30,31,30,31};

        for(int i=0; i<a; i++){
            answer+=month[i];
        }

        answer = (answer+4)%7;
        return days[answer];
    }
}
