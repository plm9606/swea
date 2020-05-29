package algorithm;

import java.util.ArrayList;
import java.util.Arrays;

public class Permutation {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4};
        permutation(arr, new int[arr.length], 0, 3);
    }

    public static void permutation(int[] arr, int[] order, int picked, int toPick){
        if(toPick == picked){
            System.out.println(Arrays.toString(order));
            print(arr, order, toPick);
            return;
        }

        boolean[] check = new boolean[arr.length];
        for(int i=0; i<picked; i++){
            check[order[i]] = true;
        }

        for(int i=0; i<arr.length; i++){
            if(!check[i]){
                order[picked] = i;
                permutation(arr, order, picked+1, toPick);
            }
        }
    }

    public static void print(int[]arr, int[] order, int toPick){
        for(int i=0; i<toPick; i++){
            int idx = order[i];
            System.out.print(arr[idx] + " ");
        }
        System.out.println();
    }

    // n개 중에 r개를 뽑는 경우, 순서를 지키며
    private static void perm(int[] arr, int[] output, boolean[] visited, int depth, int n, int r){
        if(depth == r){
            System.out.println(Arrays.toString(output));
            return;
        }

        for(int i=0; i<n; i++){
            if(visited[i] != true){ // 아직 방문하지 않았다면
                visited[i] = true;
                output[depth] = arr[i];
                perm(arr, output, visited, depth+1, n, r);
                visited[i] = false;
            }
        }
    }
}
