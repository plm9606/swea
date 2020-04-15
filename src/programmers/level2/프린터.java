package programmers.level2;

import java.util.*;

public class 프린터 {
    public static void main(String[] args) {
        int[] arr = {1,1,1,1,1,1};
        System.out.println(solution2(arr, 0));
    }
    public static int solution(int[] priorities, int location) {
        int answer = 0;

        PriorityQueue priorityQueue = new PriorityQueue<>(Collections.reverseOrder());

        for(int i: priorities){
            priorityQueue.add(i);
        }

        while (!priorityQueue.isEmpty()){
            for(int i=0; i<priorities.length; i++){
                if(priorities[i] == ((Integer) priorityQueue.peek()).intValue()){
                    answer++;
                    if(i== location){
                        return answer;
                    }
                    priorityQueue.poll();
                }
            }
        }
        return answer;
    }

    public static int solution2(int[] priorities, int location){
        int answer = 0;
        Queue<Integer> queue = new LinkedList<>();

        for(int i: priorities){
            queue.add(i);
        }

        Arrays.sort(priorities);

        while (!queue.isEmpty()){
            int priority = priorities[priorities.length-1-answer];
            int current = queue.poll();

            System.out.println("cur: " + current + ", pri["+(priorities.length-1-answer)+"]: " + priority);
            if(current == priority){
                answer++;
                location--;
                if(location<0) break;
            }else {
                queue.add(current);
                location--;
                if(location <0){
                    location = queue.size() -1 ;
                }
            }
        }
        return answer;
    }
}
