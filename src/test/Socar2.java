class Socar2 {
    public int solution(String[] drum) {
        int answer = 0;
        int n = drum.length;
        int[][] board = new int[n][n];

        for (int i = 0; i < n; i++) {
            char[] query = drum[i].toCharArray();
            for (int j = 0; j < n; j++) {
                if (query[j] == '#') board[i][j] = 0;
                else if (query[j] == '>') board[i][j] = 1;
                else if (query[j] == '<') board[i][j] = 2;
                else board[i][j] = 3;
            }
        }

        for (int start = 0; start < n; start++) {
            int cnt = 0;
            int y = 0, x = start;
            while (true) {
                if (y == n) {
                    // 탈출 성공
                    answer++;
                    break;
                }

                if (board[y][x] == 0) {
                    y++;
                } else if (board[y][x] == 1) {
                    x++;
                } else if (board[y][x] == 2) {
                    x--;
                } else if (board[y][x] == 3) {
                    if (cnt == 1) break;
                    y++;
                    cnt++;
                }
            }
        }

        return answer;
    }
}