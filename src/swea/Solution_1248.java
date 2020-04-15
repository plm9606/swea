package swea;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Solution_1248 {
    static boolean[] visited;
    static Stack<Integer> stack = new Stack<>();
    static int v;

    static Node[] nodes;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("res/input1248.txt"));
        Scanner sc = new Scanner(System.in);
        sc.nextInt();

        for (int test_case = 1; test_case <= 10; test_case++) {
            int answer = 1;
            v = sc.nextInt();
            int e = sc.nextInt();
            int node1 = sc.nextInt();
            int node2 = sc.nextInt();

            nodes = new Node[v + 1];
            visited = new boolean[v + 1];

            for (int i = 1; i <= v; i++) {
                nodes[i] = new Node(i);
            }


            for (int i = 0; i < e; i++) {
                int parent = sc.nextInt();
                int child = sc.nextInt();
                if (nodes[parent].left == 0) {
                    nodes[parent].left = child;
                } else nodes[parent].right = child;

                nodes[child].parent = parent;
            }

            int node1Parent = node1;
            int node2Parent = node2;
            while (true) {
                if (node1Parent >= 0) {
                    if (visited[node1Parent]) {
                        answer = node1Parent;
                        break;
                    } else {
                        visited[node1Parent] = true;
                        if (node1Parent == 1) node1Parent = -1;
                        else node1Parent = nodes[node1Parent].parent;
                    }
                }


                if (node2Parent >= 0) {
                    if (visited[node2Parent]) {
                        answer = node2Parent;
                        break;
                    } else {
                        visited[node2Parent] = true;
                        if (node2Parent == 1) node2Parent = -1;
                        else node2Parent = nodes[node2Parent].parent;
                    }
                }
            }

            stack.push(answer);
            int count = dfs();
            System.out.printf("#%d %d %d\n", test_case, answer, count);
        }
    }

    public static int dfs() {
        int count = 0;
        while (stack.size() > 0) {
            int cur = stack.pop();
            if (nodes[cur].left > 0) stack.push(nodes[cur].left);
            if (nodes[cur].right > 0) stack.push(nodes[cur].right);
            count += 1;
        }
        return count;
    }
}

class Node {
    int data;
    int left;
    int right;
    int parent;

    Node(int data) {
        this.data = data;
        this.left = 0;
        this.right = 0;
        this.parent = 0;
    }
}
