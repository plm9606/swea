package programmers;

import java.util.Arrays;

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
}
