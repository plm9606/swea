package programmers;

import java.util.PriorityQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class 파일명정렬 {
    public static String[] solution(String[] files) {
        String[] answer = new String[files.length];
        PriorityQueue<File> pq = new PriorityQueue<>();

        for (int i = 0; i < files.length; i++) {
            String file = files[i];
            File f = new File(i);
            Pattern pattern = Pattern.compile("[a-zA-Z-. ]+");
            Matcher matcher = pattern.matcher(file);
            if (matcher.find()) {
                String head = matcher.group();
                // System.out.println(head);
                f.head = head;
            }
            pattern = Pattern.compile("[0-9]+");
            matcher = pattern.matcher(file);
            if (matcher.find()) {
                String number = matcher.group();
                // System.out.println(number);
                int idx = file.indexOf(number);
                String tail = file.substring(idx + number.length());

                f.number = number;
                f.tail = tail;
            }
            pq.add(f);
        }

        int idx = 0;
        while (!pq.isEmpty()) {
            File f = pq.poll();
            answer[idx++] = files[f.idx];
        }
        return answer;
    }
}

class File implements Comparable<File> {
    String head, number, tail;
    int idx;

    public File(int i) {
        this.idx = i;
    }

    @Override
    public int compareTo(File o) {
        int headDiff = this.head.toUpperCase().compareTo(o.head.toUpperCase());
        if (headDiff != 0) return headDiff;
        int numDiff = Integer.compare(Integer.parseInt(this.number), Integer.parseInt(o.number));
        if (numDiff != 0) return numDiff;
        return Integer.compare(this.idx, o.idx);
    }

}