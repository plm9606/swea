
import java.io.FileNotFoundException;
import java.util.*;
import java.io.FileInputStream;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution_1216
{


    public static void s() throws Exception {
        System.setIn(new FileInputStream("res/input1216.txt"));

        Scanner sc = new Scanner(System.in);


        // toCharArray()를 사용하면 문자열을 배열로 받을 수 있다.
        for (int tt = 1; tt <= 10; tt++) {
            int tc = sc.nextInt();
            char[][] a = new char[100][100];
            for (int i = 0; i < 100; i++) {
                a[i] = sc.next().toCharArray();
            }

            int max = 0;

            for (int start=0; start< 100; start++){
                // max len 이하로는 검사를 할 필요가 없다.
                for(int e=100-1; e>start+max-1; e--) {
                    int len = e - start + 1;
                    // 어차피 나눠도 소수점은 떨어진다
                    int center = len/2;

                    for(int i=0; i<100; i++){
                        // row i(y=i)
                        Boolean flag = true;
                        for(int j=0; j<center; j++){
//                            if(a[i][start+j] != a[i][e-j]) {break;
//                            if(j==center-1){
//                                if(len>max) max=len;
//                            }
                            if(a[i][start+j] != a[i][e-j]) {
                                flag = false;
                                break;
                            }
                        }
                        if(!flag) continue;
                        if(len> max)max=len;
                    }

                    for(int i=0; i< 100; i++){
                        // col i
                        Boolean flag = true;
                        for(int j=0; j< center; j++){
//                            if(a[start+j][i] != a[e-j][i]) break;
//                            if(j==center-1){
//                                if(len>max)max=len;
//                            }
                            if(a[start+j][i] != a[e-j][i]){
                                flag=false;
                                break;
                            }
                        }
                        if(!flag) continue;
                        if(len>max) max=len;
                    }
                }
            }
            System.out.format("#%d %d\n", tc, max);
        }
    }

    public static void solution() throws Exception
    {
		/*
		   아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다.
		   여러분이 작성한 코드를 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후,
		   이 코드를 프로그램의 처음 부분에 추가하면 이후 입력을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다.
		   따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 메소드를 사용하셔도 좋습니다.
		   단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석 처리 하셔야 합니다.
		 */
        System.setIn(new FileInputStream("res/input1216.txt"));

		/*
		   표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		 */
        Scanner sc = new Scanner(System.in);
        /*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/

        for(int test_case = 1; test_case <= 10; test_case++)
        {
            int answer = 0;
            int l = sc.nextInt();
            ArrayList<ArrayList<String>> pan = new ArrayList<>();

            for(int i=0; i<100; i++){
                String str = sc.next();
                pan.add(new ArrayList<>());
                for(int j=0; j<100; j++){
                    pan.get(i).add(str.substring(j, j+1));
                }
            }

            for(int i=0; i<100; i++){
                    int rowMax = getPalindromeRowMax(pan.get(i));
                    int colMax = getPalindromeColMax(pan, i);

                    if(rowMax > colMax)
                        if(rowMax > answer) answer = rowMax;
                    else
                        if (colMax > answer) answer = colMax;

            }

            System.out.println(String.format("#%d %d", test_case, answer));
        }
    }

    public static int getPalindromeRowMax(ArrayList<String> row){
        int max = 0;

        for(int start=0; start<100-1; start++){
            outer:for(int end=100-1;end>=start+1; end--){
                int length = end-start+1;
                if(length < max) break ;
                // 대칭 검사
                Boolean flag = true;
                for(int j=0; j<length/2; j++){
                    if(!row.get(start+j).equals(row.get(end-j))){
                        flag= false;
                        break ;
                    }
                }
                if (!flag) continue ;
                if(length > max) max=length;
            }
        }

        return max;
    }

    private static int getPalindromeColMax(ArrayList<ArrayList<String>> pan, int x){
        int answer = 0;

        for(int start=0; start<100-1; start++){
            outer:for(int end=100-1; end >= start+1; end--){
                int len = end - start + 1;
                if(len < answer) break ;
                Boolean flag= true;

                for (int i=0; i< len/2; i++){
                    if(!pan.get(start+i).get(x).equals(pan.get(end-i).get(x))) {
                        flag= false;
                        break;
                    }

                }
                if(!flag) continue ;
                if(len> answer) answer=len;

            }
        }

        return answer;
    }
}