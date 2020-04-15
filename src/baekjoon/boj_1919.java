package baekjoon;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class boj_1919 {
    public void solution() throws FileNotFoundException {
        System.setIn(new FileInputStream("res/boj1919.txt"));


        Scanner sc = new Scanner(System.in);
        char[] first = sc.next().toCharArray();
        char[] second = sc.next().toCharArray();

        int[] fArray = new int[27];
        int[] sArray = new int[27];

        int ASCII = 97;

        for(int i=0; i<first.length; i++){
            fArray[(int)first[i]-ASCII] +=1;
        }

        for(int i=0; i<second.length; i++){
            sArray[(int)second[i]-ASCII] +=1;
        }

        int count = 0;

        for(int i=0; i<27; i++){
            count +=Math.abs(fArray[i] - sArray[i]);
        }

        System.out.println(count);
    }
}
