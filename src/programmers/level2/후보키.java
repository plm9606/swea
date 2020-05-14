package programmers.level2;

import java.lang.reflect.Array;
import java.util.*;

/**
 * 1. 각 속성별로 유일한 값만 존재하는지 검사한다.
 * 2. 중복이 있는 속성의 col index를 저장해둔다. {(1,2)(0,3,4)}
 * 3. 1개부터 n개를 선택하는 경우의 수를 완전탐색한다
 * 4. 각 속성의 중복되는 인덱스가 있는 경우 그룹 () 별 숫자가 겹치는지 확인한다.
 */
public class 후보키 {
    public static ArrayList<List<List<Integer>>> indexList;
    public static void main(String[] args) {
        String[][] r = {
                {"100","ryan","music","2"},
                {"200","apeach","math","2"},
                {"300","tube","computer","3"},
                {"400","con","computer","4"},
                {"500","muzi","music","3"},
                {"600","apeach","music","2"}
        };

        solution(r);

    }
    public static int solution(String[][] relation) {
        int answer = 0;
        int relationCount = relation[0].length;
        int tupleCount = relation.length;
        indexList = new ArrayList<>();

        for(int i=0; i<relationCount; i++){
            Map<String, ArrayList<Integer>> map = new HashMap<>();
            for(int j=0; j<tupleCount; j++){
                if(map.keySet().contains(relation[j][i])){
                    map.get(relation[j][i]).add(j);
                }else {
                    ArrayList<Integer> arr = new ArrayList<>();
                    arr.add(j);
                    map.put(relation[j][i], arr);
                }
            }
            List<List<Integer>> duplicateIndexList = new ArrayList<>();
            for(String key : map.keySet()){
                if(map.get(key).size() > 1)
                duplicateIndexList.add(map.get(key));
            }
            indexList.add(duplicateIndexList);
        }

        combination(new boolean[relationCount], 1, 0);
        return answer;
    }

    public static void combination(boolean[] bit, int toPick, int target){
        if(toPick == 0){
            // do something
            for(int i=0; i<bit.length; i++){
                if(!bit[i]){
                    continue;
                }
                if(indexList.get(i).size() > 0){

                }
            }
            return;
        }

        bit[target] = true;
        combination(bit, toPick-1, target+1);
        bit[target] = false;
        combination(bit, toPick, target+1);
    }
}
