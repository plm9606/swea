import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Solution_1218 {
    public void solution() throws FileNotFoundException {
        System.setIn(new FileInputStream("res/input1218.txt"));


        Scanner sc = new Scanner(System.in);

        for(int test_case = 1; test_case <= 10; test_case++){
            int answer = 1;
            int len = sc.nextInt();
            char[] str = new char[len];
            str = sc.next().toCharArray();
            Stack<String> stack = new Stack<>();
            List<String> starter = Arrays.asList("(","[","{","<");
            List<String> closer = Arrays.asList(")","]","}",">");

            for(int i=0; i<len; i++){
                if(starter.contains(str[i]+"")){
                    stack.push(str[i]+"");
                }
                else if(closer.contains(str[i]+"")) {
                    if(stack.size() <=0){
                        answer=0;
                        break;
                    }
                    String lastItem = stack.get(stack.size()-1);
                    if(starter.contains(lastItem)){
                        if(starter.indexOf(lastItem) == closer.indexOf(str[i]+"")){
                            stack.pop();
                            continue;
                        }
                    }
                    answer=0;
                    break;
                }
            }

            if(stack.size() > 0){
                answer = 0;
            }

            System.out.printf("#%d %d\n", test_case, answer);
        }
    }
}
