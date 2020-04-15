package swea;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

public class Solution_1257 {
    public void solution() throws FileNotFoundException {
        System.setIn(new FileInputStream("res/input1257.txt"));


        Scanner sc = new Scanner(System.in);

        sc.nextInt();
        for(int test_case = 1; test_case <= 10; test_case++){
            String answer="";
            int idx = sc.nextInt();
            String word = sc.next();
            int len = word.length();
            HashSet<String> set = new HashSet<>();
            ArrayList<Integer> suffix = new ArrayList<>();

            for(int i=0; i<len; i++){
                for(int j=i+1; j<=len;j++)
                    set.add(word.substring(i, j));
            }

            ArrayList<String> origin = new ArrayList(set);
            Collections.sort(origin);
            boolean[] visited = new boolean[len];

            if(idx>=origin.size()) answer = "none";
            else answer=origin.get(idx-1);
            System.out.printf("#%d %s\n", test_case, answer);

        }
    }
}
