package algorithm.course;

import java.util.Arrays;

public class NQueen {

    static  int N;
    static  int[]Q;
    static int count;
    public static void main(String[] args) {
         N = 10;
         count = 0;
        Q = new int[N+1];

        queen(1);
    }

    private static void queen(int level){
        if(level > N){
            System.out.print(++count+": ");
            System.out.println(Arrays.toString(Arrays.copyOfRange(Q,1,Q.length)));
            return;
        }

        for(int i=1; i<=N; i++){
            Q[level] = i;
            if(promising(level)){
                queen(level+1);
            }
        }
    }

    private static boolean promising(int level){
        boolean res = true;
        int cur = Q[level];

        for(int i=1; i<level; i++){
            if(Q[i] == cur || Math.abs(i-level)==Math.abs(Q[i]-cur)){
                res = false;
                break;
            }
        }

        return res;
    }
}
