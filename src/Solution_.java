import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution_ {
    public void solution() throws FileNotFoundException {
//        System.setIn(new FileInputStream("res/input.txt"));


        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++){
            int answer = 0;

            System.out.printf("#%d %d\n", test_case, answer);

        }
    }
}
