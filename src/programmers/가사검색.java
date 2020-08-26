package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

class 가사검색 {
    static int[] result;

    public static void main(String[] args) {
        solution(new String[]{"aaaa", "a", "aa", "aaa"}, new String[]{"?a", "?a", "??a", "a??"});
    }

    public static int[] solution(String[] words, String[] queries) {
        int[] answer = {};
        HashMap<String, ArrayList<Query>> map = new HashMap<>();
        result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            String query = queries[i];
            int start = query.indexOf("?");
            int end = query.lastIndexOf("?");
            // 물음표가 prefix
            if (start == 0) {
                String word = query.substring(end + 1);
                if (!map.containsKey(word)) {
                    map.put(word, new ArrayList<>());
                }
                ArrayList<Query> list = map.get(word);
                list.add(new Query(0, end - start + 1, i));
                map.replace(word, list);
            } else {
                String word = query.substring(0, start);
                if (!map.containsKey(word)) {
                    map.put(word, new ArrayList<>());
                }
                ArrayList<Query> list = map.get(word);
                list.add(new Query(1, end - start + 1, i));
                map.replace(word, list);
            }
        }

        for (String pattern : map.keySet()) {
            int[] next = makeTable(pattern);
            System.out.println(pattern);

            for (String word : words) {
                int j = 0;
                loop:
                for (int i = 0; i < word.length(); i++) {
                    while (j > 0 && !word.substring(i, i + 1).equals(pattern.substring(j, j + 1))) {
                        j = next[j - 1];
                    }
                    if (word.substring(i, i + 1).equals(pattern.substring(j, j + 1))) {
                        // 패턴의 마지막까지 전부 일치
                        if (j == pattern.length() - 1) {
                            ArrayList<Query> list = map.get(pattern);
                            for (Query query : list) {
                                if (word.length() != pattern.length() + query.cnt) continue;
                                int cnt = query.cnt;
                                if (query.type == 0) {
                                    if (i - pattern.length() - cnt + 1 == 0) {
                                        System.out.println(word);
                                        result[query.idx]++;
                                    }
                                } else {
                                    if (i + cnt + 1 == word.length()) {
                                        System.out.println(word);
                                        result[query.idx]++;
                                    }
                                }
                            }
                        } else j++;
                    }
                }
            }
        }

        System.out.println(Arrays.toString(result));
        return result;
    }

    public static int[] makeTable(String pattern) {
        int next[] = new int[pattern.length()];

        int j = 0;
        for (int i = 1; i < pattern.length(); i++) {
            while (j > 0 && !pattern.substring(i, i + 1).equals(pattern.substring(j, j + 1))) {
                j = next[j - 1];
            }
            if (pattern.substring(i, i + 1).equals(pattern.substring(j, j + 1))) {
                next[i] = j + 1;
                j += 1;
            }
        }

        return next;
    }
}

class Query {
    int type, cnt, idx;

    public Query(int type, int cnt, int idx) {
        this.type = type;
        this.cnt = cnt;
        this.idx = idx;
    }
}