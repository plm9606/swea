package programmers;

import java.util.ArrayList;
import java.util.HashSet;

public class 풍선터트리기 {
    static int answer;
    static boolean choiceable = true;
    static HashSet<Integer> set = new HashSet<>();

    public static void main(String[] args) {
        new 풍선터트리기().solution(new int[]{-16, 27, 65, -2, 58, -92, -71, -68, -61, -33});
    }

    public int solution(int[] a) {
        int answer = 0;
        HashSet<Integer> set = new HashSet<Integer>();
        int min = a[0];
        for (int i = 1; i < a.length; i++) {
            set.add(min);
            min = Math.min(min, a[i]);
        }
        min = a[a.length - 1];
        for (int i = a.length - 2; i >= 0; i--) {
            set.add(min);
            min = Math.min(min, a[i]);
        }
        return set.size();
    }

    public int solution2(int[] a) {
        int l = a[0], r = a[a.length - 1], answer = 2;

        for (int x = 1; x < a.length - 1; x++) {
            if (a[x] < l) {
                l = a[x];
                answer++;
            }
            if (a[a.length - 1 - x] < r) {
                r = a[a.length - 1 - x];
                answer++;
            }
        }
        if (l == r) answer--;
        return answer;
    }

    public void find(ArrayList<Integer> balloons) {
        // 풍선이 1개 남았고 아직 count하지 않은 풍선이면
        if (balloons.size() == 1 && set.contains(balloons.get(0))) {
            answer++;
            set.remove(balloons.get(0));
            return;
        }
        // 모든 풍선을 count 했으면
        if (set.size() == 0) return;

        for (int i = 0; i < balloons.size(); i++) {
            // 왼쪽과 쌍이 된다
            if (i - 1 >= 0) {
                // 현재 풍선이 더 크면
                if (balloons.get(i) > balloons.get(i - 1)) {

                    ArrayList<Integer> newBalloons = new ArrayList<>(balloons);
                    newBalloons.remove(i);
                    find(newBalloons);
                }
                if (choiceable) {
                    ArrayList<Integer> newBalloons = new ArrayList<>(balloons);
                    newBalloons.remove(i);
                    choiceable = false;
                    find(newBalloons);
                    choiceable = true;
                }
            }

            // 오른쪽과 쌍이 된다.
            if (i + 1 < balloons.size()) {
                // 현재 풍선이 더 크면
                if (balloons.get(i) > balloons.get(i + 1)) {
                    ArrayList<Integer> newBalloons = new ArrayList<>(balloons);
                    newBalloons.remove(i);
                    find(newBalloons);
                }
                if (choiceable) {
                    ArrayList<Integer> newBalloons = new ArrayList<>(balloons);
                    newBalloons.remove(i);
                    choiceable = false;
                    find(newBalloons);
                    choiceable = true;
                }
            }
        }

    }
}
