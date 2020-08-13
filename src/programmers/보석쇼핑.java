package programmers;
import java.util.*;
public class 보석쇼핑 {

    public static void main(String[] args) {
//        solution(new String[]{"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"});
//        solution(new String[]{"ZZZ", "YYY", "NNNN", "YYY", "BBB"});
//        solution(new String[]{"a", "b", "a", "a", "c", "d"});
        temp(new String[]{"ZZZ", "YYY", "NNNN", "YYY", "BBB"});
    }

    public static int[] temp(String[] gems) {
        ArrayList<String>list = new ArrayList<>();
        HashSet<String> hs = new HashSet<>(Arrays.asList(gems));
        int size = hs.size();
        int start = 0;
        int end =0;
        int min = 10000000;
        int[] ans = new int[2];

        list.add(gems[0]);
        hs.remove(gems[0]);
        if(hs.isEmpty()) return new int[]{start+1, end+1};

        for(start =0; start<gems.length; start++){
            while(start<=end && end+1< gems.length && !hs.isEmpty()){
                if(list.stream().filter(gem->gem.equals(list.get(0))).count() >  1) break;
                end++;
                if(hs.contains(gems[end])) hs.remove(gems[end]);
                list.add(gems[end]);
            }
            if(hs.isEmpty() && min > list.size()){
                min = list.size();
                ans = new int[]{start+1, end+1};
            }
            String first = list.get(0);
            list.remove(0);
            if(list.stream().filter(gem->gem.equals(first)).count()==0) hs.add(first);
        }


        System.out.println(Arrays.toString(ans));
        return ans;
    }

    public static int[] solution_B(String[] gems) {
        int n = gems.length;
        HashSet<String> set = new HashSet<String>(Arrays.asList(gems));
        int end = -1;
        ArrayList<String> list = new ArrayList<>();
        int start = 0;

        for(start=0; start<n; start++ ){
            // System.out.println("start: " + start);
            while(end<n && !set.isEmpty()){
                end++;
                // System.out.println("end: " + end);
                // System.out.println(list.toString());
                // System.out.println("gems: " + gems[start]+" "+gems[end]);
                if(set.contains(gems[end])){
                    set.remove(gems[end]);
                }
                list.add(gems[end]);

                // System.out.println(list.toString());
                // System.out.println(list.stream().filter(gem->gem.equals(list.get(0))).count());
                if(list.stream().filter(gem->gem.equals(list.get(0))).count() >  1) break;
            }
            if(set.isEmpty()) break;
            list.remove(0);
        }

        // System.out.println((start+1)+" " +end);

        return new int[]{start+1, end+1};
    }

    public static int[] solution(String[] gems) {
        ArrayList<String>list = new ArrayList<>();
        HashSet<String> hs = new HashSet<>(Arrays.asList(gems));
        HashMap<String, Integer> hm = new HashMap<>();
        int size = hs.size();
        int start = 0;
        int end =0;
        int min = 10000000;
        int[] ans = new int[2];

        list.add(gems[0]);
        hm.put(gems[0], 1);
        if(size == hm.size()) return new int[]{start+1, end+1};

        for(start =0; start<gems.length; start++){
            while(start<=end && end+1< gems.length && size > hm.size()){
                if(hm.get(list.get(0)) > 1) break;
                end++;
                hm.put(gems[end], hm.getOrDefault(gems[end],0)+1);
                list.add(gems[end]);
            }
            if(hs.size() == hm.size() && min > list.size()){
                min = list.size();
                ans = new int[]{start+1, end+1};
            }
            int res = hm.get(list.get(0))-1;
            if(res ==0 ) hm.remove(list.get(0));
            else hm.put(list.get(0), res);
            list.remove(0);
        }


        return ans;
    }
}

// 대부분의 풀이
class Solution {
    public int[] solution(String[] gems) {
        ArrayList<String>list = new ArrayList<>();
        HashSet<String> hs = new HashSet<>(Arrays.asList(gems));
        HashMap<String, Integer> hm = new HashMap<>();
        int start = 0;
        int end = Integer.MAX_VALUE;


        int startPoint = 0;
        int i;
        for( i = 0; i < gems.length; i++) {
            hm.put(gems[i], hm.getOrDefault(gems[i], 0) + 1);

            list.add(gems[i]);

            while(true) {
                String temp = list.get(0);                //구간 내 보석이 1이상 있으면 start++
                if(hm.get(temp) > 1) {
                    list.remove(0);
                    start++;
                    hm.put(temp, hm.get(temp) -1);
                }else {
                    break;
                }
            }

            if(hm.size() == hs.size() && end > list.size()) {
                end = list.size();
                startPoint = start;
            }


        }
        System.out.println(startPoint + " " + (i));
        return new int[] {startPoint+1, startPoint+ end };

    }
}