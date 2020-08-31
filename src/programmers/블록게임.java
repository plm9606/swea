package programmers;

public class 블록게임 {
    // int[][][] blocks = new int[][][]{{{0, 0}, {0, 1}, {0, 2}, {1, 2}}, {{0, 0}, {0, 1}, {1, 0}, {2, 0}}, {{0, 0}, {1, 0}, {1, 1}, {1, 2}}, {{0, 0}, {1, 0}, {2, 1}, {2, 0}},
    //         {{0, 0}, {0, 1}, {0, 2}, {1, 0}}, {{0, 0}, {1, 0}, {2, 0}, {2, 1}}, {{0, 0}, {1, -2}, {1, -1}, {1, 0}}, {{0, 0}, {0, 1}, {1, 1}, {1, 2}},
    //         {{0, 0}, {1, -1}, {1, 0}, {1, 1}}, {{0, 0}, {1, 0}, {1, 1}, {2, 0}}, {{0, 0}, {0, 1}, {0, 2}, {1, 1}}, {{0, 0}, {1, -1}, {1, 0}, {2, 0}}
    // };
    static int[][][] blocks = new int[][][]{{{0, 0}, {1, 0}, {1, 1}, {1, 2}}, {{0, 0}, {1, 0}, {2, -1}, {2, 0}},
            {{0, 0}, {1, 0}, {2, 0}, {2, 1}}, {{1, -2}, {1, -1}, {0, 0}, {1, 0}},
            {{1, -1}, {0, 0}, {1, 0}, {1, 1}}};
    static int[][][] empty = new int[][][]{{{0, 1}, {0, 2}}, {{0, -1}, {1, -1}}, {{0, 1}, {1, 1}}, {{0, -2}, {0, -1}}, {{0, -1}, {0, 1}}};

    public static void main(String[] args) {
        solution(new int[][]{{0, 0, 0, 0}, {0, 0, 0, 1}, {2, 0, 1, 1}, {2, 2, 2, 1}});
        int[][] arr = new int[][]{{0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 1, 2, 2, 0, 0, 3}, {0, 0, 1, 2, 0, 3, 3, 3}, {0, 1, 1, 2, 0, 5, 4, 4}, {0, 7, 6, 6, 6, 5, 0, 4}, {7, 7, 7, 6, 0, 5, 5, 4}};
//        solution(arr);

        int[][] board = new int[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 4, 0, 0, 0}, {0, 0, 0, 0, 0, 4, 4, 0, 0, 0}, {0, 0, 0, 0, 3, 0, 4, 0, 0, 0}, {0, 0, 0, 2, 3, 0, 0, 0, 5, 5}, {1, 2, 2, 2, 3, 3, 0, 0, 0, 5}, {1, 1, 1, 0, 0, 0, 0, 0, 0, 5}};
//        solution(board);
    }

    public static int solution(int[][] board) {
        int answer = 0;
        int n = board.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[j][i] != 0) break;
                board[j][i] = -1;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int blockIdx = board[i][j];
                if (board[i][j] > 0) {
                    int idx = 0;
                    for (int[][] block : blocks) {
                        boolean flag = true;
                        int cnt = 0;
                        for (int[] point : block) {
                            int yy = i + point[0];
                            int xx = j + point[1];
                            if (yy < 0 || yy >= n || xx < 0 || xx >= n) {
                                flag = false;
                                break;
                            }
                            if (!(board[yy][xx] == -1 || board[yy][xx] == blockIdx)) {
                                flag = false;
                                break;
                            }
                            if (board[yy][xx] == blockIdx) cnt++;
                        }
                        if (flag && cnt == 4) {
                            boolean hasEmpty = false;
                            for (int[] e : empty[idx]) {
                                if (!(board[i + e[0]][j + e[1]] == -1 || board[i + e[0]][j + e[1]] == blockIdx)) {
                                    hasEmpty = true;
                                    break;
                                }
                            }
                            if (!hasEmpty) {
                                System.out.println(i + " " + j);
                                for (int[] point : block) {
                                    int yy = i + point[0];
                                    int xx = j + point[1];
                                    if (yy - 1 < 0) {
                                        board[yy][xx] = -1;
                                    } else {
                                        if (board[yy - 1][xx] == -1) board[yy][xx] = -1;
                                    }
                                }
                                answer++;
                                break;
                            }
                            j = 0;
                        }
                        idx++;
                    }
                }
            }
        }
        return answer;
    }

