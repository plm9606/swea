package programmers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class 매칭점수 {
    public static void main(String[] args) {
//        solution("blind", new String[]{"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>"});
        solution("Muzi", new String[]{"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>"});
    }

    public static int solution(String word, String[] pages) {
        word = word.toUpperCase();
        int answer = 0;
        Map<String, Integer> urlMap = new HashMap<>();
        Info[] pageInfos = new Info[pages.length];

        for (int i = 0; i < pages.length; i++) {
            StringBuilder sb = new StringBuilder(pages[i]);
            int idx = 0;
            String urlRegex = "<meta property=[\"a-z:A-Z0-9]* content=[\"a-z:A-Z0-9\\/.]*>";
            Pattern pattern = Pattern.compile(urlRegex);
            Matcher match = pattern.matcher(pages[i]);
            Info info = new Info(i);

            if (match.find()) {
                String url = match.group();
                // url = url.substring(9,url.length()-1);
                url = url.substring(url.indexOf("content=") + 9, url.length() - 3);
                System.out.println(url);
                urlMap.put(url, i);
                info.url = url;
            }

            String linkRegex = "<a href=\"https:\\/\\/[a-zA-Z0-9.\\/]*\"";
            pattern = Pattern.compile(linkRegex);
            match = pattern.matcher(pages[i]);
            int linkCnt = 0;
            while (match.find()) {
                String link = match.group();
                link = link.substring(9, link.length() - 1);
                // System.out.println(link); // 2
                info.linkList.add(link);
                linkCnt++;
            }
            info.linkCount = linkCnt;

            String body = sb.substring(sb.indexOf("<body>") + 6, sb.indexOf("</body>"));
            String[] splited = body.split("[^a-zA-Z]");
            int matchCnt = 0;
            for (String split : splited) {
                if (split.trim().toUpperCase().equals(word)) {
                    matchCnt++;
                }
            }
            info.basic = matchCnt;
            pageInfos[i] = info;
        }

        for (int i = 0; i < pageInfos.length; i++) {
            Info cur = pageInfos[i];
            if (cur.linkCount == 0) continue;
            for (String link : cur.linkList) {
                if (urlMap.get(link) != null) {
                    int idx = urlMap.get(link);
                    if (cur.linkCount != 0) pageInfos[idx].linkScore += cur.basic / 1.0 / cur.linkCount;
                }

            }
        }

        PriorityQueue<Info> pq = new PriorityQueue<>();
        for (Info info : pageInfos) {
            info.matchScore = info.basic + info.linkScore;
            pq.add(info);
        }

        return pq.poll().idx;
    }
}

class Info implements Comparable<Info> {
    int basic, linkCount, idx;
    double linkScore, matchScore;
    String url;
    ArrayList<String> linkList;

    public Info(int idx) {
        this.idx = idx;
        this.linkList = new ArrayList();
        this.linkScore = 0;
        this.matchScore = 0;
        this.basic = 0;
        this.linkCount = 0;
    }

    @Override
    public int compareTo(Info o) {
        if (this.matchScore > o.matchScore) return -1;
        else if (this.matchScore < o.matchScore) return 1;
        else {
            return Integer.compare(this.idx, o.idx);
        }
    }
}