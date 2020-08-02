package programmers;
import java.util.*;
public class 압축 {
    public static void main(String[] args) {
        String msg = "TOBEORNOTTOBEORTOBEORNOT";
            HashMap<String, Integer> map = new HashMap<>();
            ArrayList<Integer> output = new ArrayList<>();
            int mapIdx = 27;
            int idx = 1;
            for(int i=65; i<=90; i++){
                map.put(Character.toString ((char) i)+"", idx++);
            }

            int msgIdx = 0;

            while(msgIdx < msg.length()){
                int len = 0;
                String w="";
                 while(msgIdx+len+1<=msg.length()){
                     if(!map.containsKey(msg.substring(msgIdx, msgIdx+len+1))) break;
                     else w = msg.substring(msgIdx, msgIdx+(len++)+1);
                 }
                System.out.print("w: " + w);
                output.add(map.get(w));
                if(msgIdx+len < msg.length()){
                    String c = msg.substring(msgIdx+len, msgIdx+len+1);
                    if(!map.containsKey(w+c))map.put(w+c,mapIdx++);
                    System.out.println(", c: " + c + ", w+c: "+ w+c + ":" + map.get(w+c));
                }else break;
                msgIdx = msgIdx + len;
            }

            int[] answer = new int[output.size()];
            for(int i=0; i<output.size(); i++){
                answer[i] = output.get(i);
            }

        System.out.println(output);
        }

}
