package programmers;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class 자물쇠와_열쇠 {

    public static void main(String[] args) {
//        solution(new int[][]{{0,0,0},{1,0,0},{0,1,1}}, new int[][]{{1,1,1},{1,1,0},{1,0,1}});
        int[][] k = {{0, 1, 1, 1, 1}, {1, 0, 0, 1, 0}, {0, 0, 1, 0, 1}, {0, 1, 1, 1, 0}, {1, 0, 0, 1, 0}};
        int [][] l = {{1, 1, 1}, {0, 0, 1}, {0, 1, 0}};

//        solution(k,l);
        solution(new int[][]{{1,1,1},{1,1,1},{1,1,1}}, new int[][]{{1,1,1},{1,1,1},{1,1,0}});
    }
        static ArrayList<int[][]> keys;
    static int[][] padding;
    static int k,l;
        public static boolean solution(int[][] key, int[][] lock) {
            boolean answer = true;
            k = key.length;
            l = lock.length;
            padding = new int[l+2*(k-1)][l+2*(k-1)];
            keys = new ArrayList<>();
            keys.add(key);

            initializeLock(lock);

            for (int[] r: padding){
                System.out.println(Arrays.toString(r));
            }
            System.out.println("-----------------");

            // 회전된 key 만들기
            for(int cnt=0; cnt<3; cnt++){
                int[][] arr = new int[k][k];
                int[][] acc = keys.get(cnt);
                for(int i=0; i<k; i++){
                    for(int j=0; j<k;j++){
                        arr[i][j]=acc[k-1-j][i];
                    }
                }

                // for(int i=0; i<k; i++){
                //     System.out.println(Arrays.toString(arr[i]));
                // }
                // System.out.println("------------------------------");
                keys.add(arr);
            }

            for(int i=0; i<padding.length; i++){
                for(int j=0; j<padding.length; j++){
                    if(!(i+k<=padding.length && j+k<=padding.length)) continue;

                    for(int cnt=0; cnt<4; cnt++){
                        // add key in lock
                        addKey(i,j, keys.get(cnt));

                        System.out.printf("i: %d, j:%d, cnt: %d\n", i, j, cnt);
                        for (int[] r: padding){
                            System.out.println(Arrays.toString(r));
                        }
                        System.out.println("-----------------");

                        // check lock
                        boolean flag = checkLock();
                        if(flag) return true;
                        else{
                            // reset lock
                            initializeLock(lock);
                        }
                    }
                }
            }
            return false;
        }

        public static void addKey(int i, int j, int[][] key){
            for(int y=0; y<k; y++){
                for(int x=0; x<k;x++){
                    padding[i+y][j+x] += key[y][x];
                }
            }
        }

        public static boolean checkLock(){
            boolean flag = true;
            loop:for(int dy=k-1; dy<k+l-1; dy++){
                for(int dx=k-1; dx<k+l-1;dx++){
                    if(padding[dy][dx] != 1){
                        flag = false;
                        break loop;
                    }
                }
            }
            return flag;
        }

        public static void initializeLock(int[][] lock){
            for(int i=k; i<k+l; i++){
                for(int j=k; j<k+l;j++){
                    padding[i-1][j-1] = lock[i-k][j-k];
                }
            }
        }
}
