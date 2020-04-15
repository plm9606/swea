package swea;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Solution_1256 {
    public void solution() throws FileNotFoundException {
        System.setIn(new FileInputStream("res/input1256.txt"));


        Scanner sc = new Scanner(System.in);

        sc.nextInt();
        for(int test_case = 1; test_case <= 10; test_case++) {
            int answer = 0;
            int idx = sc.nextInt();
            String word = sc.next();
            int len = word.length();
            ArrayList<String> temp = new ArrayList<>();
            ArrayList<Integer> suffix = new ArrayList<>();

            for (int i = 0; i < len; i++) {
                temp.add(word.substring(i, len));
            }

            boolean[] visited = new boolean[len];
            while (suffix.size() < len){
                boolean first = true;
                boolean find = false;
                int min=0;
                for(int i=0; i<len; i++){
                    if(visited[i] == true) continue;
                    if(first){
                        min = i;
                        first=false;
                        continue;
                    }
                    if(temp.get(min).compareTo(temp.get(i))>0){
                        min=i;
                    }
                }

                    visited[min] = true;
                    suffix.add(min);


            }
            System.out.printf("#%d %s\n", test_case,temp.get(suffix.get(idx-1)));

        }
    }
}
