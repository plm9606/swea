package swea;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution_2806 {
    static int[] col;
    static int n;
    static int count;
    public static void main(String[] args)throws FileNotFoundException {
        System.setIn(new FileInputStream("res/input2806.txt"));


        Scanner sc = new Scanner(System.in);

        int tc = sc.nextInt();
        for(int test_case = 1; test_case <= tc; test_case++){
            n = sc.nextInt();
            col = new int[n+1];
            count = 0;
            for(int i=1; i<=n; i++){
                col[1] = i;
                findNode(1);
            }
            System.out.printf("#%d %d\n", test_case, count);
        }
    }


    public static void findNode(int i){
        if(i>n) return;

        if(promising(i)){
            if(i==n){
                count++;
                return;
            }
            for(int k=1; k<=n; k++){
                col[i+1] = k;
                findNode(i+1);
            }
        }
    }


    public static boolean promising(int i){
        int k=1;
        boolean promising = true;
        while (k<i && promising){
            if(col[k] == col[i] || Math.abs(col[k]-col[i]) == i-k){
                promising = false;
            }
            k++;
        }

        return promising;
    }
}
