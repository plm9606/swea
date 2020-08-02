package programmers;
import java.util.*;

public class 길_찾기_게임 {
        int[] post, pre;
        public int[][] solution(int[][] nodes) {
            int[][] nodeinfo = new int[nodes.length][3];
            post = new int[nodes.length];
            pre = new int[nodes.length];
            for(int i=0; i<nodes.length; i++){
                nodeinfo[i] = new int[]{nodes[i][0], nodes[i][1], i+1};
            }

            Arrays.sort(nodeinfo, (o1, o2) -> {
                if (o1[1] == o2[1]) {
                    return Integer.compare(o1[0], o2[0]);
                } else {
                    return -Integer.compare(o1[1], o2[1]);
                }
            });

            int[] root = nodeinfo[0];
            Tree tree = new Tree();
            tree.setNodeCount(nodes.length);
            tree.setRoot(new Node(root[1], root[0], root[2]));

            for(int i=1; i<nodeinfo.length; i++){
                int[] n = nodeinfo[i];
                tree.createNode(n[1],n[0], n[2]);
            }

            tree.preOrder(tree.root, 0);
            tree.postOrder(tree.root, 0);

            return new int[][]{tree.pre, tree.post};
        }
    }
    class Node{
        int y,x, idx;
        Node left,right;
        Node(int y, int x, int idx){
            this.y = y;
            this.x = x;
            this.idx = idx;
        }
    }

    class Tree{
        public Node root;
        public int[] post, pre;

        public void setNodeCount(int n){
            this.post = new int[n];
            this.pre = new int[n];
        }
        public void setRoot(Node root){
            this.root = root;
        }

        public void createNode(int y, int x, int idx){
            Node n = new Node(y,x, idx);
            this.searchNode(n, this.root);
        }

        public void searchNode(Node target, Node cur){
            if(cur.left == null && cur.x > target.x){
                cur.left = target;
                return;
            }else if(cur.right == null && cur.x <target.x){
                cur.right = target;
                return;
            }else if(cur.y > target.y){
                if(cur.left != null && cur.x > target.x)searchNode(target, cur.left);
                if(cur.right != null && cur.x < target.x)searchNode(target, cur.right);
            }
        }

        public int preOrder(Node n, int orderIdx){
            if(n == null) return orderIdx;
            // System.out.println(n.idx+ " | "+n.y + ", " + n.x);
            this.pre[orderIdx++] = n.idx;
            int l = preOrder(n.left, orderIdx);
            int r = preOrder(n.right, l);
            return r;
        }

        public int postOrder(Node n,int orderIdx){
            if(n == null) return orderIdx;
            // System.out.println(n.idx+ " | "+n.y + ", " + n.x);
            int l = postOrder(n.left, orderIdx);
            int r = postOrder(n.right, l);
            this.post[r++] = n.idx;
            return r;
        }

}
