package algorithm;

public class Permutation {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4};
        permutation(arr, new int[arr.length], 0, 3);
    }

    public static void permutation(int[] arr, int[] order, int picked, int toPick){
        if(toPick == picked){
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
}
