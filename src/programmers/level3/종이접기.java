package programmers.level3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 종이접기 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(4)));
    }
    public static int[] solution(int n) {
        boolean ZERO = true;

        List<Integer> res = new ArrayList<>();
        res.add(0);

        for(int i=1; i<n; i++){
            List<Integer> temp = new ArrayList<>();
            int idx=0;
            ZERO = true;
            do{
                if(idx == res.size()){
                    int val = ZERO?0:1;
                    temp.add(val);
                    break;
                }

                if(ZERO){
                    temp.add(0);
                    temp.add(res.get(idx));
                    ZERO = false;
                }else {
                    temp.add(1);
                    temp.add(res.get(idx));
                    ZERO = true;
                }
                idx++;
            }while (idx <= res.size());
            res = temp;
        }

        System.out.println(res.toString());

        int[] answer = new int[res.size()];
        for(int i=0; i< res.size(); i++){
            answer[i] = res.get(i);
        }
        return answer;
    }
}