//    public static int solution(int[][] board) {
//        int answer = 0;
//        int N = board.length;
//
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < N; j++) {
//                if (board[j][i] != 0) break;
//                board[j][i] = -1;
//            }
//        }
//
//        for (int y = 0; y < N; y++) {
//            for (int x = 0; x < N; x++) {
//                if (board[y][x] > 0) {
//                    int idx = 0;
//                    for (int[][] block : blocks) {
//                        int blockCnt = 1;
//                        boolean flag = true;
//                        int blockIdx = board[y][x];
//                        for (int[] point : block) {
//                            int yy = y + point[0];
//                            int xx = x + point[1];
//                            if (yy < 0 || yy >= N || xx < 0 || xx >= N) {
//                                flag = false;
//                                break;
//                            }
//                            if (!(board[yy][xx] == -1 || board[yy][xx] == blockIdx)) {
//                                flag = false;
//                                break;
//                            }
//                            if (board[yy][xx] == blockIdx) blockCnt++;
//                        }
//                        if (blockCnt == 4 && flag) {
//                            if (y - 1 >= 0) {
//                                if (board[y - 1][x] == -1) board[y][x] = -1;
//                            } else board[y][x] = -1;
//
//                            for (int[] p : blocks[idx]) {
//                                int yy = y + p[0];
//                                int xx = x + p[1];
//                                if (yy - 1 < 0) {
//                                    if (board[yy][xx] == blockIdx) board[yy][xx] = -1;
//                                } else if (board[yy - 1][xx] == -1 && board[yy][xx] == blockIdx) board[yy][xx] = -1;
//                            }
//
//                            System.out.println(y + " " + x);
//                            answer++;
//                            break;
//                        }
//                        idx++;
//                    }
//                }
//            }
//        }
//        return answer;
//    }
}

class Solution_블록게임 {
    int[][][] blocks = new int[][][]{{{0, 0}, {1, 0}, {1, 1}, {1, 2}}, {{0, 0}, {1, 0}, {2, -1}, {2, 0}},
            {{0, 0}, {1, 0}, {2, 0}, {2, 1}}, {{1, -2}, {1, -1}, {0, 0}, {1, 0}},
            {{1, -1}, {0, 0}, {1, 0}, {1, 1}}};
    int[][][] empty = new int[][][]{{{0, 1}, {0, 2}}, {{0, -1}, {1, -1}}, {{0, 1}, {1, 1}}, {{0, -2}, {0, -1}}, {{0, -1}, {0, 1}}};

    public int solution(int[][] board) {
        int answer = 0;
        int n = board.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[j][i] != 0) break;
                board[j][i] = -1;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int blockIdx = board[i][j];
                if (board[i][j] > 0) {
                    int idx = 0;
                    loop:
                    for (int[][] block : blocks) {
                        boolean flag = true;
                        int cnt = 0;
                        for (int[] point : block) {
                            int yy = i + point[0];
                            int xx = j + point[1];
                            if (yy < 0 || yy >= n || xx < 0 || xx >= n) {
                                flag = false;
                                break;
                            }
                            if (!(board[yy][xx] == -1 || board[yy][xx] == blockIdx)) {
                                flag = false;
                                break;
                            }
                            if (board[yy][xx] == blockIdx) cnt++;
                        }
                        if (flag && cnt == 4) {
                            boolean hasEmpty = false;
                            for (int[] e : empty[idx]) {
                                if (!(board[i + e[0]][j + e[1]] == -1 || board[i + e[0]][j + e[1]] == blockIdx)) {
                                    hasEmpty = true;
                                    break;
                                }
                            }
                            if (!hasEmpty) {
                                System.out.println(i + " " + j);
                                for (int[] point : block) {
                                    int yy = i + point[0];
                                    int xx = j + point[1];
                                    if (yy - 1 < 0) {
                                        board[yy][xx] = -1;
                                    } else {
                                        if (board[yy - 1][xx] == -1) board[yy][xx] = -1;
                                    }
                                }
                                answer++;
                                j = -1;
                                break loop;
                            }
                        }
                        idx++;
                    }
                }
            }
        }
        return answer;
    }
}