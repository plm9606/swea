import java.io.FileInputStream;
import java.util.Scanner;

public class Solution_1208 {

        public static void solution() throws Exception
        {
		/*
		   아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다.
		   여러분이 작성한 코드를 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후,
		   이 코드를 프로그램의 처음 부분에 추가하면 이후 입력을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다.
		   따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 메소드를 사용하셔도 좋습니다.
		   단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석 처리 하셔야 합니다.
		 */
            System.setIn(new FileInputStream("res/input1208.txt"));

		/*
		   표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		 */
            Scanner sc = new Scanner(System.in);


            for(int test_case = 1; test_case <= 10; test_case++)
            {
                int answer = 0;
               int dump = sc.nextInt();
               int[] boxes = new int[100];
               int[] heights = new int[101];

               for (int i=0;i<100; i++){
                   boxes[i] = sc.nextInt();
               }

               for (int i=0; i<100; i++){
                   heights[boxes[i]] += 1;
               }

                int sIdx=200, lIdx=-1;

                for(int j=1; j<=100; j++){
                    if(sIdx > j && heights[j]>0){
                        sIdx = j;
                    }
                    else if(lIdx < j && heights[j]>0){
                        lIdx = j;
                    }
                }

               for(int i=1; i<dump; i++){
                   if(lIdx - sIdx <= 1) {
                       break;
                   }

                   heights[sIdx] -= 1;
                   heights[sIdx+1] += 1;
                   heights[lIdx] -= 1;
                   heights[lIdx-1] += 1;

                   if(heights[sIdx] <=0){
                       for(int s=sIdx+1; s<101; s++){
                           if(heights[s]>0){
                               sIdx = s;
                               break;
                           }
                       }
                   }

                   if(heights[lIdx] <=0){
                       for(int e = lIdx-1; e>0; e--){
                           if(heights[e] >0){
                               lIdx = e;
                               break;
                           }
                       }
                   }
               }

               answer = lIdx-sIdx;
                System.out.format("#%d %d\n", test_case, answer);

            }
        }

}
