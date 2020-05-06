class FloydWarshall {
  public static void main(String[] args) {
    int[][] g = {{0,5,Integer.MAX_VALUE,8},
    {7,0,9,Integer.MAX_VALUE},
    {2, Integer.MAX_VALUE, 0, 4},
    {Integer.MAX_VALUE, Integer.MAX_VALUE, 3, 0}};

    solution(g);
  }

  public static void solution(int[][] graph) {
        int nodeCount = graph.length;
        
        for(int n=0; n<nodeCount; n++){
            for(int start=0; start<nodeCount; start++){
                for(int end=0; end<nodeCount; end++){
                    System.out.printf("D[%d, %d], node %d \n", start, end, n);
                    if(graph[start][end] > graph[start][n]+graph[n][end] && (graph[start][n]+graph[n][end])>=0){
                        System.out.printf("graph[s][e]= %d, sums = %d\n", graph[start][end], graph[start][n]+graph[n][end]);
                        graph[start][end] =graph[start][n]+graph[n][end];
                    }
                }
            }
        }
        
      for(int i=0; i<nodeCount; i++){
        for(int j=0; j<nodeCount; j++){
          System.out.printf("%3d ",graph[i][j]);
        }
        System.out.println();
      }
    }
}
