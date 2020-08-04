package baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Boj_3055 {
    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        FileReader fr = new FileReader("res/boj3055.txt");
        BufferedReader br = new BufferedReader(fr);
        String[] ss = br.readLine().split(" ");
        int R = Integer.parseInt(ss[0]);
        int C = Integer.parseInt(ss[1]);
        String map[][] = new String[R][R];
        Queue<int[]> water = new LinkedList<>();
        Queue<int[]> go = new LinkedList<>();

        for(int i=0; i<R; i++){
            String[] s = br.readLine().split("");
            for(int j=0; j<R; j++){
                map[i][j] = s[j];
                if(s[j] == "D") {
                    go.add(new int[]{i, j});
                }else if(s[j]=="*"){
                    water.add(new int[]{i,j});
                }
            }
        }
        while (true){
            for(int i=0; i<water.size(); i++){
                int[] p = water.poll();
                int y = p[0];
                int x = p[1];
                if(map[y][x] != "X" && map[y][x] != "S" && map[y][x] != "*"){
                    map[y][x] = "*";
                }
            }
        }

    }
}