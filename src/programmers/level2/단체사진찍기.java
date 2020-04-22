package programmers.level2;

import java.util.HashMap;
import java.util.Map;

public class 단체사진찍기 {
    static Map<Character, Integer> map;
    static int[] assignedNum;
    static boolean[] visited;
    static int count;

    public static void main(String[] args) {
        String[] data = {"N~F=0", "R~T>2"};
        System.out.println(solution(2, data));
    }

    public static int solution(int n, String[] data){
        count = 0;
        assignedNum = new int[8];
        visited = new boolean[8];
        map = new HashMap<>();
        map.put('A', 0);
        map.put('C', 1);
        map.put('F', 2);
        map.put('J', 3);
        map.put('M', 4);
        map.put('N', 5);
        map.put('R', 6);
        map.put('T', 7);

        dfs(0, data);

        return count;
    }

    public static void dfs(int pos, String[] data){
        if(pos == 8 ){
            int friend1, friend2, distance;
            char sign;
            for(int i=0; i<data.length; i++) {
                friend1 = assignedNum[map.get(data[i].charAt(0))];
                friend2 = assignedNum[map.get(data[i].charAt(2))];
                distance = data[i].charAt(4) - '0';
                sign = data[i].charAt(3);

                if (sign == '<') {
                    if (Math.abs(friend1 - friend2)-1 >= distance) return;
                } else if (sign == '=') {
                    if (Math.abs(friend1 - friend2)-1 != distance) return;
                } else if (sign == '>') {
                    if (Math.abs(friend1 - friend2)-1 <= distance) return;
                }
            }
            count++;
            return;
        }

        for(int i = 0; i<8; i++){
            if(!visited[i]){
                visited[i] = true;
                assignedNum[pos] = i;
                dfs(pos+1, data);
                visited[i] = false;
            }
        }
    }
}
