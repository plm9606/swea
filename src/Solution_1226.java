import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Solution_1226 {
    public static void main(String[] args)throws FileNotFoundException {
        System.setIn(new FileInputStream("res/input1226.txt"));


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
            int [][] maze = new int[16][16];
            boolean [][] visited = new boolean[16][16];
            Queue<List<Integer>> queue = new LinkedList<>();

            for(int i=0; i<16; i++){
                char[] row = sc.next().toCharArray();
                for(int j=0; j<16; j++){
                    maze[i][j] = Integer.parseInt(row[j]+"");
                }

            }

            queue.offer(Arrays.asList(1,1));
            w:while (queue.size()>0){
                List<Integer> point = queue.poll();
                int y = point.get(0);
                int x = point.get(1);
                visited[y][x] = true;
                if(maze[y][x] == 3){
                    answer=1;
                    break;
                }
                for(List<Integer> delta: deltas){
                    if(y+delta.get(0)<0 || y+delta.get(0)>=16 || x+delta.get(1)<0 || x+delta.get(1)>=16) continue;
                    if(maze[y+delta.get(0)][x+delta.get(1)]== 3){
                        answer=1;
                        break w;
                    }
                    if(maze[y+delta.get(0)][x+delta.get(1)] == 0 && visited[y+delta.get(0)][x+delta.get(1)]==false){
                        queue.offer(Arrays.asList(y+delta.get(0), x+delta.get(1)));
                    }
                }
            }


            System.out.printf("#%d %d\n", test_case, answer);

        }
    }
}
