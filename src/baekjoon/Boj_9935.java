package baekjoon;

import java.io.IOException;
import java.util.Stack;

public class Boj_9935 {
    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("res/boj9935.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        String s = br.readLine();
//        String bomb = br.readLine();

        String s = "12ab112ab2ab", bomb = "12ab";


        char[] result = new char[s.length()];
        int pointer = 0;
        for (int i = 0; i < s.length(); i++) {
            result[pointer] = s.charAt(i);
            if (pointer >= bomb.length() - 1) {
                boolean flag = true;
                for (int j = 0; j < bomb.length(); j++) {
                    if (result[pointer - j] != bomb.charAt(bomb.length() - 1 - j)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    pointer -= bomb.length();
                }
            }
            pointer++;
        }

        if (pointer == 0) System.out.println("FRULA");
        else {
            System.out.println(String.valueOf(result, 0, pointer));
        }

        solution(s, bomb);
    }

    public static void solution(String s, String bomb) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            stack.push(s.charAt(i));

            if (stack.peek() == bomb.charAt(bomb.length() - 1) && stack.size() >= bomb.length()) {
                boolean flag = true;
                for (int j = 1; j < bomb.length(); j++) {
                    if (stack.get(stack.size() - 1 - j) != bomb.charAt(bomb.length() - 1 - j)) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    for (int j = 0; j < bomb.length(); j++)
                        stack.pop();
                }
            }
        }

        if (stack.size() == 0) System.out.println("FRULA");
        else {
            StringBuilder sb = new StringBuilder();
            for (char ch : stack) {
                sb.append(ch);
            }
            System.out.println(sb.toString());
        }
    }
}
