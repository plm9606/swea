package test;

import java.util.*;

class Solution {
    public static void main(String[] args) {
        solution2("02:03:55", "00:14:15", new String[]{"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"});
        solution("99:59:59", "25:00:00", new String[]{"69:59:59-89:59:59", "01:00:00-21:00:00", "79:59:59-99:59:59", "11:00:00-31:00:00"});
        solution("50:00:00", "50:00:00", new String[]{"15:36:51-38:21:49", "10:14:18-15:36:51", "38:21:49-42:51:45"});
    }

    static public String solution2(String play_time, String adv_time, String[] logs) {
        int answer = 0;
        int answerTime = 0;
        int play = timeToSec(play_time);
        int adv = timeToSec(adv_time);
        Time[] logTimes = new Time[logs.length + 1];

        for (int i = 0; i < logs.length; i++) {
            int[] times = logToSec(logs[i]);
            logTimes[i] = new Time(times[0], times[1], i);
        }

        logTimes[logs.length] = new Time(0, play, -1);
        Arrays.sort(logTimes, new Comparator<Time>() {
            @Override
            public int compare(Time o1, Time o2) {
                if (o1.start != o2.start) {
                    return Integer.compare(o1.start, o2.start);
                } else return Integer.compare(o1.end, o2.end);
            }
        });

        for (int i = 0; i < logTimes.length; i++) {
            int advStart = logTimes[i].start;
            int advEnd = advStart + adv > play ? play : advStart + adv;
            int acc = 0;
            int cnt = 0;

            PriorityQueue<Integer> pq = new PriorityQueue<>();
            ArrayList<Time> candidate = new ArrayList<>();

            int start = advStart;
            pq.add(advStart);
            pq.add(advEnd);
            int j = 1;
            loop:
            while (!pq.isEmpty()) {
//                int time = pq.peek();

                while (j < logTimes.length) {
                    int time = pq.peek();
                    Time t = logTimes[j];
                    if (t.start >= time) {
                        if (time != advStart) {
                            acc += (candidate.size() * (time - start));
                            candidate.removeIf(c -> c.end <= time);
                        }
                        if (t.end >= advStart && t.end <= advEnd) {
                            if (!pq.contains(t.end)) {
                                pq.add(t.end);
                                candidate.add(t);
                            }
                        }
                        j++;
                        break;
                    }
                    boolean flag = false;
                    if (t.start >= advStart && t.start <= advEnd) {
//                    checkTimes.add(t.start);
                        if (!pq.contains(t.start)) {
                            pq.add(t.start);
                        }
                        flag = true;
                    }
                    if (t.end >= advStart && t.end <= advEnd) {
//                    checkTimes.add(t.end);
                        if (!pq.contains(t.end)) {
                            pq.add(t.end);
                        }
                        flag = true;
                    }

                    if (flag) {
                        candidate.add(t);
                    }
                    j++;
                }
                start = pq.poll();
            }

            if (answer < acc) {
                answer = acc;
                answerTime = advStart;
            }
        }

        System.out.println(intToTime(answerTime));
        return intToTime(answerTime);
    }

    static public String solution(String play_time, String adv_time, String[] logs) {
        int answer = 0;
        int answerTime = 0;
        int play = timeToSec(play_time);
        int adv = timeToSec(adv_time);
        Time[] logTimes = new Time[logs.length + 1];

        for (int i = 0; i < logs.length; i++) {
            int[] times = logToSec(logs[i]);
            logTimes[i] = new Time(times[0], times[1], i);
        }

        logTimes[logs.length] = new Time(0, play, -1);
        Arrays.sort(logTimes, new Comparator<Time>() {
            @Override
            public int compare(Time o1, Time o2) {
                if (o1.start != o2.start) {
                    return Integer.compare(o1.start, o2.start);
                } else return Integer.compare(o1.end, o2.end);
            }
        });

        for (int i = 0; i < logTimes.length; i++) {
            int advStart = logTimes[i].start;
            int advEnd = advStart + adv > play ? play : advStart + adv;
            int acc = 0;
            int cnt = 0;

            ArrayList<Time> candidate = new ArrayList<>();
            HashSet<Integer> checkTimes = new HashSet<>();
            checkTimes.add(advStart);
            checkTimes.add(advEnd);
            for (int j = 1; j < logTimes.length; j++) {
                Time t = logTimes[j];
                boolean flag = false;
                if (t.start >= advStart && t.start <= advEnd) {
                    checkTimes.add(t.start);
                    flag = true;
                }
                if (t.end >= advStart && t.end <= advEnd) {
                    checkTimes.add(t.end);
                    flag = true;
                }
                if (flag) candidate.add(t);
            }

            ArrayList<Integer> check = new ArrayList<>(checkTimes);
            Collections.sort(check);
            for (int k = 0; k < check.size() - 1; k++) {
                cnt = 0;
                int start = check.get(k);
                int end = check.get(k + 1);
                for (Time c : candidate) {
                    if (c.isInTime(start, end)) {
                        cnt++;
                    }
                }
                acc += (cnt * (end - start));
            }

            if (answer < acc) {
                answer = acc;
                answerTime = advStart;
            }
        }

        System.out.println(intToTime(answerTime));
        return intToTime(answerTime);
    }

    static public String intToTime(int t) {
        String ans = "";
        int sec = t % 60;
        t = t / 60;
        int min = t % 60;
        int hour = t / 60;
        return convert(hour) + ":" + convert(min) + ":" + convert(sec);
    }

    static public String convert(int time) {
        if (time == 0) return "00";
        else if (time < 10) return "0" + time;
        else return time + "";
    }

    static public int timeToSec(String t) {
        String[] splited = t.split(":");
        return Integer.parseInt(splited[0]) * 3600 + Integer.parseInt(splited[1]) * 60 + Integer.parseInt(splited[2]);
    }

    static public int[] logToSec(String t) {
        int[] res = new int[2];
        String times[] = t.split("-");
        for (int i = 0; i < times.length; i++) {
            String[] splited = times[i].split(":");
            res[i] = Integer.parseInt(splited[0]) * 3600 + Integer.parseInt(splited[1]) * 60 + Integer.parseInt(splited[2]);
        }

        return res;
    }
}

class Time {
    int start, end, idx;

    public Time(int start, int end, int idx) {
        this.start = start;
        this.end = end;
        this.idx = idx;
    }

    public boolean isInTime(int s, int e) {
        if (this.start <= s && this.end >= e) return true;
        else return false;
    }
}
