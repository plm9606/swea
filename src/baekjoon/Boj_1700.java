package baekjoon;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Boj_1700 {
    public static void main(String[] args) throws FileNotFoundException {

        System.setIn(new FileInputStream("res/boj_1700.txt"));
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();
        int[] order = new int[K];
        int replace = 0;
        ArrayList<Integer> plug = new ArrayList<>();
        HashMap<Integer, List<Integer>> locality = new HashMap<>();

        for(int i=0; i<K; i++){
            order[i] = sc.nextInt();

            if(!locality.containsKey(order[i])) locality.put(order[i], new ArrayList<>());
            locality.get(order[i]).add(i);
        }

        for(int i=0; i<K; i++){
            int cur = order[i];
            // 이미 플러그에 전기용품이 꽂혀있는 경우
            if(plug.contains(cur)){
                locality.get(cur).remove(0);
                continue;
            }
            // 플러그에 빈 구멍이 있는 경우
            if(plug.size()<N){
                plug.add(cur);
                locality.get(cur).remove(0);
                continue;
            }

            int maxLocality = Integer.MIN_VALUE;
            int idx = -1;
            for(int h=0; h<N; h++){
                if(locality.get(plug.get(h)).size() == 0) {
                    idx = h;
                    break;
                }
                if(maxLocality < locality.get(plug.get(h)).get(0)){
                    maxLocality = locality.get(plug.get(h)).get(0);
                    idx = h;
                }
            }

            if(idx != -1){
                plug.remove(idx);
                plug.add(cur);
                locality.get(cur).remove(0);
                replace++;
                continue;
            }
        }

        System.out.println(replace);

    }
}