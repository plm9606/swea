package programmers.level3;
import java.util.*;

/**
 * test case
 * stones: [1, 4, 1, 3, 1, 2, 1] k:4 / answer: 3
 */
class Solution {
    int[] stones, jump;
    int limit, answer,S;
    boolean STOP;
    int MAX;

    public int solution(int[] stones, int k) {
        int max = -1;
        for(int i=0; i<stones.length; i++){
            if(max < stones[i])
                max = stones[i];
        }
        MAX = 0;
        binary(1, max);
        return MAX;
    }

    public void binary(int min, int max){
        if(min>max) return;

        int mid = (min+max)/2;

        if(crossable(mid)) {
            if(MAX<mid) MAX = mid;
            binary(mid+1, max);
        }
        else binary(min, mid-1);
    }

    public  boolean crossable(int mid){
        int cnt = 0;
        for(int stone:stones){
            if(stone-mid < 0 ) cnt++;
            else cnt = 0;

            if(cnt >=limit) return false;
        }
        return true;
    }

    // 정확도 100이지만 효율성 테스트 통과 못함
    public int solution_timeout(int[] stone, int k) {
        answer = 0;
        S = stone.length;
        jump = new int[S+2];
        stones = new int[S+2];
        STOP = false;
        MAX = 0;

        for(int i=0; i<S; i++){
            stones[i+1] = stone[i];
        }
        stones[S+1]=-1;
        limit = k;
        while(!STOP){
            move(0, 1, k-1);
        }
        return answer;
    }

    public void move(int from, int to, int k){
        if(k<0 || from > S+1) {
            STOP = true;
            return;
        }

        if(to == S+1){
            if(to-from != 1)
                jump[from] = to - from;
            answer++;
            return;
        }

        if(stones[to] != 0){
            if(to-from != 1)
                jump[from] = to - from;
            stones[to] -= 1;
            move(to, to+1,limit-1);
        }else{
            if(jump[to] != 0){
                move(from, to+jump[to], k-jump[to]);
            }else{
                move(from, to+1, k-1);
            }
        }
    }
}