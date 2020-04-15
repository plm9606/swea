package baekjoon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Boj_14499 {
    static int[][] dice;
    static List<int[]> swapList = new ArrayList<>();
    static int x, y, n, m;
    static int[][] arr;

    public static void main(String[] args) {
        // left-bottom-right-top
        swapList.add(new int[]{1, 0});
        swapList.add(new int[]{1, 1});
        swapList.add(new int[]{1, 2});
        swapList.add(new int[]{3, 1});

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        x = sc.nextInt();
        y = sc.nextInt();
        int k = sc.nextInt();
        arr = new int[n][m];
        dice = new int[4][3];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < k; i++) {
            int order = sc.nextInt();
            if (!canMove(order)) continue;
            if (order == 1) {
                order1();
                moveBoard(1);
            } else if (order == 2) {
                order2();
                moveBoard(2);
            } else if (order == 3) {
                order3();
                moveBoard(3);
            } else if (order == 4) {
                order4();
                moveBoard(4);
            }
        }
    }

    public static void order1() {
        int temp = dice[1][0];
        for (int i = 0; i < 4; i++) {
            int y = swapList.get(i)[0];
            int x = swapList.get(i)[1];
            if (i + 1 <= 3) {
                dice[y][x] = dice[swapList.get(i + 1)[0]][swapList.get(i + 1)[1]];

            } else {
                dice[y][x] = temp;
            }
        }
        System.out.println(dice[3][1]);
    }

    public static void order2() {
        int temp = dice[3][1];

        for (int i = 3; i >= 0; i--) {
            int y = swapList.get(i)[0];
            int x = swapList.get(i)[1];
            if (i - 1 >= 0) {
                dice[y][x] = dice[swapList.get(i - 1)[0]][swapList.get(i - 1)[1]];
            } else {
                dice[y][x] = temp;
            }
        }
        System.out.println(dice[3][1]);
    }

    public static void order3() {
        int temp = dice[3][1];

        for (int i = 3; i >= 0; i--) {
            if (i - 1 >= 0) {
                dice[i][1] = dice[i - 1][1];
            } else {
                dice[i][1] = temp;
            }
        }
        System.out.println(dice[3][1]);

    }

    public static void order4() {
        int temp = dice[0][1];

        for (int i = 0; i < 4; i++) {
            if (i + 1 <= 3)
                dice[i][1] = dice[i + 1][1];
            else dice[i][1] = temp;
        }

        System.out.println(dice[3][1]);
    }

    public static boolean canMove(int order) {
        if (order == 1) {
            if (x + 1 < m) {
                x += 1;
                return true;
            }
        } else if (order == 2) {
            if (x - 1 >= 0) {
                x -= 1;
                return true;
            }
        } else if (order == 3) {
            if (y - 1 >= 0) {
                y -= 1;
                return true;
            }
        } else if (order == 4) {
            if (y + 1 < n) {
                y += 1;
                return true;
            }
        }
        return false;
    }

    public static void moveBoard(int order) {
        int bottom = dice[1][1];

        if (arr[y][x] == 0) {
            dice[y][x] = bottom;
        } else {
            dice[1][1] = arr[y][x];
            arr[y][x] = 0;
        }
    }
}
