package baekjoon;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Boj_1213 {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("res/name.txt"));

        Scanner sc = new Scanner(System.in);
        char[] name = sc.next().toCharArray();
        int[] alphabet = new int[26];
        int ASCII = 65;

        for(int i=0; i<name.length; i++){
            alphabet[(int)name[i]-ASCII] += 1;
        }

        int oddCount = -1;
        String answer = "";
        for(int i=0; i<alphabet.length; i++){
            if(alphabet[i]%2 != 0){
                if(oddCount >=0) {
                    System.out.println("\"I'm Sorry Hansoo\"");
                    return;
                }
                oddCount = i;
                for(int j=0; j<alphabet[i]/2; j++)
                    answer += Character.toString((char) (i+ASCII));
                alphabet[i] -= (alphabet[i]/2)*2;
            }else if(alphabet[i]>0){
                for(int j=0; j<alphabet[i]/2; j++)
                    answer += Character.toString((char) (i+ASCII));
            }
        }

        System.out.printf(answer);
        if(oddCount>=0)
            for(int i=0; i<alphabet[oddCount]; i++){
                System.out.printf(Character.toString((char) (ASCII+oddCount)));
            }
        for(int i=answer.length()-1; i>=0; i--){
            System.out.print(answer.charAt(i));
        }
    }
}
