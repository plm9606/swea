package test;

class Socar1 {
    public int solution(String[] bakery_schedule, String current_time, int K) {
        String[] curTime = current_time.split(":");
        int cur = Integer.parseInt(curTime[0]) * 60 + Integer.parseInt(curTime[1]);
        int acc = 0;
        int end = 0;
        for (String t : bakery_schedule) {
            String[] schdule = t.split(" ");
            String[] times = schdule[0].split(":");
            int time = Integer.parseInt(times[0]) * 60 + Integer.parseInt(times[1]);
            if (time < cur) continue;
            if (acc >= K) break;
            int count = Integer.parseInt(schdule[1]);
            acc += count;
            end = time;
        }

        if (acc < K) return -1;
        else return end - cur;
    }
}