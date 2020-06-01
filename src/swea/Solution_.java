package swea;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution_ {
    public static void main(String[] args)throws FileNotFoundException {
        System.setIn(new FileInputStream("res/input.txt"));


        Scanner sc = new Scanner(System.in);

        int tc = sc.nextInt();
        for(int test_case = 1; test_case <= tc; test_case++){
            int answer = 0;

            System.out.printf("#%d %d\n", test_case, answer);

        }
    }
}
