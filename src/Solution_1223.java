import java.awt.print.Pageable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class Solution_1223 {
    public static void main(String[] args)throws FileNotFoundException {
        System.setIn(new FileInputStream("res/input1223.txt"));


        Scanner sc = new Scanner(System.in);
        HashMap<String, Integer> priority =  new HashMap<>();
        priority.put("+", 1);
        priority.put("*",2);

        for(int test_case = 1; test_case <= 10; test_case++){
            int answer = 0;
            int len = sc.nextInt();
            char[] expression = sc.next().toCharArray();
            Stack<Character> stack = new Stack<>();
            ArrayList<Character> postfixExpression = new ArrayList<>();


            for(char c: expression){
                if(priority.keySet().contains(c+"")){
                    if(stack.size() == 0){
                        stack.push(c);
                        continue;
                    }
                    while (stack.size()>0){
                        Character top = stack.get(stack.size()-1);
                        if(priority.get(top+"") < priority.get(c+"")){
                            stack.push(c);
                            break;
                        }
                        else {
                            postfixExpression.add(stack.pop());
                            if(stack.size()==0){
                                stack.push(c);
                                break;
                            }
                        }
                    }

                    continue;
                }
                postfixExpression.add(c);
            }

            while (stack.size()>0){
                postfixExpression.add(stack.pop());
            }

            Stack<Integer> calc = new Stack<>();

            for(Character c: postfixExpression){
                if(priority.keySet().contains(c+"")){
                    if((c+"").equals("+"))
                        calc.push(calc.pop()+calc.pop());
                    else
                        calc.push(calc.pop() * calc.pop());
                    continue;
                }
                calc.push(Integer.parseInt(c+""));
            }

            answer = calc.pop();
            System.out.printf("#%d %d\n", test_case, answer);

        }
    }
}
