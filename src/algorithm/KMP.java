package algorithm;

public class KMP {
    public static void main(String[] args) {
        String pattern ="abacaaba";
        String parent = "ababacabacaabacaaba";
        KMP(parent, pattern);

    }

    public static int[] makeTable(String pattern){
        int[] next = new int[pattern.length()];

        int j = 0;
        for(int i=1; i<pattern.length(); i++){
            while (j>0 && !pattern.substring(i, i+1).equals(pattern.substring(j,j+1))){
                j = next[j-1];
            }
            if(pattern.substring(i,i+1).equals(pattern.substring(j,j+1))){
                next[i] = j+1;
                j+=1;
            }
        }
        return next;
    }

    public static void KMP(String parent, String pattern){
        int [] next = makeTable(pattern);
        int j=0;

        for(int i=0;i<parent.length(); i++){
            while (j>0 && !parent.substring(i,i+1).equals(pattern.substring(j,j+1))){
                j = next[j-1];
            }
            if(parent.substring(i,i+1).equals(pattern.substring(j,j+1))){
                // 패턴의마지막까지 일치한 경우
                if(j==pattern.length()-1) {
                    System.out.printf("%d번째에서 찾았습니다\n", i-pattern.length()+2);
                    j = next[j];
                    // parent에 또 pattern이 있을수 있기 때문에 점프를 시켜준다
                }
                else {
                    // 아직 끝까지 일치하지 않은 경우에는 일단 j를 증가시켜 계속 비교
                    j++;
                }
            }
        }
    }
}
