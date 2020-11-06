package baekjoon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Boj_4256 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("res/boj4256.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < t; tc++) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st;
            int[] preorder = new int[n], inorder = new int[n];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                preorder[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                inorder[i] = Integer.parseInt(st.nextToken());
            }

            LinkedList<Integer> postorder = split(preorder, inorder);


            System.out.println(split2(preorder, inorder).trim());
        }
    }

    static String split2(int[] preorder, int[] inorder) {

        if (preorder.length == 0) return "";
        if (preorder.length == 1) {
            return preorder[0] + " ";
        }


        int root = preorder[0];
//        int inorderRootIdx = Arrays.binarySearch(inorder, root);

        int inorderRootIdx = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == root) {
                inorderRootIdx = i;
                break;
            }
        }
        // left
        String left = split2(Arrays.copyOfRange(preorder, 1, inorderRootIdx + 1), Arrays.copyOfRange(inorder, 0, inorderRootIdx));
        //right
        String right = split2(Arrays.copyOfRange(preorder, inorderRootIdx + 1, preorder.length), Arrays.copyOfRange(inorder, inorderRootIdx + 1, inorder.length));


        return left + right + root + " ";
    }


    static LinkedList<Integer> split(int[] preorder, int[] inorder) {

        if (preorder.length == 0) return new LinkedList<>();
        if (preorder.length == 1) {
//            if (preorder[0] == inorder[0]) {
//                return "" + inorder[0] + inorder[1];
//            }
//            return preorder[0]
            LinkedList<Integer> list = new LinkedList<>();
            list.add(preorder[0]);
            return list;
        }


        int root = preorder[0];
//        int inorderRootIdx = Arrays.binarySearch(inorder, root);

        int inorderRootIdx = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == root) {
                inorderRootIdx = i;
                break;
            }
        }
        // left
        LinkedList<Integer> left = split(Arrays.copyOfRange(preorder, 1, inorderRootIdx + 1), Arrays.copyOfRange(inorder, 0, inorderRootIdx));
        //right
        LinkedList<Integer> right = split(Arrays.copyOfRange(preorder, inorderRootIdx + 1, preorder.length), Arrays.copyOfRange(inorder, inorderRootIdx + 1, inorder.length));

        left.addAll(right);
        left.add(root);

        return left;
    }
}
