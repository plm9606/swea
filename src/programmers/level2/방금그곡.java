package programmers.level2;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class 방금그곡 {
    public static void main(String[] args) {
        String[] s = {"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"};
        String[] ss = {"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:6,WORLD,CA#BC#C#"};
        System.out.println(solution("C#C",ss));
    }
    public static String solution(String m, String[] musicinfos) {
        String answer = "";
        int answerPlayTime = 0;
        String[] mArr = getSong(m);

        for(String s: musicinfos){
            String[] arr = s.split(",");
            String[] songArr = getSong(arr[3]);

            int playTime = getPlayTime(arr[0], arr[1]);
            int musicTime = songArr.length;

            String fullVersion = getSong(playTime, musicTime, songArr, arr[3]);
            String[] fullArr = getSong(fullVersion);

            for(int i=0; i< fullArr.length; i++){
                if(Arrays.deepEquals(Arrays.copyOfRange(fullArr, i,i+mArr.length), mArr)){
                    if(answerPlayTime < playTime){
                        answer = arr[2];
                        answerPlayTime = playTime;
                    }
                }
            }
        }

        if(answer == "") answer = "(None)";
        return answer;
    }

    private static String[] getSong(String songStr) {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < songStr.length(); i++) {
            if (songStr.charAt(i) == '#') {
                list.set(list.size() - 1, list.get(list.size() - 1) + "#");
            } else {
                list.add(songStr.charAt(i) + "");
            }
        }
        return list.toArray(new String[0]);
    }

    public static int getPlayTime(String start, String end){
        String[] s = start.split(":");
        String[] e = end.split(":");

        int hour = Integer.parseInt(e[0])- Integer.parseInt(s[0]);
        int min = Integer.parseInt(e[1])- Integer.parseInt(s[1]);

        return hour*60+min;
    }

    public static String getSong(int playTime, int musicTime, String[] songArr, String songStr){
        String answer = "";
        if(playTime > musicTime){
            for(int i=0; i<playTime/musicTime; i++){
                answer += songStr;
            }
            for(int i=0; i<playTime%musicTime; i++){
                answer += songArr[i];
            }
        }else {
            for(int i=0; i<playTime; i++){
                answer += songArr[i];
            }
        }

        return answer;
    }
}
