import java.util.LinkedList;
import java.util.Queue;

class Socar3 {
    int[] dy = {0, 1, 0, -1}, dx = {1, 0, -1, 0};
    int[][][] delivery;
    int r, answer = 0, maxTime = 0;

    public int solution(int r, int[][] delivery) {
        this.r = r;
        this.delivery = new int[r][r][2];
        int idx = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < r; j++) {
                int[] query = delivery[idx++];
                this.delivery[i][j][0] = query[0];
                this.delivery[i][j][1] = query[1];
                maxTime = Math.max(maxTime, query[0]);
            }
        }

        bfs();
        return answer;
    }

    public void bfs() {
        Queue<Order> q = new LinkedList<>();
        q.add(new Order(0, 0, 0, 0, new boolean[r][r], r * r));

        while (!q.isEmpty()) {
            Order order = q.poll();
            int curFee = order.curFee;
            int[] query = delivery[order.y][order.x];

            if (order.toVisit == 0 || order.time == maxTime + 1) {
                answer = Math.max(answer, order.curFee);
                continue;
            }

            if (order.time <= query[0] && !order.visited[order.y][order.x]) {
                order.toVisit -= 1;
                curFee += query[1];
                order.visited[order.y][order.x] = true;
            }

            for (int i = 0; i < 4; i++) {
                int yy = order.y + dy[i];
                int xx = order.x + dx[i];
                if (yy >= 0 && yy < r && xx >= 0 && xx < r)
                    q.add(new Order(yy, xx, order.time + 1, curFee, deepCopy(order.visited, r), order.toVisit));
            }
        }
    }

    public boolean[][] deepCopy(boolean[][] original, int n) {
        boolean[][] result = new boolean[n][n];
        for (int i = 0; i < original.length; i++) {
            System.arraycopy(original[i], 0, result[i], 0, original[i].length);
        }
        return result;
    }
}

class Order {
    int y, x, time, curFee, toVisit;
    boolean visited[][];

    public Order(int y, int x, int time, int curFee, boolean[][] visited, int toVisit) {
        this.visited = visited;
        this.y = y;
        this.x = x;
        this.time = time;
        this.curFee = curFee;
        this.toVisit = toVisit;
    }
}