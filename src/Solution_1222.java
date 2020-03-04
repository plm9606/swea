import java.awt.print.Pageable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Solution_1222 {
    public static void main(String[] args)throws FileNotFoundException {
        System.setIn(new FileInputStream("res/input1222.txt"));


        Scanner sc = new Scanner(System.in);


        for(int test_case = 1; test_case <= 10; test_case++){
            int answer = 0;
            Stack<Character> stack = new Stack<Character>();
            int len = sc.nextInt();
            char[] expression = sc.next().toCharArray();
            ArrayList<Character> postExpression = new ArrayList<>();
            for(char c:expression){
                if((c+"").equals("+")){
                    if(stack.size()>0)
                        postExpression.add(stack.pop());
                    stack.push(c);
                        continue;
                }
                postExpression.add(c);
            }
           for(Character c: stack){
               postExpression.add(c);
           }

           Stack<Integer> calculator = new Stack<>();
            for(Character c:postExpression){
                if((c+"").equals("+")){
                    int result = calculator.pop()+calculator.pop();
                    calculator.push(result);
                    continue;
                }
                else {
                   calculator.push(Integer.parseInt(c+""));
                }
            }
            answer = calculator.pop();

            System.out.printf("#%d %d\n", test_case, answer);

        }
    }
}
