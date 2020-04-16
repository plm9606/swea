package programmers.level2;

import java.util.Arrays;
import java.util.Stack;

public class 카카오프렌즈_컬러링북 {
    public static void main(String[] args) {
        int[][] pic = {{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}};
        int[][] pic2 = {{1, 1, 1, 0}, {1, 1, 1, 0}, {0, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 1}};
        int[][] pic3 = {{1, 0, 1, 0}, {0, 1, 0, 1}, {1, 0, 1, 0}, {0, 1, 0, 1}, {1, 0, 1, 0}, {0, 1, 0, 1}};
        int[][] pic4 = {{0, 1, 0, 0}, {1, 1, 1, 0}, {0, 1, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};

        System.out.println(Arrays.toString(solution(6,4,pic4)));
    }
    public static int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        boolean[][] visited = new boolean[m][n];

        for(int y=0; y<m; y++){
            for(int x=0; x<n; x++){
                if(picture[y][x] == 0){
                    visited[y][x] = true;
                }
            }
        }

        for(int y=0; y<m; y++){
            for(int x=0; x<n; x++){
                if(!visited[y][x]){
                    int count = findArea(visited, picture, y, x, m,n);
                    numberOfArea++;
                    if(count > maxSizeOfOneArea) {
                        maxSizeOfOneArea = count;
                    }
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;

        return answer;
    }

    public static int findArea(boolean[][] visited, int[][] picture, int y, int x, int m, int n){
        Stack<int[]> stack = new Stack<>();
        int color = picture[y][x];
        int count = 1;
        stack.push(getPoint(y,x));

        while (!stack.isEmpty()){
            int[] curPoint = stack.pop();
            int curY = curPoint[0];
            int curX = curPoint[1] ;

            if(curY+1 < m && picture[curY+1][curX] == color){
                if(!visited[curY+1][curX]){
                    visited[curY+1][curX] = true;
                    stack.push(getPoint(curY+1, curX));
                    count++;
                }
            }
            if(curX + 1 < n && picture[curY][curX+1] == color){
                if(!visited[curY][curX+1]){
                    visited[curY][curX+1] = true;
                    stack.push(getPoint(curY, curX+1));
                    count++;
                }
            }
        }
        return count;
    }

    public static int[] getPoint(int y, int x){
        int[] point = {y,x};
        return point;
    }
}
