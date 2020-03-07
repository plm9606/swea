import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Solution_1227 {
    public static void main(String[] args)throws FileNotFoundException {
        System.setIn(new FileInputStream("res/input1227.txt"));


        Scanner sc = new Scanner(System.in);
        List<List<Integer>> deltas = new ArrayList<>();
        deltas.add(Arrays.asList(1,-1));
        deltas.add(Arrays.asList(1,0));
        deltas.add(Arrays.asList(1,1));
        deltas.add(Arrays.asList(0,-1));
        deltas.add(Arrays.asList(0,1));
        deltas.add(Arrays.asList(-1,-1));
        deltas.add(Arrays.asList(-1,0));
        deltas.add(Arrays.asList(-1,1));



        for(int test_case = 1; test_case <= 10; test_case++){
            int answer = 0;
            sc.nextInt();
            int [][] maze = new int[100][100];
            boolean [][] visited = new boolean[100][100];
            Stack<List<Integer>> stack = new Stack<>();

            for(int i=0; i<100; i++){
                char[] row = sc.next().toCharArray();
                for(int j=0; j<100; j++){
                    maze[i][j] = Integer.parseInt(row[j]+"");
                }

            }

            stack.push(Arrays.asList(1,1));
            w: while (stack.size()>0){
                List<Integer> point = stack.pop();
                int y = point.get(0);
                int x = point.get(1);

                visited[y][x]= true;

                for(List<Integer> delta: deltas){
                    if(y+delta.get(0)<0 || y+delta.get(0)>=100 || x+delta.get(1)<0 || x+delta.get(1)>=100) continue;
                    if(maze[y+delta.get(0)][x+delta.get(1)]== 3){
                        answer=1;
                        break w;
                    }
                    if(maze[y+delta.get(0)][x+delta.get(1)] == 0 && visited[y+delta.get(0)][x+delta.get(1)]==false){
                        stack.push(Arrays.asList(y+delta.get(0), x+delta.get(1)));
                    }
                }
            }


            System.out.printf("#%d %d\n", test_case, answer);

        }
    }
}
