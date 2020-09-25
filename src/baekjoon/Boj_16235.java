package baekjoon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Boj_16235 {
    static int[][] arr, fertilizer;
    static int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1}, dx = {-1, 0, 1, -1, 1, -1, 0, 1};
    static LinkedList<Tree> trees;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("res/boj16235.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        fertilizer = new int[n][n];
        arr = new int[n][n];
        trees = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                fertilizer[i][j] = Integer.parseInt(st.nextToken());
                arr[i][j] = 5;
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken());
            trees.add(new Tree(y, x, z));
        }

//        Collections.sort(trees);
// 맨처음 나무를 주어지는 경우에서 동일한 위치는 업다고 했으니까 sort할 필요가 없음

        while (k > 0) {
            springAndSummer();
            fall();
            if (k == 1) {
                System.out.println(trees.size());
                System.exit(0);
            }
            winter();
            k--;
        }

        System.out.println(trees.size());

    }

    public static void springAndSummer() {
        ArrayList<Tree> deads = new ArrayList<>();
        for (int i = 0; i < trees.size(); i++) {
            Tree t = trees.get(i);
            if (arr[t.y][t.x] >= t.age) {
                arr[t.y][t.x] -= t.age;
                t.age += 1;

            } else {
                deads.add(t);
                t.alive = false;
            }
        }

//        for (Tree dead : deads) {
//            arr[dead.y][dead.x] += (dead.age / 2);
//        }

        for (Iterator<Tree> itt = trees.iterator(); itt.hasNext(); ) {
            Tree t = itt.next();
            if (!t.alive) {
                arr[t.y][t.x] += (t.age / 2);
                itt.remove();
            }
        }
    }

    public static void fall() {
//        for (int i = 0; i < trees.size(); i++) {
//            Tree t = trees.get(i);
//            if (t.age % 5 != 0) continue;
//            for (int d = 0; d < 8; d++) {
//                int xx = t.x + dx[d];
//                int yy = t.y + dy[d];
//                if (yy >= 0 && yy < n && xx >= 0 && xx < n) {
//                    trees.addFirst(new Tree(yy, xx, 1));
//                    i++;
//                }
//            }
//        }

        LinkedList<Tree> newTreeList = new LinkedList<>();
        for (int i = 0; i < trees.size(); i++) {
            Tree t = trees.get(i);
            if (t.age % 5 != 0) continue;
            for (int d = 0; d < 8; d++) {
                int xx = t.x + dx[d];
                int yy = t.y + dy[d];
                if (yy >= 0 && yy < n && xx >= 0 && xx < n) {
                    newTreeList.add(new Tree(yy, xx, 1));
                }
            }
        }
        trees.addAll(0, newTreeList);
    }

    public static void winter() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] += fertilizer[i][j];
            }
        }
    }
}

class Tree {
    int y, x, age;
    boolean alive;

    public Tree(int y, int x, int age) {
        this.y = y;
        this.x = x;
        this.age = age;
        this.alive = true;
    }
}
