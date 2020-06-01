package swea;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Solution_1259 {
    static List<LinkedList<Screw>> subtrees = new ArrayList<>();

    public static void main(String[] args)throws FileNotFoundException {
        System.setIn(new FileInputStream("res/input1259.txt"));
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        for(int test_case = 1; test_case <= tc; test_case++){
            int answer = 0;
            int n = sc.nextInt();
            subtrees.clear();

            for(int i=0; i<n; i++){
                Screw screw = new Screw(sc.nextInt(), sc.nextInt());

                if(findLinkedSubtree(screw)){
                    // merge
                    merge(screw);
                }else {
                    LinkedList<Screw> link = new LinkedList<>();
                    link.add(screw);
                    subtrees.add(link);
                }
            }

            if(subtrees.size() ==1){
                System.out.printf("#%d ", test_case);
                for(Screw screw: subtrees.get(0)){
                    System.out.print(screw.toString());
                }
                System.out.println();
            }


        }
    }

    public static boolean findLinkedSubtree(Screw screw){
        boolean res=false;

        for(LinkedList<Screw> list: subtrees){
            if(list.getFirst().male == screw.female){
                res = true;
                break;
            }else if(list.getLast().female == screw.male){
                res = true;
                break;
            }
        }
        return res;
    }

    public static void merge(Screw screw){
        int front =-1, back=-1;
        for(int i=0; i<subtrees.size(); i++){
            if(subtrees.get(i).getFirst().male == screw.female){
                front = i;
            }else if(subtrees.get(i).getLast().female == screw.male){
                back = i;
            }
        }

        if(front != -1 && back != -1){
            LinkedList<Screw> frontList = subtrees.get(front);
            LinkedList<Screw> rearList = subtrees.get(back);
            rearList.addLast(screw);
            rearList.addAll(frontList);
            subtrees.remove(front);
        }else if(front != -1){
            subtrees.get(front).addFirst(screw);
        }else if(back != -1){
            subtrees.get(back).addLast(screw);
        }
    }

}

class Screw{
    int male, female;

    public Screw(int male, int female) {
        this.male = male;
        this.female = female;
    }

    @Override
    public String toString() {
        return male+ " " + female + " ";
    }
}