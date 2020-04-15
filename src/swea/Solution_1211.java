package swea;

import java.io.FileInputStream;
import java.util.Scanner;

public class Solution_1211 {
    int[][] arr = new int[100][100];
    int max = 0;
    int answer = 0;
    public void solution() throws Exception {
        System.setIn(new FileInputStream("res/input1210.txt"));
        Scanner sc = new Scanner(System.in);

        for(int test_case = 1; test_case <= 10; test_case++) {
            sc.nextInt();
            max=0;
            for(int i=0; i<100; i++){
                for(int j=0; j<100; j++){
                    arr[i][j] = sc.nextInt();
                }
            }

            for(int i=0;i<100; i++){
                if(arr[0][i]==1)find(0, i, i);
            }

            System.out.printf("#%d %d\n", test_case, answer);

        }

    }

    public void find(int y, int x, int start){
        boolean[][] visited = new boolean[100][100];
        int count = 0;

        while (y<100){
            count++;
            visited[y][x]= true;

            if(arr[y][x]== 2){
                System.out.println("find");
                if(max<count){
                    max = count;
                    answer = start;
                }
                break;
            }

            if(x-1>=0 && arr[y][x-1]==1 && visited[y][x-1]==false){
                x--;
            }else if(x+1<100 && arr[y][x+1]==1 && visited[y][x+1]==false){
                x++;
            }
            else y++;
        }
    }

}
