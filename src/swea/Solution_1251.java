package swea;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Solution_1251 {
    public static void main(String[] args)throws FileNotFoundException {
        System.setIn(new FileInputStream("res/input1251.txt"));


        Scanner sc = new Scanner(System.in);

        int tc = sc.nextInt();
        for(int test_case = 1; test_case <= tc; test_case++){
            int answer = 0;
            int N = sc.nextInt();
            ArrayList<int[]> points = new ArrayList<>();

            for(int i=0; i<N; i++){
                points.add(new int[]{sc.nextInt(), sc.nextInt()});
            }

            int E = sc.nextInt();

            System.out.printf("#%d %d\n", test_case, answer);

        }
    }
}
