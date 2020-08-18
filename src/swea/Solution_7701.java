package swea;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Solution_7701 {
    public static void main(String[] args)throws FileNotFoundException {
        System.setIn(new FileInputStream("res/input_7701.txt"));


        Scanner sc = new Scanner(System.in);

        int tc = sc.nextInt();
        for(int test_case = 1; test_case <= tc; test_case++){
            int answer = 0;
            int n = sc.nextInt();
            HashSet<String> set = new HashSet<>();
            ArrayList<String> list = new ArrayList<>();

            for(int i=0; i<n; i++){
                String s = sc.next();
                if(set.contains(s)) continue;
                set.add(s);
                list.add(s);
            }

            Comparator<String> comparator = new Comparator<String>() {
                @Override
                public int compare(String s, String t1) {
                    if(s.length() > t1.length()) return 1;
                    else if (s.length() < t1.length()) return -1;
                    else {
                        for(int i=0; i<s.length(); i++){
                            if(s.charAt(i) > t1.charAt(i)) return 1;
                            else if(s.charAt(i) < t1.charAt(i)) return -1;
                            else continue;
                        }
                    }
                    return 0;
                }
            };

            Collections.sort(list, comparator);

            System.out.printf("#%d\n", test_case);

            for(String name:list){
                System.out.println(name);
            }
        }
    }
}
