package programmers;

import java.util.HashSet;
import java.util.Set;

public class 문자열압축 {
           public static void main(String[] args) {
            s2(4);
        }
        public static int solution(int p) {
            int answer = 0;
            boolean FLAG = true;
            p++;

            loop:while (p<=10000){
                FLAG = true;
                String year = Integer.toString(p);

                outer:for(int i=0; i<year.length()-1; i++){
                    for(int j=i+1; j<year.length(); j++){
                        if(year.charAt(i) == year.charAt(j)) {
                            FLAG= false;
                            break outer;
                        }
                    }
                }

                if(FLAG) {
                    break loop;
                }

                p++;
            }

            return p;
        }

        static long cnt;
        static long res = 0;

        public static long s2(long n){
            int count  = (int)Math.ceil (Math.sqrt(n+1));
            int[] arr = new int[count-1];
            boolean[] b = new boolean[arr.length];
            cnt = n - (int)Math.pow(2, count-1)+1;

            if(cnt == 0){
                return (int)Math.pow(3, count-1);
            }

            for(int i=0; i<arr.length; i++){
                arr[i] = i;
            }
            for(int i=1; i<count; i++) {
                if(cnt==0) break;;
                combination(arr, b, i, 0);
            }

        System.out.println(res + Math.pow(3, count-1));
        return  (long)(res + Math.pow(3, count-1));
    }

    public static void combination(int[] arr, boolean[] bit, int toChoice, int target){
        if(toChoice == 0){
            if(--cnt == 0){
                long sum =0;
                for(int i=0; i< arr.length; i++){
                    boolean b = bit[i];
                    if(b){
                        sum += Math.pow(3, arr[i]);
                    }
                }
                res = sum;
            }
            return;
        }

        for(int i=target; i<arr.length; i++){
            bit[i] = true;
            combination(arr, bit, toChoice-1, i+1);
            bit[i] = false;
        }

    }
}
