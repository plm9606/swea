package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class 버스검색 {
    public static String timeToString(int t) {
        if (t < 10) {
            return ("0" + t);
        } else return (t + "");
    }

    public static String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        int[] times = new int[timetable.length];
        int friends = timetable.length;
        for (int i = 0; i < timetable.length; i++) {
            String[] time = timetable[i].split(":");
            times[i] = (Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]));
        }

        Arrays.sort(times);
        int time = 540;
        int idx = -1;
        int j;
        int passengers = 0;

        for (int i = 0; i < n; i++) {
            time = 540 + (t * i);
            passengers = m;

            while (passengers > 0 && idx < friends - 1) {
                if (times[idx + 1] <= time) {
                    idx++;
                    passengers--;
                } else break;
            }
        }

        if (passengers > 0) {
            answer = (timeToString(time / 60) + ":" + timeToString(time % 60));
        } else {

            idx = idx == -1 ? 0 : idx;
            int ans = times[idx] - 1;
            answer = (timeToString(ans / 60) + ":" + timeToString(ans % 60));
        }

        return answer;
    }
}

class 버스탐색2 {
    public static String timeToString(int t) {
        if (t < 10) {
            return ("0" + t);
        } else return (t + "");
    }

    public static String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < timetable.length; i++) {
            String[] time = timetable[i].split(":");
            list.add((Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1])));
        }

        Collections.sort(list);
        int time = 540;
        int passengers = 0;
        int lastTime = 0;
        for (int i = 0; i < n; i++) {
            time = 540 + (t * i);
            passengers = 0;

            while (!list.isEmpty()) {
                if (list.get(0) <= time && passengers < m) {
                    passengers++;
                    lastTime = list.get(0);
                    list.remove(0);
                } else break;
            }
        }


        if (passengers < m) {
            answer = (timeToString(time / 60) + ":" + timeToString(time % 60));
        } else {
            int ans = lastTime - 1;
            answer = (timeToString(ans / 60) + ":" + timeToString(ans % 60));
        }

        return answer;
    }
}