package programmers.level3;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;

public class 추석_트래픽 {
    public static void main(String[] args) {
        String[] arr = {"2016-09-15 00:00:00.000 2.3s", "2016-09-15 23:59:59.999 0.1s"};
//        String[] arr = {"2016-09-15 20:59:57.421 0.351s", "2016-09-15 20:59:58.233 1.181s", "2016-09-15 20:59:58.299 0.8s", "2016-09-15 20:59:58.688 1.041s", "2016-09-15 20:59:59.591 1.412s", "2016-09-15 21:00:00.464 1.466s", "2016-09-15 21:00:00.741 1.581s", "2016-09-15 21:00:00.748 2.31s", "2016-09-15 21:00:00.966 0.381s", "2016-09-15 21:00:02.066 2.62s"};
//        solution(arr);
        solution_B(arr);
    }

    public static int solution_B(String[] arr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        ArrayList<Time> list = new ArrayList<>();
        int max = 0;

        for (String date : arr) {
            String[] res = date.split(" ");
            System.out.println(Arrays.toString(res));
            LocalDateTime end = LocalDateTime.parse(res[0] + " " + res[1], formatter);
            long T = (long) (Float.parseFloat(res[2].substring(0, res[2].length() - 1)) * 1000);
            LocalDateTime start = end.minus(T, ChronoUnit.MILLIS);
            start = start.plus(1, ChronoUnit.MILLIS);
            list.add(new Time(start, end));
        }

        for (int i = 0; i < list.size(); i++) {
            int cnt = 0;
            int endCount = 0;
            Time time = list.get(i);
            for (int j = 0; j < list.size(); j++) {
                if (time.start.compareTo(list.get(j).end) <= 0 && time.start.plus(999, ChronoUnit.MILLIS).compareTo(list.get(j).start) >= 0) {
                    cnt++;
                }
                if (time.end.compareTo(list.get(j).end) <= 0 && time.end.plus(999, ChronoUnit.MILLIS).compareTo(list.get(j).start) >= 0) {
                    endCount++;
                }
            }
            if (max < cnt) {
                max = cnt;
            }
            if (max < endCount) {
                max = endCount;
            }
        }
        System.out.println(max);
        return max;
    }

    public static int solution(String[] lines) {
        int answer = 0;
        long[][] logs = new long[lines.length][2];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
        for (int i = 0; i < lines.length; i++) {
            String[] res = lines[i].split(" ");
            System.out.println(Arrays.toString(res));
            LocalTime end = LocalTime.parse(res[1], formatter);
            logs[i][1] = end.getLong(ChronoField.MILLI_OF_DAY);
            long T = (long) (Float.parseFloat(res[2].substring(0, res[2].length() - 1)) * 1000);
            logs[i][0] = logs[i][1] - T + 1;
        }

        for (int i = 0; i < logs.length; i++) {
            int startCnt = 0, endCnt = 0;
            long start = logs[i][0];
            long end = logs[i][1];
            for (int j = 0; j < logs.length; j++) {
                if (start <= logs[j][1] && start + 999 >= logs[j][0]) {
                    startCnt++;
                }
                if (end <= logs[j][1] && end + 999 >= logs[j][0]) {
                    endCnt++;
                }
            }

            if (answer < startCnt) answer = startCnt;
            if (answer < endCnt) answer = endCnt;
        }
        return answer;
    }
}

class Time {
    LocalDateTime start, end;

    public Time(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }
}