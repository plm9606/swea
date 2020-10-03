package baekjoon;

import java.io.*;
import java.util.*;

public class Boj_1202 {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("res/boj_1202.txt"));
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        PriorityQueue<Item> pq = new PriorityQueue<>();
        PriorityQueue<Item> wightPQ = new PriorityQueue<>(new WeightComparator());

        int[] bags = new int[K];
        long sum = 0;

        for (int i = 0; i < N; i++) {
            pq.add(new Item(sc.nextInt(), sc.nextInt()));
        }

        for (int i = 0; i < K; i++) {
            bags[i] = sc.nextInt();
        }

        Arrays.sort(bags);

        System.out.println(Arrays.toString(bags));
        System.out.println(wightPQ.toString());

        for (int i = 0; i < K; i++) {
            int bagWeight = bags[i];
            System.out.println("baWeight: " + bagWeight);
            while (!wightPQ.isEmpty() && wightPQ.peek().weight <= bagWeight) {
                pq.add(wightPQ.poll());
            }
            System.out.println("PQ: " + pq.toString());
            if (!pq.isEmpty()) {
                sum += pq.poll().value;
            }
        }

        System.out.println(sum);
    }
}

class WeightComparator implements Comparator<Item> {
    @Override
    public int compare(Item t1, Item t2) {
        return t1.weight - t2.weight;
    }
}

class Item implements Comparable<Item> {
    int weight, value;

    public Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }

    @Override
    public int compareTo(Item o) {
        return -(this.value - o.value);
    }

    @Override
    public String toString() {
        return "(w: " + this.weight + ", v: " + this.value + ")";
    }
}

class Q1202 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Jewelry[] a = new Jewelry[n];
        int[] bag = new int[m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(reader.readLine());
            a[i] = new Jewelry(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        for (int i = 0; i < m; i++) {
            bag[i] = Integer.parseInt(reader.readLine());
        }

        Arrays.sort(a);
        Arrays.sort(bag);

        Queue<Integer> q = new PriorityQueue<>();
        long ans = 0;
        int j = 0;
        for (int i = 0; i < m; i++) {
            while (j < n && a[j].c <= bag[i]) {
                q.add(-a[j].v);
                j++;
            }
            if (!q.isEmpty()) {
                ans += Math.abs(q.poll());
            }
        }
        System.out.println(ans);
    }
}

class Jewelry implements Comparable<Jewelry> {
    int c;
    int v;

    public Jewelry(int c, int v) {
        this.c = c;
        this.v = v;
    }

    @Override
    public int compareTo(Jewelry o) {
        return this.c - o.c;
    }
}


