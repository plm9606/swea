package baekjoon;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Boj_7575 {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("res/boj7575.txt"));
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        int len = sc.nextInt();
        ArrayList<int[]> patterns = new ArrayList<>();

        for(int test =0; test<T; test++){
            int programLen = sc.nextInt();
            int[] program = new int[programLen];

            for(int i=0; i<programLen; i++){
                program[i] = sc.nextInt();
            }

            if(test==0){
                for(int i=0; i<=programLen-len; i++){
                    int[] pattern = new int[len];
                    for(int j=0; j<len; j++){
                        pattern[j] = program[i+j];
                    }
                    patterns.add(pattern);
                }
                continue;
            }

            for(int i=len;i<programLen; i++){

            }

        }
    }
}
