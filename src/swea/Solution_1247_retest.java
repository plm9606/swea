package swea;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Solution_1247_retest {
    static Point company;
    static Point home;
    static List<Point> customers;
    static int min;
    public static void main(String[] args)throws FileNotFoundException {
        System.setIn(new FileInputStream("res/input1247.txt"));


        Scanner sc = new Scanner(System.in);

        int tc = sc.nextInt();
        for(int test_case = 1; test_case <= tc; test_case++){
            min=Integer.MAX_VALUE;
            int n = sc.nextInt();
            company = new Point(sc.nextInt(), sc.nextInt());
            home = new Point(sc.nextInt(), sc.nextInt());
            customers = new ArrayList<>();

            for(int i=0; i<n; i++){
                customers.add(new Point(sc.nextInt(), sc.nextInt()));
            }

            permutation(n,n, 0, new boolean[n], new int[n]);
            System.out.printf("#%d %d\n", test_case, min);

        }
    }

    public static void permutation(int n, int r, int picked, boolean[] visited, int[] output){
        if(picked == r){
            int distance = 0;
            for(int i=0; i<output.length-1; i++){
                distance+= getDistance(customers.get(output[i]), customers.get(output[i+1]));
            }
            distance+= getDistance(company, customers.get(output[0]));
            distance += getDistance(home, customers.get(output[r-1]));
            if(min> distance) min = distance;
            return;
        }

        for(int i=0; i<n; i++){
            if(!visited[i]){
                visited[i] = true;
                output[picked] = i;
                permutation(n,r, picked+1, visited, output);
                visited[i] = false;
            }
        }
    }

    public static int getDistance(Point from, Point to){
        return Math.abs(from.x-to.x) + Math.abs(from.y-to.y);
    }

}
class Point{
    int x,y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}