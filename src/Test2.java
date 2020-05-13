import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test2 {
    public static void main(String[] args) {
//        System.out.println(solution("FRANCE","french"));
        System.out.println(solution(	"aaabb", "+aa+"));
    }
    public static int solution(String str1, String str2) {
        int answer = 0;
//        List<String> and = new ArrayList<>();
//        List<String> union = new ArrayList<>();

        int and=0, union = 0;

        Map<String, Integer> map1 = new HashMap();
        Map<String, Integer> map2 = new HashMap();

        str1 = str1.toUpperCase();
        str2 = str2.toUpperCase();

        for(int i=0; i<str1.length()-1; i++){
            if(str1.charAt(i) < 'A' || str1.charAt(i) > 'Z'){
                System.out.println("invalid " + str1.charAt(i));
                continue;
            }
            if(str1.charAt(i+1) < 'A' || str1.charAt(i+1) > 'Z'){
                System.out.println("invalid " + str1.charAt(i+1));
                continue;
            }
            String sub = str1.substring(i, i+2);
            if(map1.containsKey(sub)){
                map1.replace(sub, map1.get(sub)+1);
            }else {
                map1.put(sub, 1);
            }
        }

        for(int i=0; i<str2.length()-1; i++){
            if(str2.charAt(i) < 'A' || str2.charAt(i) > 'Z'){
                System.out.println("invalid " + str2.charAt(i));
                continue;
            }
            if(str2.charAt(i+1) < 'A' || str2.charAt(i+1) > 'Z'){
                System.out.println("invalid " + str2.charAt(i+1));
                continue;
            }
            String sub = str2.substring(i, i+2);
            if(map2.containsKey(sub)){
                map2.replace(sub, map2.get(sub)+1);
            }else {
                map2.put(sub, 1);
            }
        }

        for(String key: map1.keySet()){
            if(map2.containsKey(key)){
                and += Math.min(map1.get(key), map2.get(key));
                union += Math.max(map1.get(key), map2.get(key));
                map2.remove(key);
            }else {
                union += map1.get(key);
            }
        }

        for(String key: map2.keySet()){
            union += map2.get(key);
        }

        //교집합이 0은 0이고, 합집합이 0인 것이 65536 입니다.
        answer = (int)(((double)and/(double)union)*65536);
        return answer;
    }

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
