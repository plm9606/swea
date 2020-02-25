import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Solution_1210 {
    int[] dy = {0,0,1};
    int[] dx = {-1,1,0};
    int answer = 0;

    public void solution() throws Exception
    {
		/*
		   아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다.
		   여러분이 작성한 코드를 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후,
		   이 코드를 프로그램의 처음 부분에 추가하면 이후 입력을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다.
		   따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 메소드를 사용하셔도 좋습니다.
		   단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석 처리 하셔야 합니다.
		 */
        System.setIn(new FileInputStream("res/input1210.txt"));

		/*
		   표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		 */
        Scanner sc;
        sc = new Scanner(System.in);

		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/

        for(int test_case = 1; test_case <= 10; test_case++)
        {
            sc.nextInt();
            int[][] arr = new int[100][100];
//            int[] target = new int[2];
            for(int i=0; i<100; i++){
                for(int j=0; j<100; j++){
                    int temp = sc.nextInt();
                    arr[i][j] = temp;
//                    if(temp==2){
//                        target[0]=i;
//                        target[1]=j;
//                    }
                }

            }

            for(int i=0; i<100; i++){
                if(arr[0][i]==1)find( deepCopy(arr, 100), 0, i, i);
            }



            System.out.printf("#%d %d\n", test_case, answer);
        }



    }
    public static int[][] deepCopy(int[][] original, int n) {
        if (original == null) {
            return null;
        }

        int[][] result = new int[n][n];
        for (int i = 0; i < original.length; i++) {
            System.arraycopy(original[i], 0, result[i], 0, original[i].length);
        }
        return result;
    }
    public void find(int[][] arr, int y, int x, int start){
        if(arr[y][x] == 2){
            System.out.println("find");
            return;
        }

        for(int i=0; i<3; i++){
            if(y+dy[i] >= 0 && y+dy[i]<100 && x+dx[i] >=0 && x+dx[i]<100){
                if(arr[y+dy[i]][x+dx[i]]==1){
                    arr[y][x]=0;
                    find(deepCopy(arr, 100), y+dy[i], x+dx[i], start);
                }
            }
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