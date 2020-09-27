import java.util.ArrayList;
import java.util.HashMap;

public class naver2 {
    static int total;
    static HashMap<Integer, ArrayList<Integer>> map;
    static int min = Integer.MAX_VALUE, n;

    public static void main(String[] args) {
        n = 19;
        int[][] edges = {{0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 5}, {2, 6}, {2, 7}, {3, 8}, {3, 9}, {3, 10}, {4, 11}, {4, 12}, {4, 13}};
//        int[][] edges = {{0, 1}, {0, 2}, {0, 3}, {1, 4}, {1, 5}, {2, 6}, {3, 7}, {3, 8}, {3, 9}, {4, 10}, {4, 11}, {5, 12}, {5, 13}, {6, 14}, {6, 15}, {6, 16}, {8, 17}, {8, 18}};
        map = new HashMap<>();

        for (int[] e : edges) {
            if (map.containsKey(e[0])) {
                map.get(e[0]).add(e[1]);
            } else {
                ArrayList<Integer> l = new ArrayList<>();
                l.add(e[1]);
                map.put(e[0], l);
            }
        }

        total = map.keySet().size();
        go(0, 0, 0, map.get(0));
        System.out.println(min);
    }

    public static void go(int level, int acc, int erase, ArrayList<Integer> list) {
        if (list.size() == 0) {
            min = Math.min(min, acc);
            return;
        }

        if (acc > min) return;

        ArrayList<Integer> l = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            l.add(list.get(i));
        }

        for (int j = 0; j < list.size(); j++) {
            l.remove(j);
            ArrayList<Integer> ll = new ArrayList<>();
            for (int k = 0; k < l.size(); k++) {
                if (map.get(l.get(k)) != null) ll.addAll(map.get(l.get(k)));
            }
            go(level + 1, acc + list.size() - 1, erase + 1, ll);
            l.add(j, list.get(j));
        }
    }
}
