package algorithm;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Huffman {
    public static void main(String[] args) {
        huffman();
    }
    public static void huffman(){
        PriorityQueue<HNode> pq = new PriorityQueue<>();
        pq.add(new HNode("b",5));
        pq.add(new HNode("e",10));
        pq.add(new HNode("c",12));
        pq.add(new HNode("a",16));
        pq.add(new HNode("d",17));
        pq.add(new HNode("f",25));

        while (pq.size() >1){
            HNode first = pq.poll();
            HNode second =  pq.poll();

            HNode mergedNode = merge(first, second);

            pq.add(mergedNode);
        }

        print(pq.poll(),"");


    }

    public static void print(HNode tree, String bit){
        if(tree.right == null && tree.left == null){
            System.out.println(tree.character + ":" + bit);
            return;
        }
//        HNode left = tree.left;
        print(tree.left, bit+"0");
        print(tree.right, bit+"1");
//        HNode right = tree.right;
    }
    public static HNode merge(HNode n1, HNode n2){
        HNode parent = new HNode(n1.frequency + n2.frequency);

        parent.left = n1;
        parent.right = n2;

        n1.parent = parent;
        n2.parent = parent;

        return parent;
    }
}

class HNode implements Comparable<HNode>{
    int frequency;
    String character;
    HNode left, right, parent;

    public HNode() {
    }

    public HNode(String character, int frequency) {
        this.frequency = frequency;
        this.character = character;
    }

    public HNode(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public int compareTo(HNode hNode) {
        if(this.frequency < hNode.frequency ) return -1;
        if(this.frequency == hNode.frequency) return 0;
        return 1;
    }
}
