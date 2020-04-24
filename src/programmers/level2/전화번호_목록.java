package programmers.level2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 전화번호_목록 {
    public static boolean solution(String[] phone_book) {
        boolean answer = true;
        String[] s = new String[phone_book.length];
        Map<String, List<Integer>> map = new HashMap<>();

        for(int i=0; i< phone_book.length-1; i++){
            for(int j=i+1; j<phone_book.length; j++){
                if(phone_book[i].startsWith(phone_book[j]))
                    return false;
                if(phone_book[j].startsWith(phone_book[i]))
                    return false;
            }
        }
        return answer;
    }
}
