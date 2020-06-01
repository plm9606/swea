package swea;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

class CalcResult{
    Box resultBox;
    int times;

    public CalcResult(Box resultBox, int times) {
        this.resultBox = resultBox;
        this.times = times;
    }
}

class Box{
    int height, width;
    int[][] arr;

    public Box(int height, int width, int[][] arr) {
        this.width = width;
        this.height = height;
        this.arr = arr;
    }
}

public class Solution_1260 {
    static int[][] map;
    static boolean[][] visited;
    static List<Box> boxes;
    static LinkedList<Box> orderedList;
    static int n;
    static int min;

    public static void main(String[] args)throws FileNotFoundException {
        System.setIn(new FileInputStream("res/input1260.txt"));


        Scanner sc = new Scanner(System.in);

        int tc = sc.nextInt();
        for(int test_case = 1; test_case <= tc; test_case++){
            n = sc.nextInt();
            map = new int[n][n];
            visited = new boolean[n][n];
            boxes = new ArrayList<>();
            orderedList = new LinkedList<>();
            min = Integer.MAX_VALUE;

            for(int i=0; i<n; i++){
                for(int j = 0; j<n; j++){
                    map[i][j] = sc.nextInt();
                }
            }

            for(int i=0; i<n; i++){
                for(int j = 0; j<n; j++){
                    if(!visited[i][j] && map[i][j] != 0){
                        search(i,j);
                    }
                }
            }

            orderedList.add(boxes.get(0));
            boxes.remove(0);

            while (boxes.size()>0){
                Box box = orderedList.getFirst();
                for(int i=0; i<boxes.size(); i++){
                    if(box.height == boxes.get(i).width){
                        orderedList.addFirst(boxes.get(i));
                        boxes.remove(i);
                        break;
                    }
                }

                box = orderedList.getLast();
                if(boxes.size() >0) {
                    for (int i = 0; i < boxes.size(); i++) {
                        if (box.width == boxes.get(i).height) {
                            orderedList.addLast(boxes.get(i));
                            boxes.remove(i);
                            break;
                        }
                    }
                }
            }

            int count = orderedList.size();
            permutation(count, count, new boolean[count], new int[count], 0);

            System.out.printf("#%d %d\n", test_case, min);

        }
    }

    public static CalcResult calculation(Box b1, Box b2){
        int newArr[][] = new int[b1.height][b2.width];

        for(int i=0; i<b1.height; i++){     // 0~1
            for(int k=0; k< b2.width; k++){     // 0~3
                int acc = 0;
                for(int j=0; j< b1.width; j++){     // 0~2
                acc += b1.arr[i][j] * b2.arr[j][k];
                }
                newArr[i][k] = acc;
            }
        }

        return new CalcResult(new Box(b1.height, b2.width, newArr), b2.width*b1.height*b1.width);
    }

    public static void permutation(int n, int r, boolean[] visited, int[] result, int picked){
        if(picked == r){
            // do something
            int times = 0;
            Box acc = orderedList.get(result[0]);

            for(int i=1; i<result.length; i++){
                CalcResult calcResult = calculation(acc, orderedList.get(result[i]));
                acc = calcResult.resultBox;
                times += calcResult.times;
            }
            if(min > times) min = times;

            return;
        }

        for(int i=0; i<n; i++){
            if(!visited[i]){
                visited[i] = true;
                result[picked] = i;
                permutation(n, r, visited, result, picked+1);
                visited[i] = false;
            }
        }
    }

    public static void search(int y, int x){
        int width=1, height=1;
        while (x+width < n && map[y][x+width] != 0){
            width++;
        }
        while (y+height < n && map[y+height][x] != 0){
            height++;
        }

        int[][] arr = new int[height][width];

        for(int i=y; i<y+height; i++){
            for(int j = x; j< x+width; j++){
                visited[i][j] = true;
                arr[i-y][j-x] = map[i][j];
            }
        }


        boxes.add(new Box(height, width, arr));
    }
}
