package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 모의고사 {
    public static void main(String[] args) {
        int[] a = {6,6,6,6,6,6,6};//{1, 3, 2, 4, 2};
        int[] answer = solution(a);
        System.out.println(Arrays.toString(answer));
    }

        public static int[] solution(int[] answers) {
            List<Integer> answer = new ArrayList<>(3);
            int[][] students = {{1,2,3,4,5},{2,1,2,3,2,4,2,5},{3,3,1,1,2,2,4,4,5,5}};
            int[] correct = new int[3];


            for(int i=0; i< answers.length; i++){
                int a = answers[i];
                if(a == students[0][i%5]){
                    correct[0]++;
                }
                if (a == students[1][i%8]){
                    correct[1] ++;
                }
                if (a == students[2][i%10]){
                    correct[2] ++;
                }
            }
            int max = correct[0];
            answer.add(1);
            for(int i=1; i<correct.length; i++){
                if(max > correct[i]) {
                    continue;
                }else if (max == correct[i]){
                    answer.add(i+1);
                }
                else {
                    answer = new ArrayList<>(3);
                    max = correct[i];
                    answer.add(i+1);
                }
            }
            return convertIntegers(answer);
        }

    public static int[] convertIntegers(List<Integer> integers)
    {
        int[] ret = new int[integers.size()];
        for (int i=0; i < ret.length; i++)
        {
            ret[i] = integers.get(i).intValue();
        }
        return ret;
    }

}
