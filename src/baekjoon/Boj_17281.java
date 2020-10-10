package baekjoon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Boj_17281 {
    static int[][] play;
    static int N, max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("res/boj17281.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        play = new int[N][9];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                play[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean[] lineup = new boolean[9];
        lineup[0] = true;

        permutation(new int[9], 0, lineup);

        System.out.println(max);
    }

    public static void playGame(int[] lineup) {
        int score = 0;
        int playerNum = -1;
        for (int inning = 0; inning < N; inning++) {
            int[] result = play[inning];
            int out = 0;
            int cnt = 1;

            while (true) {
                playerNum = (playerNum + 1) % 9;
                int curPlayer = lineup[playerNum];
                int r = result[curPlayer];
                if (r == 0) {
                    out++;
                } else {
                    // 1~4는 해당 번호만큼 밀어주면 된다.
                    cnt <<= r;
                    // 타석(0번째)에 다음 사람을 위해 1을 세팅
                    cnt |= 1;
                }

                if (out == 3) break;
                // 3루를 넘어간 사람(2^4)이 있다면
                if (cnt > 15) {
                    // 2^4 ~2^8까지
                    for (int k = 1 << 4; k < 256; k <<= 1) {
                        // 해당 비트가 0이면 pass
                        if ((cnt & k) == 0) continue;
                        // 3루 이상 넘어간 사람 비트마스크에서 지우면서 score++
                        cnt &= ~k;
                        score++;
                    }
                }
            }
        }

        max = Math.max(max, score);
    }


//    public static void playGame(int[] lineup) {
//        int score = 0;
//        int playerNum = -1;
//        for (int inning = 0; inning < N; inning++) {
//            int[] result = play[inning];
//            int out = 0;
//            LinkedList<Integer> base = new LinkedList<>();
//            for (int i = 0; i < 3; i++) base.add(-1);
//
//            while (out < 3) {
//                playerNum = (playerNum + 1) % 9;
//                int curPlayer = lineup[playerNum];
//                int r = result[curPlayer];
//                if (r == 1) {
//                    int base3 = base.pollLast();
//                    if (base3 != -1) score++;
//                    base.addFirst(curPlayer);
//                } else if (r == 2) {
//                    for (int j = 0; j < 2; j++) {
//                        int base3 = base.pollLast();
//                        if (base3 != -1) score++;
//                    }
//                    base.addFirst(curPlayer);
//                    base.addFirst(-1);
//                } else if (r == 3) {
//                    for (int j = 0; j < 3; j++) {
//                        int base3 = base.pollLast();
//                        if (base3 != -1) score++;
//                    }
//                    base.addFirst(curPlayer);
//                    base.addFirst(-1);
//                    base.addFirst(-1);
//                } else if (r == 4) {
//                    for (int j = 0; j < 3; j++) {
//                        int base3 = base.pollLast();
//                        if (base3 != -1) score++;
//                        base.addFirst(-1);
//                    }
//                    score++;
//
//                } else {
//                    out++;
//                }
//                if (out == 3) break;
//            }
//        }
//
//        max = Math.max(max, score);
//    }

    public static void permutation(int[] order, int picked, boolean[] check) {
        if (order.length == picked) {
//            System.out.println(Arrays.toString(order));
            playGame(order);
            return;
        }

        if (picked == 3) {
            order[picked] = 0;
            permutation(order, picked + 1, check);
        } else {
            for (int i = 0; i < order.length; i++) {
                if (!check[i]) {
                    order[picked] = i;
                    check[i] = true;
                    permutation(order, picked + 1, check);
                    check[i] = false;
                }
            }
        }
    }

}
