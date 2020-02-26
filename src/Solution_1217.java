import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution_1217 {
    public void solution() throws FileNotFoundException {
        System.setIn(new FileInputStream("res/input1217.txt"));


        Scanner sc = new Scanner(System.in);

        for(int test_case = 1; test_case <= 10; test_case++){
            sc.nextInt();

            int n = sc.nextInt();
            int m = sc.nextInt();

            int res = squre(n,m);
            System.out.printf("#%d %d\n",test_case, res);

        }
    }

    public int squre(int n, int m){
        if(m==1) return n;

        return n*squre(n, m-1);
    }
}
