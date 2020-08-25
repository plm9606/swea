package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class 순위 {
    public int solution(int n, int[][] results) {
        int[][] scores = new int[n+1][n+1];

        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                if(i!=j) scores[i][j] = 1000000;
            }
        }

        for(int i=0; i<results.length; i++){
            scores[results[i][0]][results[i][1]] = 1;
        }

//        for(int i=1; i<=n; i++){
//            for(int j=1; j<=n; j++){
//                for(int k=1; k<=n; k++){
//                    if(scores[i][j] > scores[i][k] + scores[k][j]){
//                        System.out.printf("%d %d %d\n",i,j,k);
//                        scores[i][j] =  scores[i][k] + scores[k][j];
//                    }
//                }
//            }
//        }

        for(int k=1; k<=n; k++){
            for(int i=1; i<=n; i++){
                for(int j=1; j<=n; j++){
                    if(scores[i][j] > scores[i][k] + scores[k][j]){
                        System.out.printf("%d %d %d\n",i,j,k);
                        scores[i][j] =  scores[i][k] + scores[k][j];
                    }
                }
            }
        }

        for(int i=1; i<=n; i++){
            System.out.println(Arrays.toString(scores[i]));
        }

        int[] check = new int[n+1];
        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                if(scores[i][j] == scores[j][i]&&scores[j][i] == 1000000){
                    check[i]=1;
                    break;
                }
            }
        }


        long answer = Arrays.stream(check).filter(item->item==0).count();
        return (int)answer-1;
    }

    public int solution_B(int n, int[][] results){
        int answer = 0;
        HashSet<Integer>[][] sets = new HashSet[n+1][2];

        for(int i=0; i<results.length; i++){
            int win = results[i][0];
            int lose = results[i][1];

            sets[win][1].add(lose);
            sets[lose][0].add(win);
        }

        for(int i=1; i<=n; i++){
            ArrayList<Integer> stronger = new ArrayList<>(sets[i][0]);
            ArrayList<Integer> weaker = new ArrayList<>( sets[i][1]);

            for(int k : stronger){
                if(!sets[k][0].isEmpty()){
                    for(int s:sets[k][0]){
                        sets[i][0].add(s);
                    }
                }
            }

            for(int k: weaker){
                if(!sets[k][1].isEmpty()){
                    for(int s:sets[k][1]){
                        sets[i][1].add(s);
                    }
                }
            }
        }

        for(int i=1; i<=n; i++){
            System.out.println(sets[i][0]);
            System.out.println(sets[i][1]);
            System.out.println("---------------------------------------------------------------------");
        }



        return answer;
    }
}
