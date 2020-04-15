package programmers.level1;

import java.util.Stack;

public class Doll {
    public static void main(String[] args) {
        int[][] board = {{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}};
        int[] moves = {1,5,3,5,1,2,1,4};
        int[][] b = {{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}, {0, 2, 3, 0, 2}, {0, 1, 4, 5, 4}};
        int[] m = {2, 2, 3, 5, 3, 3, 5, 5, 4};

        int res = solution(b, m);
        System.out.println(res);
    }

    /**
     * board.length = depth
     * board[0].length = row 수
     */
    public static int  solution(int[][] board, int[] moves){
        int answer =0;
        int depth = board.length;
        int rowCount = board[0].length;
        int[] top = new int[rowCount];
        Stack<Integer> stack = new Stack<>();

        for(int x=0; x<rowCount; x++){
            for(int y=0; y<depth; y++){
                if(board[y][x] == 0){
                    top[x] = y;
                }else {
                    break;
                }
            }
        }

        for(int m: moves){

        }
        for(int i=0; i< moves.length; i++){
            int selectedRow = moves[i]-1;
            int topPointer = ++top[selectedRow];
            if(topPointer >= depth) continue;

            int cur = board[topPointer][selectedRow];

            if(!stack.isEmpty()){
                if(stack.peek() == cur){
                    answer+=2;
                    stack.pop();
                }else {
                    stack.push(cur);
                }
            }else {
                stack.push(cur);
            }
        }
        return answer;
    }
}
//[[0,0,0,0,0],
//[0,0,1,0,3],
//[0,2,5,0,1],
//[4,2,4,4,2],
//[3,5,1,3,1]]