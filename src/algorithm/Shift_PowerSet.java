package algorithm;

public class Shift_PowerSet {
    public static void main(String[] args) {
        // n개의 원소로 만들어 낼 수 있는 부분집합의 수 = 2^n개
        int n = 3;
        // 1<<n은 2^n과 동일하다
        for(int i=0; i<(1<<n); i++){
            System.out.println(i);
            System.out.print("[");
            // n개의 비트에 대해
            for(int j=0; j<n; j++){
                /*
                AND 연산을 통해 검사할 비트의 자리가 1인 경우에만 해당 원소를 출력
                e.g. i == 1, j == 0
                0001 (i)
                0001 (1<<0)
                0001, arr[0] 출력

                i == 1, j == 1
                0001 (i)
                0010 (1<<1)
                0000, 출력X
                */
                if((i & (1<<j)) != 0){
                    System.out.print(j+",");
                }
            }
            System.out.println("]");
        }
    }
}
