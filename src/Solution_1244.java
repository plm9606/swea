import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution_1244 {
    static int result = 0;
    static int count = 0;
    static int[] arr;
    static boolean[][] visited;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("res/input1244.txt"));


        Scanner sc = new Scanner(System.in);
        sc.nextInt();

        for (int test_case = 1; test_case <= 10; test_case++) {
            char[] numbers = sc.next().toCharArray();
            count = sc.nextInt();
            arr = new int[numbers.length];
            visited = new boolean[1000010][count + 1];
            result = 0;
            for (int i = 0; i < arr.length; i++) {
                arr[i] = Integer.parseInt(numbers[i] + "");
            }

            dfs(0, 0);
            System.out.printf("#%d %d\n", test_case, result);

        }
    }

    public static int parseToInt() {
        int ret = 0;

        for (int i = 0; i < arr.length; i++) {
            ret *= 10;
            ret += arr[i];
        }
        return ret;
    }

    public static void dfs(int k, int cnt) {
        int temp;
        if (visited[parseToInt()][cnt]) return;
        visited[parseToInt()][cnt] = true;

        if (cnt == count) {
            int tempResult = parseToInt();
            result = Math.max(result, tempResult);
            return;
        }

        for (int i = k; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;      //swap
                dfs(i, cnt + 1);
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
    }
}
