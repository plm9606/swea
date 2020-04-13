package programmers;

import java.util.*;

public class 같은_숫자는_싫어 {
    public static void main(String[] args) {
        int[] a = {1, 1, 3, 3, 0, 1, 1};
        System.out.println(Arrays.toString(solution(a)));
    }
    public static int[] solution(int []arr) {
        List<Integer> list = new ArrayList<>();

        for(int num: arr){
            if(list.size()>0 && list.get(list.size()-1) == num) continue;

            list.add(num);
        }

        int[] array = list.stream().mapToInt(i->i).toArray();
        return array;
    }

}
