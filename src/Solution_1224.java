import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Solution_1224 {
    public static void main(String[] args)throws FileNotFoundException {
        System.setIn(new FileInputStream("res/input1224.txt"));


        Scanner sc = new Scanner(System.in);

        HashMap<String, List<Integer>> priority = new HashMap<>();
        priority.put("+", Arrays.asList(1,1));
        priority.put("*", Arrays.asList(2,2));
        priority.put(")", Arrays.asList(-1,-1));
        priority.put("(", Arrays.asList(0,3));


        for(int test_case = 1; test_case <= 10; test_case++){
            sc.nextInt();
            char[] expression = sc.next().toCharArray();
            ArrayList<Character> postfixExpression = new ArrayList<>();
            Stack<Character> stack = new Stack<>();
            int answer = 0;

            for(char c: expression){
                if(priority.keySet().contains(c+"")){
                    if((c+"").equals(")")){
                        while (true){
                            char top = stack.get(stack.size()-1);
                            if((top+"").equals("(")) {
                                stack.pop();
                                break;
                            }
                            else postfixExpression.add(stack.pop());
                        }
                        continue;
                    }
                        while (true){
                            if(stack.size()==0){
                                stack.push(c);
                                break;
                            }
                            char top = stack.get(stack.size()-1);

                            if(priority.get(top+"").get(0) < priority.get(c+"").get(1)){
                                stack.push(c);
                                break;
                            }
                            else{
                                postfixExpression.add(stack.pop());
                            }
                        }
                    continue;
                }
                postfixExpression.add(c);
            }

            while (stack.size()>0){
                postfixExpression.add(stack.pop());
            }

//            System.out.println(postfixExpression.toString());

            Stack<Integer> calc = new Stack<>();
            for(Character c: postfixExpression){
                if(priority.keySet().contains(c+"")){
                    if((c+"").equals("+"))
                        calc.push(calc.pop()+calc.pop());
                    else calc.push(calc.pop()*calc.pop());
                    continue;
                }
                calc.push(Integer.parseInt(c+""));
            }

            answer = calc.pop();
            System.out.printf("#%d %d\n", test_case, answer);

        }
    }
}
