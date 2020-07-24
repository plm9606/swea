package algorithm;

public class ShiftCombination {
    static int[] num;
    public static void main(String[] args) {
        int bit, n=5, m=3;

        bit = (1<<m) -1;
        num = new int[bit];

        while (bit < (1<< n)){
            func(bit, n);
            int temp =bit | ((bit & -bit)-1);
        }
    }

    public static void func(int bit, int n){
        int msb = 1 << (n-1);
        while (msb != 0){
            System.out.println(Boolean.valueOf(Integer.toString(msb&bit)));
            msb >>= 1;
        }
    }
}
