package programmers.level2;


import java.util.ArrayList;

public class 더_맵게 {
    public static void main(String[] args) {
        //섞은 음식의 스코빌 지수 = 가장 맵지 않은 음식의 스코빌 지수 + (두 번째로 맵지 않은 음식의 스코빌 지수 * 2)
        int[] s= {1, 2, 3, 9, 10, 12};
        System.out.println(solution(s, 200));
    }

    public static int solution(int[] scoville, int k) {
        int answer = 0;
        Heap heap = new Heap();
        for(int i: scoville){
            heap.addNode(i);
        }

        while (heap.getNodesCount() > 1 && heap.getMin() < k){
            int first = heap.pop();
            int second = heap.pop();

            heap.addNode(first +(second*2));
            answer++;
        }

        if(heap.getMin() < k) answer = -1;
        return answer;
    }
}



class Heap{
    private ArrayList<Integer> nodes;

    public Heap(){
        nodes = new ArrayList<>();
        nodes.add(-1);
    }

    public void addNode(int i){
        nodes.add(i);
        int curIndex = nodes.size()-1;
        while (curIndex>1){
            if(nodes.get((curIndex/2)) > nodes.get(curIndex)){
                //swap
                swap(curIndex, (curIndex/2));
                // change index
                curIndex = curIndex/2;
            }else {
                break;
            }
        }

        System.out.println("add node " + i + ", " + nodes.toString());
    }

    private void swap(int i, int j){
        int temp = nodes.get(i);
        nodes.set(i, nodes.get(j));
        nodes.set(j, temp);
    }

    public Integer getMin(){
        if(nodes.size() == 1) return null;
        else return nodes.get(1);
    }

    public Integer pop (){
        if(nodes.size() == 1) return null;

        int min = nodes.get(1);
        nodes.set(1, nodes.get(nodes.size()-1));
        nodes.remove(nodes.size()-1);

        int curIndex = 1;
        while (curIndex*2 < nodes.size()){
            int left = nodes.get(curIndex*2);

            if(curIndex*2+1 < nodes.size()){
                int right = nodes.get(curIndex*2+1);

                if(nodes.get(curIndex) > right && right < left){
                    swap(curIndex, curIndex*2+1);
                    curIndex = curIndex*2+1;
                    continue;

                }
            }

            if(nodes.get(curIndex) > left ){
                swap(curIndex, curIndex*2);
                curIndex = curIndex*2;
            }else break;
        }
        System.out.println("pop node " + min + ", " + nodes.toString());
        return min;
    }

    public boolean isEmpty(){
        return nodes.size() > 1? false:true;
    }

    public int getNodesCount(){
        return nodes.size()-1;
    }
}