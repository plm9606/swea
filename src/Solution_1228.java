import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Solution_1228 {
    public static void main(String[] args)throws FileNotFoundException {
        System.setIn(new FileInputStream("res/input1228.txt"));


        Scanner sc = new Scanner(System.in);


        for(int test_case = 1; test_case <= 10; test_case++){
            int answer = 0;
            int len = sc.nextInt();
            List<char[]> cryptogram = new LinkedList<>();

            for(int i =0; i<len; i++){
                cryptogram.add(sc.next().toCharArray());
            }
            int count = sc.nextInt();

            for(int i=0; i<count; i++){
                sc.next();
                int x = sc.nextInt();
                int y = sc.nextInt();
                for(int j=0; j<y; j++){
                    cryptogram.add(x+j, sc.next().toCharArray());
                }
            }


            System.out.printf("#%d", test_case);
            for(int i=0; i<10; i++){
                System.out.printf(" %s", new String(cryptogram.get(i)));
            }
            System.out.print("\n");

        }
    }
}
