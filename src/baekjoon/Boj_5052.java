package baekjoon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class Boj_5052 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("res/boj5052.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < t; tc++) {
            int n = Integer.parseInt(br.readLine());
            String[] numbers = new String[n];
            for (int i = 0; i < n; i++) {
                numbers[i] = br.readLine();
            }

            boolean flag = true;
            Trie trie = new Trie();
            for (String number : numbers) {
                if (!trie.checkNumber(number.toCharArray())) {
                    flag = false;
                    break;
                }
            }

            if (flag) System.out.println("YES");
            else System.out.println("NO");
        }
    }

    private static void solution_hashmap(String[] numbers, int n) {
        boolean flag = true;

        Arrays.sort(numbers, new Comparator<String>() {
            @Override
            public int compare(String s, String t1) {
                if (s.length() > t1.length()) return 1;
                else if (s.length() < t1.length()) return -1;
                else return 0;
            }
        });

        HashMap<String, Boolean> map = new HashMap<>();
        map.put(numbers[0], true);
        loop:
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < numbers[i].length(); j++) {
                if (map.get(numbers[i].substring(0, j + 1)) != null) {
                    flag = false;
                    break loop;
                }

            }
            map.put(numbers[i], true);
        }
        if (!flag) System.out.println("NO");
        else System.out.println("YES");
    }
}

class Trie {
    TrieNode root;

    Trie() {
        this.root = new TrieNode();
    }

    public boolean checkNumber(char[] number) {
        TrieNode acc = this.root;
        for (int i = 0; i < number.length; i++) {
            if (acc.finish) {
                return false;
            }
            if (acc.children.get(number[i]) != null && i == number.length - 1) {
                return false;
            }
            if (acc.children.get(number[i]) == null) {
                acc.addChild(number[i]);
            }
            acc = acc.children.get(number[i]);
            if (i == number.length - 1) {
                acc.finish = true;
            }
        }

        return true;
    }
}

class TrieNode {
    boolean finish;
    HashMap<Character, TrieNode> children;

    TrieNode() {
        this.children = new HashMap<>();
        this.finish = false;
    }

    public void addChild(Character c) {
        if (!this.children.containsKey(c)) {
            this.children.put(c, new TrieNode());
        }
    }
}