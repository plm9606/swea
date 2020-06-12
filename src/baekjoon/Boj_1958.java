package baekjoon;

import javax.swing.text.MutableAttributeSet;
import java.util.Scanner;

public class Boj_1958 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
//        String first = sc.next();
//        String second = sc.next();
//        String third = sc.next();

        String first = "dababcf";//"abcdefghijklmn";
        String second = "ababdef";//"bdefg";
        String third = "df";

        String sub1 = makeLCS(first, second, third);
        String sub = makeLCS(first, second);
        String sub2 = makeLCS(sub, third);

        System.out.println(sub1 +", " + sub2);
    }

    public static String makeLCS(String first, String second){
        int fLen = first.length();
        int sLen = second.length();

        int[][] lcs1 = new int[fLen+1][sLen+1];

        for(int i=1; i<=fLen; i++){
            char cFirst = first.charAt(i-1);
            for(int j=1; j<=sLen; j++){
                char cSecond = second.charAt(j-1);
                if(cFirst==cSecond){
                    lcs1[i][j] = lcs1[i-1][j-1]+1;
                }else {
                    lcs1[i][j] = Math.max(lcs1[i-1][j], lcs1[i][j-1]);
                }
            }
        }

        int y= fLen, x = sLen;
        String subsequence = "";
        while (y>0 && x>0){

            if(lcs1[y][x-1] == lcs1[y][x]){
                x--;
            }else if(lcs1[y-1][x] == lcs1[y][x]){
                y--;
            }else if(lcs1[y-1][x-1] == lcs1[y][x]-1){
                subsequence = first.charAt(y-1) + subsequence;
                y--;
                x--;

            }else {
                break;
            }
        }

        return subsequence;
    }

    public static String makeLCS(String first, String second, String third){
        int fLen = first.length();
        int sLen = second.length();
        int tLen = third.length();

        int[][][] lcs1 = new int[fLen+1][sLen+1][tLen+1];

        for(int i=1; i<=fLen; i++){
            char cFirst = first.charAt(i-1);
            for(int j=1; j<=sLen; j++){
                char cSecond = second.charAt(j-1);
                for(int k=1; k<=tLen; k++){
                    char cThird = third.charAt(k-1);
                    if(cFirst == cSecond && cSecond == cThird){
                        lcs1[i][j][k] = lcs1[i-1][j-1][k-1]+1;
                    }else {
                        lcs1[i][j][k] = Math.max(lcs1[i][j][k-1], Math.max(lcs1[i-1][j][k], lcs1[i][j-1][k]));
                    }
                }
            }
        }

        int y= fLen, x = sLen, z = tLen;
        String subsequence = "";
        while (y>0 && x>0){

            if(lcs1[y][x-1][z] == lcs1[y][x][z]){
                x--;
            }else if(lcs1[y-1][x][z] == lcs1[y][x][z]){
                y--;
            }else if(lcs1[y][x][z-1] == lcs1[y][x][z]){
                z--;
            }else if(lcs1[y-1][x-1][z-1] == lcs1[y][x][z]-1){
                subsequence = first.charAt(y-1) + subsequence;
                y--;
                x--;
                z--;
            }else {
                break;
            }
        }

        return subsequence;
    }

}
