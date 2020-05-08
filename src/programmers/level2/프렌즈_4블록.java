package programmers.level2;


class 프렌즈_4블록 {
    private  static char[][] b;
    private static int[][] points = {{0,0}, {0,1}, {1,0}, {1,1}};

    public static void main(String[] args) {
        System.out.println("Hello world!");
        String[] b ={"CCBDE", "AAADE", "AAABF", "CCBBF"};
        String[] b2 = {"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"};
        System.out.println(solution(6,6,b2));
    }

    public static int solution(int m, int n, String[] board) {
      int answer = 0;
      b = new char[m][n];

      for(int i=0; i<m; i++){
          String row = board[i];
          for(int j=0; j<n; j++){
              b[i][j] = row.charAt(j);
          }
      }

      boolean MORE_BLOCK;

        for(int y=0; y<m; y++) {
            for (int x = 0; x < n ; x++) {
                System.out.printf("%c", b[y][x]);
            }
            System.out.println("");
        }

      do{
          MORE_BLOCK = false;
          for(int y=0; y<m-1; y++){
              for(int x=0; x<n-1; x++){
                  char value = b[y][x];
                  if(compareChar(b[y][x+1], value) && compareChar(b[y+1][x], value) && compareChar(b[y+1][x+1], value)){
                      int plusCount = checkAndGetFound(y,x);
                      if(plusCount > 0) MORE_BLOCK = true;
                      answer+= plusCount;
                  }
              }
          }


          if(MORE_BLOCK){
              for(int x=0; x<n; x++){
                  for(int y= m-2; y>=0; y--){
                      for(int k=y; k<m-1; k++){
                          if(b[k+1][x] == ' ' || isLower(b[k+1][x])){
                              b[k+1][x] = b[k][x];
                              b[k][x] = ' ';
                          }else break;
                      }
                  }
              }
          }


          if(MORE_BLOCK){
              System.out.println("------------------------");
              for(int y=0; y<m; y++) {
                  for (int x = 0; x < n ; x++) {
                      System.out.printf("%c", b[y][x]);
                  }
                  System.out.println("");
              }
          }



      }while (MORE_BLOCK);

      return  answer;
    }

    public static boolean compareChar(char a, char b){
        if(a==b) return true;
        if((""+a).toUpperCase().equals((""+b).toUpperCase())) return true;
        return false;
    }

    public static int checkAndGetFound(int y, int x){
        int answer = 0;

        for(int[] add: points){
            if(b[y+add[0]][x+add[1]] != ' ' && Character.isUpperCase(b[y+add[0]][x+add[1]])){
                answer++;
                b[y+add[0]][x+add[1]] = Character.toLowerCase(b[y+add[0]][x+add[1]]);
            }
        }

        return answer;
    }

    public static boolean isLower(char c){
        return Character.isLowerCase(c)? true: false;
    }
}

class Point{
    private int x;
    private int y;
    private char value;

    public Point(int x, int y, char value){
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getValue() {
        return value;
    }
}