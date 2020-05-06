class BinomialCoefficient {
  public static void main(String[] args) {
    System.out.println("Hello world!");
    solution(6,3);
  }

  public static void solution(int n, int r){
      int[][] arr = new int[n+1][r+1];

      for(int i=0; i<=n; i++){
        for(int j=0; j<=Math.min(i, r); j++){
          // System.out.printf("%d, %d\n", i,j);
          if(i==j || j==0){
              arr[i][j] = 1;
          }else{
            arr[i][j] = arr[i-1][j] + arr[i-1][j-1];
          }
        }
      }

      System.out.printf("%d\n", arr[n][r]);
  }
}
