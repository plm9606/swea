package programmers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

public class 실패율 {
    public static void main(String[] args) {
        solution(5, new int[]{2, 1, 2, 6, 2, 4, 3, 3});
    }

    public static int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        int acc = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Fail> pq = new PriorityQueue<>();
        for (int i = 1; i <= N + 1; i++) {
            map.put(i, 0);
        }

        for (int stage : stages) {
            map.replace(stage, map.get(stage) + 1);
            acc += 1;
        }


        for (int i = 1; i <= N; i++) {
            int notClear = map.get(i);
            if (notClear == 0) pq.add(new Fail(i, 0));
            else pq.add(new Fail(i, (notClear / 1.0 / acc)));
            acc -= notClear;
        }

        for (int i = 0; i < N; i++) {
            Fail f = pq.poll();
            System.out.println(f.stage + " " + f.percent);
            answer[i] = f.stage;
        }


        System.out.println(Arrays.toString(answer));
        return answer;
    }
}

class Fail implements Comparable<Fail> {
    double percent;
    int stage;

    public Fail(int stage, double percent) {
        this.stage = stage;
        this.percent = percent;
    }

    @Override
    public int compareTo(Fail o) {
        if (this.percent > o.percent) return -1;
        else if (this.percent < o.percent) return 1;
        else {
            if (this.stage > o.stage) return 1;
            else if (this.stage < o.stage) return -1;
            else return 0;
        }
    }
}