import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution_1225 {
    public static void main(String[] args)throws FileNotFoundException {
        System.setIn(new FileInputStream("res/input1225.txt"));


        Scanner sc = new Scanner(System.in);


        for(int test_case = 1; test_case <= 10; test_case++){
            int answer = 0;
            sc.nextInt();
            Queue<Integer> crypto = new LinkedList<>();

            for(int i=0; i<8; i++){
                crypto.offer(sc.nextInt());
            }

            boolean FIND = false;
            while (!FIND){
                for(int i=1; i<=5; i++){
                    int first = crypto.poll();
                    if(first-i <= 0){
                        crypto.offer(0);
                        FIND = true;
                        break;
                    }
                    crypto.offer(first-i);

                }
            }

            System.out.printf("#%d", test_case);
            for(int i=0; i<8; i++){
                System.out.printf(" %d", crypto.poll());
            }
            System.out.println("");

        }
    }
}
