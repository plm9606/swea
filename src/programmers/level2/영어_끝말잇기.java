package programmers.level2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class 영어_끝말잇기 {
    public static void main(String[] args) {
        String[] s = {"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"};
        String[] s1 = {"hello", "observe", "effect", "take", "either", "recognize", "encourage", "ensure", "establish", "hang", "gather", "refer", "reference", "estimate", "executive"};
        String[] s2 = {"land", "dream", "mom", "mom", "ror"};
        System.out.println(Arrays.toString(solution(2, s2)));
    }
    public static int[] solution(int n, String[] words) {
        int[] answer = {0,0};
        Set<String> set = new HashSet<>();
        char lastWord = words[0].charAt(0);
        for(int i =0; i<words.length; i++){
            if(i==0){
                set.add(words[i]);
            }else {
                if(words[i].length() == 1){
                    answer[0] = getNumber(n, i);
                    answer[1] = getRound(n, i);
                    break;
                }
                if(lastWord != words[i].charAt(0)){
                    System.out.println(lastWord + " != "+ words[i].charAt(0) +"  i: "+ i);
                    answer[0] = getNumber(n, i);
                    answer[1] = getRound(n, i);
                    break;
                }
                if(set.contains(words[i])){
                    System.out.println("이미 있어요 " + i);
                    answer[0] = getNumber(n, i);
                    answer[1] = getRound(n, i);
                    break;
                }
            }
            set.add(words[i]);
            lastWord = words[i].charAt(words[i].length()-1);
        }
        return answer;
    }

    public static int getNumber(int n, int i){
        int answer = (i+1)%n;
        return answer ==0 ?n: answer;
    }

    public static int getRound(int n, int i){
        return  (i/n)+1;
    }
}
