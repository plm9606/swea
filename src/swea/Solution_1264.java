package swea;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution_1264 {
    public static void main(String[] args)throws FileNotFoundException {
        System.setIn(new FileInputStream("res/input1264.txt"));


        Scanner sc = new Scanner(System.in);

        int tc = sc.nextInt();
        for(int test_case = 1; test_case <= tc; test_case++){
            int n = sc.nextInt();
            String first =sc.next(), second=sc.next();
            int[][] lcs = new int[n+1][n+1];

            for(int i=1; i<=n; i++){
                char cFirst = first.charAt(i-1);
                for(int j=1; j<=n; j++){
                    char cSecond= second.charAt(j-1);
                    if(cFirst == cSecond){
                        lcs[i][j] = lcs[i-1][j-1]+1;
                    }else{
                        lcs[i][j] = Math.max(lcs[i-1][j], lcs[i][j-1]);
                    }
                }
            }


            System.out.printf("#%d %.2f\n", test_case, (double)lcs[n][n]/n*100);

        }
    }
}
