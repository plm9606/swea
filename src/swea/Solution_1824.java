package swea;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution_1824 {
    static int[] dy = {-1, 0, 1, 0}, dx = {0, 1, 0, -1};
    static int R, C;
    static char[][] program;
    static Queue<Query> q;
    static boolean[][][][] check;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("res/input1824.txt"));
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        for (int test_case = 1; test_case <= tc; test_case++) {
            String answer = "NO";

            R = sc.nextInt();
            C = sc.nextInt();
            program = new char[R][C];
            // check[y][x][mem][dir]
            check = new boolean[R][C][16][4];

            for (int i = 0; i < R; i++) {
                char[] row = sc.next().toCharArray();
                for (int j = 0; j < C; j++) {
                    program[i][j] = row[j];
                }
            }

            q = new LinkedList<>();
            q.add(new Query(0, 0, 0, 1));

            while (!q.isEmpty()) {
                Query query = q.poll();
                if (program[query.y][query.x] == '@') {
                    answer = "YES";
                    break;
                }
                if (check[query.y][query.x][query.memory][query.dir]) {
                    continue;
                }

                check[query.y][query.x][query.memory][query.dir] = true;
                doQuery(query);
            }

            System.out.printf("#%d %s\n", test_case, answer);

        }
    }

    static void doQuery(Query query) {
        char operator = program[query.y][query.x];
        if (operator == '?') {
            for (int i = 0; i < 4; i++) {
                query.dir = i;
                move(query);
            }
            return;
        }

        if (operator == '<') {
            query.dir = 3;
        } else if (operator == '>') {
            query.dir = 1;
        } else if (operator == '^') {
            query.dir = 0;
        } else if (operator == 'v') {
            query.dir = 2;
        } else if (operator == '_') {
            query.dir = query.memory == 0 ? 1 : 3;
        } else if (operator == '|') {
            query.dir = query.memory == 0 ? 2 : 0;
        } else if (operator == '+') {
            query.memory = query.memory == 15 ? 0 : query.memory + 1;
        } else if (operator == '-') {
            query.memory = query.memory == 0 ? 15 : query.memory - 1;
        } else if (operator == '.') {

        } else {
            query.memory = Integer.parseInt(operator + "");
        }

        move(query);
    }

    static void move(Query query) {
        int xx = query.x + dx[query.dir];
        int yy = query.y + dy[query.dir];
        if (yy >= R) {
            yy = 0;
        } else if (yy < 0) {
            yy = R - 1;
        }
        if (xx >= C) {
            xx = 0;
        } else if (xx < 0) {
            xx = C - 1;
        }

        q.add(new Query(yy, xx, query.memory, query.dir));
    }

}

class Query {
    int y, x, memory, dir;

    public Query(int y, int x, int memory, int dir) {
        this.y = y;
        this.x = x;
        this.memory = memory;
        this.dir = dir;
    }
}
