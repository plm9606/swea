package swea;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Solution_1258 {
    static int[][] arr;
   static boolean[][] visited;
    static int n;
    static int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
    static List<Matrix> list;

    public static void main(String[] args)throws FileNotFoundException {
        System.setIn(new FileInputStream("res/input1258.txt"));
        Scanner sc = new Scanner(System.in);
        sc.nextInt();

        for(int test_case = 1; test_case <= 10; test_case++){
            n = sc.nextInt();
            arr = new int[n][n];
            visited = new boolean[n][n];
            list = new ArrayList<>();
            int total = 0;

            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    arr[i][j] = sc.nextInt();
                }
            }

//            for(int i=0; i<n; i++){
//                for(int j=0; j<n; j++){
//                    if(!visited[i][j] && arr[i][j] != 0){
//                        dfs(i,j);
//                        total++;
//                    }
//                }
//            }

            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    if(!visited[i][j] && arr[i][j]!= 0){
                        list.add(search(i,j));
                        total++;
                    }
                }
            }

            Collections.sort(list, new Comparator<Matrix>() {
                @Override
                public int compare(Matrix m1, Matrix m2) {
                    if(m1.size > m2.size) return 1;
                    else if (m1.size < m2.size) return -1;
                    else {
                        if(m1.c > m2.c) return 1;
                        else if(m1.c < m2.c) return -1;
                        else return 0;
                    }
                }
            });


            System.out.printf("#%d %d ", test_case, total);
            for(Matrix matrix: list){
                System.out.print(matrix.c + " " + matrix.w + " ");
            }
            System.out.println();
        }
    }

    public static void dfs(int y, int x){
        visited[y][x] = true;

        for(int i=0; i<4; i++){
            int dx = x+ dir[i][1];
            int dy = y+ dir[i][0];

            if(isInside(dy, dx) && !visited[dy][dx] && arr[dy][dx] != 0){
                System.out.println("["+dy+", "+dx+"]");
                dfs(dy, dx);
            }
        }
    }

    public static boolean isInside(int y, int x){
        return (x >=0 && x<n && y>=0 && y<n);
    }

    public static Matrix search(int i, int j){
        int[][] dir = {{0,1},{1,0}};

        int y=i, x=j;
        int row = 1;
        int col=1;

        // col 구하기
        while (true){
            int dy = y+dir[0][1];
            int dx = x + dir[0][0];
            if(isInside(dy, dx) && arr[dy][dx]!=0){
                col++;
                x=dx;
                y=dy;
            }else {
                break;
            }
        }

        //row 구하기
        while (true){
            int dy = y+dir[1][1];
            int dx = x + dir[1][0];
            if(isInside(dy, dx) && arr[dy][dx]!=0){
                row++;
                x=dx;
                y=dy;
            }else {
                break;
            }
        }

        for(int k=i; k< i+col; k++){
            for(int l=j; l<j+row; l++){
                visited[k][l] = true;
            }
        }

        return new Matrix(row, col, row*col);
    }
}

class Matrix{
    int w,c,size;

    public Matrix(int w, int c, int size) {
        this.w = w;
        this.c = c;
        this.size = size;
    }
}
