package programmers;

import java.util.Arrays;
import java.util.HashMap;

public class 호텔방_배정 {
    static HashMap<Long, Long> check = new HashMap<>();

    public static void main(String[] args) {
        solution(10, new long[]{1, 3, 4, 1, 3, 1});
    }

    public static long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];
        // long[] check = new long[k+1];


        for (int i = 0; i < room_number.length; i++) {
            System.out.println(check.keySet().toString());

            long n = room_number[i];
            if (!check.containsKey(n)) {
                check.put(n, n + 1);
                answer[i] = n;
            } else {
                long next = check.get(n);
                if (!check.containsKey(next)) {
                    check.replace(n, next + 1);
                    check.put(next, next + 1);
                    answer[i] = next;
                } else {
                    long res = find(next);
                    check.replace(n, res);
                    answer[i] = res;
                }
            }
        }

        System.out.println(Arrays.toString(answer));
        return answer;
    }

    public static long find(long next) {
        if (!check.containsKey(next)) {
            check.put(next, next + 1);
            return next;
        }
        // int temp = check[next];
        check.replace(next, find(check.get(next)));
        // check[next] = find(check[next]);
        return check.get(next);
    }
}

class 호텔방_배정2 {
    HashMap<Long, Long> map = new HashMap<>();

    public long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];


        for (int i = 0; i < room_number.length; i++) {
            long n = room_number[i];
            answer[i] = find(n);
        }
        return answer;
    }

    public long find(long n) {
        if (!map.containsKey(n)) {
            map.put(n, n + 1);
            return n;
        }

        map.replace(n, find(map.get(n)));
        return map.get(n);
    }
}