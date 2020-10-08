package test;

public class Kakao1 {
    public static void main(String[] args) {
        String new_id = "z-+.^.";
        String answer = "";
        new_id = new_id.toLowerCase();
        new_id = new_id.replaceAll("[^a-z_.-]", "");

        for (int i = 0; i < new_id.length(); i++) {
            if (new_id.charAt(i) == '.') {
                int idx = i;
                while (idx < new_id.length() && new_id.charAt(idx) == '.') {
                    idx++;
                }
                answer += ".";
                i = idx >= new_id.length() ? idx : idx - 1;
            } else answer += new_id.charAt(i);
        }

        new_id.split(":");

    }
}
