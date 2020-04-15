package swea;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Solution_1210 {
    int[] dy = {0,0,1};
    int[] dx = {-1,1,0};
    int[][] arr = new int[100][100];
    boolean FIND = false;

    public void solution() throws Exception
    {
        System.setIn(new FileInputStream("res/input1210.txt"));

        Scanner sc;
        sc = new Scanner(System.in);

        for(int test_case = 1; test_case <= 10; test_case++)
        {
            int answer = 0;
            FIND = false;

            sc.nextInt();
            for(int i=0; i<100; i++){
                for(int j=0; j<100; j++){
                    int temp = sc.nextInt();
                    arr[i][j] = temp;
                }

            }

            for(int i=0; i<100; i++){
                if(arr[0][i]==1){
                    find( 0, i, i);
                    if(FIND) {
                        answer = i;
                        break;
                    }
                }
            }



            System.out.printf("#%d %d\n", test_case, answer);
        }



    }
    public void find(int y, int x, int start){
        // 방문을 체크하기 위해 자료구조를 만든다
        boolean[][] visited = new boolean[100][100];

        while (y<100){
            visited[y][x] = true;

            if(arr[y][x] == 2){
                FIND = true;
                break;
            }
            if(x-1 >=0 && x-1<100 && visited[y][x-1] == false && arr[y][x-1] ==1){
                x--;
            }else if(x+1 >=0 && x+1 < 100 && visited[y][x+1]== false && arr[y][x+1] == 1){
                x++;
            }else y++;
        }
    }

    public void solutionReverse() throws FileNotFoundException {
        System.setIn(new FileInputStream("res/input1210.txt"));

        Scanner sc;
        sc = new Scanner(System.in);

        for(int test_case = 1; test_case <= 10; test_case++) {

            int answer = 0;
            int y=0,x=0;
            sc.nextInt();
            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 100; j++) {
                    int temp = sc.nextInt();
                    if(temp == 2){
                        y=i;
                        x=j;
                    }
                    arr[i][j] = temp;
                }
            }

            while (y>=0){
                if(arr[y][x] == 0) break;

                if(y==0){
                    answer = x;
                    break;
                }

                // 방문 표시가 없으면 무한루프에 빠진다.
                arr[y][x]=0;
                if(x-1>=0 && arr[y][x-1] == 1){
                    x--;
                }
                else if(x+1<100 && arr[y][x+1]==1){
                    x++;
                }else y--;
            }

            System.out.printf("#%d %d\n", test_case, answer);
        }
    }
}
/**
 * 왼쪽으로 뚫릴수도 있고 오른쪽으로 뚫릴수도 있지만 양쪽 다 뚫리는 경우 없다?
 * 나는 타고 내려온 자리를 0으로 바꾸어서 지나갔던 자리를 다시 가지 않도록 하려고 했음
 * 그런데 깊은 복사를 매번 해야했음. 2차원 배열이라 더 까다로웠음?
 *
 * 새로운 자료구조를 추가해 보조적으로 사용할 수 있는 방법이 있음
 * find[][]를 만들어서 지나간 자리를 true 처리한다
 *
 * 확실히 아는 목적지에서 거꾸로 도착하는 지점을 찾는다.
 * 이 경우에는 find[][]가 필요 없다.
 **/