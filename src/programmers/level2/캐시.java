package programmers.level2;

import java.util.*;

public class 캐시 {
    public static void main(String[] args) {
        String[] c1 = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
        String[] c2 = {"Jeju", "Pangyo", "NewYork", "newyork"};
        String[] c3 = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
        String[] c4 = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"};

        System.out.println(solution(3, c1)); //50
        System.out.println(solution(2, c2)); // 16
        System.out.println(solution(0, c3)); // 25
        System.out.println(solution(5, c4)); // 52
        System.out.println(solution(2, c4)); // 60
    }
    public static int solution(int cacheSize, String[] cities) {
        int answer = 0;
        List<String> cache = new ArrayList<>(cacheSize);

        if(cacheSize == 0){
            return cities.length * 5;
        }

        for(String c: cities){
            String city = c.toUpperCase();
            if(cache.contains(city)){
                cache.remove(city);
                cache.add(city);
                answer += 1;
            }else {
                if(cache.size()==cacheSize)cache.remove(0);
                cache.add(city);
                answer+=5;
            }
        }
        return answer;
    }
}
