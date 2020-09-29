package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class t3 {
    public static void main(String[] args) {
//        int[] A = {2, 1, 1};

        int[] A = new int[2000000];

        for (int i = 0; i < 200000; i++) {
            A[i] = 200000;
        }
        long start = System.currentTimeMillis();
        int n = A.length;
        ArrayList<Integer> list = new ArrayList<>();

        HashSet<Integer> set = new HashSet<>();
        for (int i = 1; i <= n; i++) set.add(i);

        for (int i = 0; i < n; i++) {
            if (set.contains(A[i])) {
                set.remove(A[i]);
                continue;
            }
            list.add(A[i]);
        }

        Collections.sort(list);
        int move = 0, cnt = 0;
        for (int remain : set) {
            if (move > 1000000000) {
                move = -1;
                break;
            }
            move += Math.abs(remain - list.get(cnt++));
        }
        long end = System.currentTimeMillis();

        System.out.println("실행 시간 : " + (end - start) / 1000.0);

        System.out.println(move);

//        File file = new File("text11.txt");
//        String str = "Hello world!";
//
//        try {
//            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
//            writer.write("[");
//            for (int i = 0; i < 200000; i++) {
//                writer.write("200000, ");
//            }
//            writer.write("]");
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
