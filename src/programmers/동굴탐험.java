package programmers;

import java.util.*;

public class 동굴탐험 {

    static ArrayList<LinkedList<Integer>> pathList = new ArrayList<>();
    static boolean[] visit;
    static HashSet<Integer> first = new HashSet<>();
    static HashSet<Integer> last = new HashSet<>();
    static HashMap<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) {
        solution(9, new int[][]{{0, 1}, {0, 3}, {0, 7}, {8, 1}, {3, 6}, {1, 2}, {4, 7}, {7, 5}}, new int[][]{{8, 5}, {6, 7}, {4, 1}});
    }

    static public boolean solution(int n, int[][] path, int[][] order) {
        boolean answer = true;
        visit = new boolean[n];

        for (int i = 0; i < n; i++) {
            pathList.add(new LinkedList<>());
        }


        for (int[] p : path) {
            pathList.get(p[0]).add(p[1]);
            pathList.get(p[1]).add(p[0]);
        }

        for (int i = 0; i < order.length; i++) {
            int[] o = order[i];
            map.put(o[0], i);
            last.add(o[1]);
        }

        Deque<Integer> dq = new LinkedList<>();
        dq.add(0);

        while (!dq.isEmpty()) {
            int cur = dq.pollLast();
            if (last.contains(cur)) {
                int cnt = dq.size();
                boolean GOABLE = false;
                dq.addFirst(cur);
                while (cnt > 0) {
                    int cave = dq.pollLast();
                    if (last.contains(cave)) {
                        dq.addFirst(cave);
                    } else {
                        cur = cave;
                        GOABLE = true;
                        break;
                    }
                    cnt--;
                }
                if (!GOABLE) {
                    answer = false;
                    break;
                }
            }

            if (map.containsKey(cur)) {
                int idx = map.get(cur);
                map.remove(cur);
                last.remove(order[idx][1]);
            }
            visit[cur] = true;
            for (int p : pathList.get(cur)) {
                if (!visit[p]) {
                    dq.addLast(p);
                }
            }

        }
        return answer;
    }
}