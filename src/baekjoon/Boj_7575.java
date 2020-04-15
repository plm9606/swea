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
        HashMap<String,int[]> patternMap = new HashMap<>();
        HashMap<String, int[]> nextMap = new HashMap<>();

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
                    patternMap.put(Arrays.toString(pattern), pattern);
                    nextMap.put(Arrays.toString(pattern), makeNextTable(pattern));
                }

                continue;
            }

            List<String> removeList = new ArrayList<>();
            for(String key:patternMap.keySet()){
                int[] pattern = patternMap.get(key);
                int[] next = nextMap.get(key);
                int j=0;
                boolean MATCH = false;
                for(int i=0; i<programLen; i++){
                    while (j>0 && program[i] != pattern[j]){
                        j = next[j-1];
                    }
                    if(program[i]==pattern[j]){
                        if(j == pattern.length-1){
                            System.out.println("#"+test +" find "+ (i-pattern.length+2));
                            MATCH = true;
                            break;
                        }
                        j++;
                    }
                }
                j=0;
                for(int i=programLen-1; i>=0; i--){
                    while (j>0 && program[i] != pattern[j])
                        j = next[j-1];
                    if(program[i] == pattern[j]){
                        if(j== pattern.length-1){
                            System.out.println("#"+test +" find2 at "+i);
                            MATCH = true;
                            break;
                        }
                        j++;
                    }
                }

                if(!MATCH) removeList.add(key);
            }

            for(String key : removeList){
                patternMap.remove(key);
            }

        }
        if(patternMap.size()>0){
            for(String key:patternMap.keySet())
                System.out.println(key);

        }else System.out.println("NO");
    }

    public static int[] makeNextTable (int[] pattern){
        int[] next = new int[pattern.length];
        int j=0;

        for(int i=1; i<pattern.length; i++){
            while (j>0 && pattern[i]!=pattern[j]){
                j = next[j-1];

            }
            if(pattern[i]==pattern[j]){
                next[i] = j+1;
                j++;
            }
        }
        return  next;
    }



}
