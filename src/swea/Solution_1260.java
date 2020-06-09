package swea;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

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
    static int dp[][];
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
            dp= new int[count][count];
            for(int i=0; i<count; i++){
                for(int j=0; j<count; j++){
                    dp[i][j] = -1;
                }
            }
            int min = getMinTimes(0, count-1);
            System.out.printf("#%d %d\n", test_case, min);

        }
    }


    public static int getMinTimes(int start, int end) {
        if(start==end){
           return 0;
        }
        if (dp[start][end] != -1) return dp[start][end];

        int min = Integer.MAX_VALUE;
       for(int i=start; i<end; i++){
           min = Math.min(min, getMinTimes(start, i) + getMinTimes(i+1, end) + (orderedList.get(start).height*orderedList.get(i).width*orderedList.get(end).width));
       }

       return dp[start][end] = min;
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
