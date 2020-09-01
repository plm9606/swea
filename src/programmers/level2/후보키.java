package programmers.level2;

import java.util.ArrayList;
import java.util.HashSet;

public class 후보키 {
    String[][] relation;
    int answer = 0, columnCnt, rowCnt;
    ArrayList<int[]> can = new ArrayList<>();

    public int solution(String[][] relation) {
        rowCnt = relation.length;
        this.relation = relation;
        columnCnt = relation[0].length;

        for (int i = 1; i <= columnCnt; i++) {
            combination(columnCnt, new boolean[columnCnt], i, 0);
        }

        return answer;
    }

    public void combination(int n, boolean[] bit, int toChoice, int target) {
        if (toChoice == 0) {
            ArrayList<Integer> list = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                if (bit[i]) {
                    list.add(i);
                }
            }

            if (list.size() > 1) {
                HashSet<Integer> curKeys = new HashSet<>(list);
                for (int[] cnadidateKey : can) {
                    int cnt = 0;
                    for (int key : cnadidateKey) {
                        if (curKeys.contains(key)) {
                            cnt++;
                        }
                    }
                    if (cnt == cnadidateKey.length) return;
                }
            }

            HashSet<String> set = new HashSet<>();
            boolean flag = true;

            for (int r = 0; r < rowCnt; r++) {
                String[] row = relation[r];
                String key = "";

                for (int colIdx : list) {
                    key += (row[colIdx] + " ");
                }

                if (set.contains(key)) {
                    flag = false;
                    break;
                } else {
                    set.add(key);
                }
            }

            if (flag) {
                answer++;
                can.add(list.stream().mapToInt(i -> i).toArray());
            }
            return;
        }

        for (int i = target; i < n; i++) {
            bit[i] = true;
            combination(n, bit, toChoice - 1, i + 1);
            bit[i] = false;
        }
    }
}