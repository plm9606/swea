package baekjoon;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Boj_1495 {
    static int M, N, answer;
    static int[] vol;
    static int[][] memo;
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("res/boj_1495.txt"));

        Scanner sc = new Scanner(System.in);
        answer = -1;
         N = sc.nextInt();
        int S = sc.nextInt();
        M = sc.nextInt();
        vol =new int[N];
        memo = new int[N][M+1];
        for(int i=0; i<N; i++){
            vol[i] = sc.nextInt();
        }

        // 초기값 세팅
        if(S + vol[0] <= M)memo[0][S+vol[0]] = 1;
        if(S - vol[0] >= 0)memo[0][S-vol[0]] = 1;

        // 두번째 곡부터 계산
        for(int i=1; i<N; i++){
            for(int k=0; k<=M;k++){
                // 현재 변경할 볼륨이 이전단계에서 변경되었는지 확인
                if(memo[i-1][k]==0){
                    continue;
                }
                if(k-vol[i] >= 0){
                    memo[i][k-vol[i]] = 1;
                }
                if(k+vol[i] <= M){
                    memo[i][k+vol[i]] = 1;
                }
            }
        }

        for(int i=M; i>=0; i--){
            if(memo[N-1][i] ==1){
                answer = i;
                break;
            }
        }

        System.out.println(answer);
    }

    public static void backtracing(int i, int volume){
        if(i==N){
            if(answer < volume) answer = volume;
            return;
        }
        if(volume < 0 || volume > M) return;

        if(volume + vol[i] <= M){
            backtracing(i+1, volume+vol[i]);
        }

        if(volume - vol[i] >= 0){
            backtracing(i+1, volume- vol[i]);
        }
    }
}
