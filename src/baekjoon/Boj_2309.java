package baekjoon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Boj_2309 {
    static int[] dwarfs = {20,7,23,19,10,8,13,6,17};
    static int dSum;
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        dwarfs = new  int[9];
        dSum = 0;
        for(int i=0; i<9; i++){
//            dwarfs[i] = sc.nextInt();
            dSum += dwarfs[i];
        }

        outer:for(int i=0; i<dwarfs.length-1; i++){
            for(int j=i+1; j<dwarfs.length; j++){
                if(dSum - dwarfs[i] - dwarfs[j] == 100){
                    int[] res = new int[7];
                    int idx = 0;
                    for(int k=0; k<9; k++){
                        if(k != i && k!= j){
                            res[idx++] = dwarfs[k];
                        }
                    }
                    Arrays.sort(res);
                    for(int k=0; k<7; k++){
                        System.out.println(res[k]);
                    }
                    break outer;
                }
            }
        }
    }
}
