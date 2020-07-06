package algorithm.course;

public class Knapsack {
    static int maxProfit, N, WEIGHT;
    static int[] W, P;
    public static void main(String[] args) {
        maxProfit = 0;
        N=4;
        WEIGHT=16;

        W = new int[]{-1,2,5,10,5};
        P = new int[]{-1,40,30,50,10};

        Item init = new Item(0,0,0);
        backtarcking(init);

        System.out.println(maxProfit);
    }

    public static void backtarcking(Item n){
        if(n.level == N){
            if(n.weight <= WEIGHT && n.profit > maxProfit){
                maxProfit= n.profit;
            }
            return;
        }

        if(promising(n)){
            Item left = new Item(n.weight+W[n.level+1], n.profit+P[n.level+1], n.level+1);
                backtarcking(left);

            Item right = new Item(n.weight, n.profit, n.level+1);
            backtarcking(right);
        }
    }


    public static boolean promising(Item n){
        if(n.weight>WEIGHT){
            return false;
        }

        int remainWeight = WEIGHT - n.weight;
        int profit = 0;
        for(int i=n.level+1; i<=N; i++){
            if(remainWeight<=0) break;
            if(remainWeight < W[i]){
                profit += remainWeight*(P[i]/W[i]);
                break;
            }else {
                profit += P[i];
                remainWeight -= W[i];
            }
        }

        if(profit+n.profit < maxProfit) return false;
        return true;
    }
}

class Item {
    int weight;
    int profit;
    int level;

    public Item(int weight, int profit, int level) {
        this.weight = weight;
        this.profit = profit;
        this.level = level;
    }
}