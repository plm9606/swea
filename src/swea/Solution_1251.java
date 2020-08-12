package swea;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;

public class Solution_1251 {
    static ArrayList<int[]> points;
    static PriorityQueue<Path> paths;
    static int[] parents;
    public static void main(String[] args)throws FileNotFoundException {
        System.setIn(new FileInputStream("res/input1251.txt"));


        Scanner sc = new Scanner(System.in);

        int tc = sc.nextInt();
        for(int test_case = 1; test_case <= tc; test_case++){
            double answer = 0;
            int N = sc.nextInt();
            parents = new int[N];
            points = new ArrayList<>();
            paths = new PriorityQueue<>();

            for(int i=0; i<N; i++){
                points.add(new int[]{sc.nextInt(), 0});
                parents[i] = i;
            }

            for(int i=0; i<N; i++){
                points.get(i)[1] = sc.nextInt();
            }

            double E = sc.nextDouble();

            combination(new boolean[N], 2, 0);
//
//            for(int i=0; i<N-1; i++){
//                for(int j=i+1;j<N; j++){
//                    paths.add(new Path(i,j,getDistance(i,j)));
//                }
//            }
//
            while (!paths.isEmpty()){
                if(Arrays.stream(parents).allMatch(p->p==parents[0])) break;
                Path p = paths.poll();
                if(find(p.v1) == find(p.v2)) continue;
                answer += Math.pow(p.length,2)*E;
                union(p.v1, p.v2);
            }
            System.out.printf("#%d %.0f\n", test_case, answer);
        }
    }

    public static void union(int a, int b){
        a = find(a);
        b = find(b);
        parents[b] = a;
    }

    public static int find(int a){
        if(a == parents[a]) return a;
        parents[a] = find(parents[a]);
        return parents[a];
    }

    public static void combination(boolean[] bit, int toChoice, int target){
        if(toChoice == 0){
            print(bit);
            return;
        }

        for(int i=target; i<bit.length; i++){
            bit[i] = true;
            combination( bit, toChoice-1, i+1);
            bit[i] = false;
        }
    }

    public static void print( boolean[] bit){
        Path path = new Path();
        for(int i=0; i< bit.length; i++){
            boolean b = bit[i];
            if(b){
                if(path.v1 == -1){
                    path.v1 = i;
                }else path.v2 = i;
            }
        }
        path.length = getDistance(path.v1, path.v2);
        paths.add(path);
    }

    public static double getDistance(int v1, int v2){
        int[] p1 = points.get(v1);
        int[] p2 = points.get(v2);

        return Math.sqrt(Math.pow(p1[0]-p2[0],2)+ Math.pow((p1[1]-p2[1]), 2));
    }
}

class Path implements Comparable<Path>{
    int v1, v2;
    double length;

    public Path() {
        this.v1 = -1;
        this.v2 = -1;
    }

    public Path(int v1, int v2, double length) {
        this.v1 = v1;
        this.v2 = v2;
        this.length = length;
    }

    @Override
    public int compareTo(Path path) {
        // 이렇게 리턴값을 설정하면 정렬이 잘못됨. 대소 비교를 해주거나 compare함수 사용
//        return (int)(this.length - path.length);
        return Double.compare(this.length, path.length);
    }
}
