package programmers;

import java.util.ArrayList;
import java.util.Collections;

public class 기둥과_보_설치 {
    static int BEAM = 1, PILLAR = 0, DEL = 0, CONSTRUCT = 1, N;
    static int[][][] board;

    public static void main(String[] args) {
//        solution(5, new int[][]{{1, 0, 0, 1}, {1, 1, 1, 1}, {2, 1, 0, 1}, {2, 2, 1, 1}, {5, 0, 0, 1}, {5, 1, 0, 1}, {4, 2, 1, 1}, {3, 2, 1, 1}});
        solution(5, new int[][]{{0, 0, 0, 1}, {2, 0, 0, 1}, {4, 0, 0, 1}, {0, 1, 1, 1}, {1, 1, 1, 1}, {2, 1, 1, 1}, {3, 1, 1, 1}, {2, 0, 0, 0}, {1, 1, 1, 0}, {2, 2, 0, 1}});
    }

    public static int[][] solution(int n, int[][] build_frame) {
        N = n;
        board = new int[n + 1][n + 1][2];
        ArrayList<Point> list = new ArrayList<>();
        for (int[] query : build_frame) {
            int x = query[0];
            int y = query[1];
            int a = query[2];
            int b = query[3];

            if (b == DEL) {
                board[y][x][a] = 0;
            } else {
                board[y][x][a] = 1;
            }
            if (!check(x, y, a, b)) {
                if (b == DEL) {
                    board[y][x][a] = 1;
                } else {
                    board[y][x][a] = 0;
                }
            } else {
                if (b == DEL) {
                    list.removeIf(p -> p.x == x && p.y == y && p.type == a);
                } else {
                    list.add(new Point(x, y, a));
                }
            }

        }

        Collections.sort(list);
        int[][] answer = new int[list.size()][3];
        for (int i = 0; i < list.size(); i++) {
            Point p = list.get(i);
            answer[i][0] = p.x;
            answer[i][1] = p.y;
            answer[i][2] = p.type;
        }

        System.out.println(list);
        return answer;
    }

    private static boolean check(int x, int y, int a, int b) {
        int yy = a == BEAM ? y : y + 1;
        int xx = a == BEAM ? x + 1 : x;

        // 벽면을 벗어나는지
        if (y < 0 || y > N || x < 0 || x > N) return false;
        if (yy < 0 || yy > N || xx < 0 || xx > N) return false;

        if (b == CONSTRUCT) {
            if (a == PILLAR && checkPillar(x, y)) {
                return true;
            } else if (a == BEAM && checkBeam(x, y)) {
                return true;
            } else {
                return false;
            }
        } else {
            loop:
            for (int i = 0; i <= N; i++) {
                for (int j = 0; j <= N; j++) {
                    if (board[i][j][PILLAR] == 1) {
                        if (!checkPillar(j, i)) return false;
                    }
                    if (board[i][j][BEAM] == 1) {
                        if (!checkBeam(j, i)) return false;
                    }
                }
            }
            return true;
        }
    }

    private static boolean checkPillar(int x, int y) {
//        if (board[y][x][PILLAR] == 0) return true;
        // 바닥 위
        if (y == 0) {
            return true;
        }
        // 보의 한쪽 끝에 있거나
        else if ((x - 1 >= 0 && board[y][x - 1][BEAM] == 1) || (board[y][x][BEAM] == 1)) {
            return true;
        }
        // 다른 기둥 위에 있거나
        else if (y - 1 >= 0 && board[y - 1][x][PILLAR] == 1) {
            return true;
        }
        return false;
    }

    private static boolean checkBeam(int x, int y) {
//        if (board[y][x][BEAM] == 0) return true;

        // 바닥에 설치 불가
        if (y == 0) return false;

        // 한쪽 끝이 기둥 위
        if ((y - 1 >= 0 && board[y - 1][x][PILLAR] == 1) || (y - 1 >= 0 && x + 1 <= N && board[y - 1][x + 1][PILLAR] == 1)) {
            return true;
        }
        // 양쪽 끝이 다른 보와 동시 연결
        if (x + 1 <= N && board[y][x + 1][BEAM] == 1 && x - 1 >= 0 && board[y][x - 1][BEAM] == 1) {
            return true;
        }

        return false;
    }
}

class Point implements Comparable<Point> {
    int x, y, type;

    public Point(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    @Override
    public String toString() {
        return "[" + this.x + ", " + this.y + ", " + this.type + "]";
    }

    @Override
    public int compareTo(Point point) {
        if (this.x < point.x) return -1;
        else if (this.x > point.x) return 1;
        else {
            if (this.y < point.y) return -1;
            else if (this.y > point.y) return 1;
            else return 0;
        }
    }
}