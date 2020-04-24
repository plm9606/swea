package programmers.level2;
import java.util.*;

public class 뉴스_클러스터링 {
    public int solution(String str1, String str2) {
        int answer = 0;

        int and=0, union = 0;

        Map<String, Integer> map1 = new HashMap();
        Map<String, Integer> map2 = new HashMap();

        str1 = str1.toUpperCase();
        str2 = str2.toUpperCase();

        for(int i=0; i<str1.length()-1; i++){
            if(str1.charAt(i) < 'A' || str1.charAt(i) > 'Z'){
                continue;
            }
            if(str1.charAt(i+1) < 'A' || str1.charAt(i+1) > 'Z'){
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
                continue;
            }
            if(str2.charAt(i+1) < 'A' || str2.charAt(i+1) > 'Z'){
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

        if(union == 0) return 65536;
        if(and == 0) return 0;
        answer = (int)(((double)and/(double)union)*65536);
        return answer;
    }
}
