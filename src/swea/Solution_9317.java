package swea;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution_9317 {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("res/input9317.txt"));


        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int answer = 0;
            sc.nextInt();
            char[] origin = sc.next().toCharArray();
            char[] dictation = sc.next().toCharArray();

            for (int i = 0; i < origin.length; i++) {
                if (origin[i] == dictation[i]) answer++;
            }
            System.out.printf("#%d %d\n", test_case, answer);

        }
    }
}
