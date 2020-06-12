package baekjoon;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Boj_9251 {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("res/boj9251.txt"));

        Scanner sc = new Scanner(System.in);
        String first = sc.next();
        String second = sc.next();
        int len = first.length();
        int secondLen = second.length();

        int[][]lcs = new int[len+1][secondLen+1];

        for(int y=1;y<=len; y++){
            for(int x=1; x<=secondLen; x++){
                char cy = first.charAt(y-1);
                char cx = second.charAt(x-1);
                if(cy == cx){
                    lcs[y][x] = lcs[y-1][x-1]+1;
                }else {
                   lcs[y][x] = Math.max(lcs[y-1][x], lcs[y][x-1]);
                }
            }
        }

        System.out.println(lcs[len][secondLen]);
    }

}
