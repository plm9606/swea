package baekjoon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Boj_17825 {
    static HashMap<Integer, GameNode> map;
    static int max = Integer.MIN_VALUE;
    static int dicePlay[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("res/boj17825.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        dicePlay = new int[10];
        for (int i = 0; i < 10; i++) {
            dicePlay[i] = Integer.parseInt(st.nextToken());
        }

        HashSet<Integer> marker = new HashSet<>();
        for (int i = 0; i < 4; i++) marker.add(i);

        makeBoard();

        play(0, new int[4], 0);
        System.out.println(max);
    }

    public static boolean check(int[] dice, int idx) {
        for (int diceStatus : dice) {
            if (diceStatus == idx) return false;
        }
        return true;
    }

    public static void play(int round, int[] dice, int acc) {
        if (round == 10) {
            max = Math.max(max, acc);
            return;
        }

        int curDice = dicePlay[round];

        loop:
        for (int i = 0; i < 4; i++) {
            // 도착지에 도착한 경우 pass
            if (dice[i] == -1) continue;
            GameNode curNode;

            // 이미 출전한 말일 경우 dice에 저장된 현위치 불러온다.
            if (dice[i] != 0) curNode = map.get(dice[i]);
                // 아닐 경우 출발점 위치 가져온다.
            else curNode = map.get(0);

            // blue node는 시작할 때 한번만 신경써주면 된다. 이동할때는 무조건 red만 가져오게 하면 된다.
            int movedIdx = curNode.blue == 0 ? curNode.red : curNode.blue;

            for (int move = 1; move < curDice; move++) {
                // 도착지에 도착했을 경우
                if (movedIdx == -1) {
                    break;
                }
                GameNode nextNode = map.get(movedIdx);
                movedIdx = nextNode.red;
            }

            // 도착지일 경우
            if (movedIdx == -1) {
                int temp = dice[i];
                dice[i] = -1;
                play(round + 1, dice, acc);
                dice[i] = temp;
                continue loop;
            }

            // 아무도 그 위치에 없을 경우
            if (check(dice, movedIdx)) {
                int temp = dice[i];
                dice[i] = movedIdx;
                play(round + 1, dice, acc + map.get(movedIdx).point);
                dice[i] = temp;
            }
        }
    }

    public static void makeBoard() {
        map = new HashMap<>();
        map.put(0, new GameNode(1, 0));
        for (int i = 1; i < 5; i++) {
            map.put(i, new GameNode(i + 1, i * 2));
        }
        map.put(5, new GameNode(6, 21, 10));

        for (int i = 6; i < 10; i++) {
            map.put(i, new GameNode(i + 1, i * 2));
        }
        map.put(10, new GameNode(11, 27, 20));

        for (int i = 11; i < 15; i++) {
            map.put(i, new GameNode(i + 1, i * 2));
        }
        map.put(15, new GameNode(16, 29, 30));

        for (int i = 16; i < 20; i++) {
            map.put(i, new GameNode(i + 1, i * 2));
        }
        map.put(20, new GameNode(-1, 40));

        map.put(21, new GameNode(22, 13));
        map.put(22, new GameNode(23, 16));
        map.put(23, new GameNode(24, 19));
        map.put(24, new GameNode(25, 25));

        map.put(25, new GameNode(26, 30));
        map.put(26, new GameNode(20, 35));
        map.put(27, new GameNode(28, 22));
        map.put(28, new GameNode(24, 24));
        map.put(29, new GameNode(30, 28));
        map.put(30, new GameNode(31, 27));
        map.put(31, new GameNode(24, 26));

    }
}

class GameNode {
    int red;
    int blue;
    int point;

    public GameNode(int red, int blue, int point) {
        this.red = red;
        this.blue = blue;
        this.point = point;
    }

    public GameNode(int red, int point) {
        this.red = red;
        this.point = point;
        this.blue = 0;
    }
}