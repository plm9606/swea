package programmers.level2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class 오픈채팅방 {
    private static String ENTER = "Enter";
    private static String LEAVE = "Leave";
    private static String CHANGE = "Change";

    public static void main(String[] args) {
        String[] s = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
        System.out.println(Arrays.toString(solution(s)));
    }
    public static String[] solution(String[] record) {
        Map<String, String> idMap = new HashMap<>();
        int count=0;

        for(String log: record){
            String[] splitLog = log.split(" ");
            if(splitLog[0].equals(ENTER)){
                idMap.put(splitLog[1], splitLog[2]);
                count++;
            }else if (splitLog[0].equals(CHANGE)){
                idMap.put(splitLog[1], splitLog[2]);
            }else{
                count++;
            }
        }

        String[] answer = new String[count];
        int k=0;
        for(int i=0; i<record.length; i++){
            String[] splitLog = record[i].split(" ");
            String res = "";
            if(splitLog[0].equals(ENTER)){
                res += idMap.get(splitLog[1]);
                res += "님이 들어왔습니다.";
                answer[k] = res;
                k++;
            }else if(splitLog[0].equals(LEAVE)){
                res += idMap.get(splitLog[1]);
                res += "님이 나갔습니다.";
                answer[k] = res;
                k++;
            }
        }
        return answer;
    }
}
