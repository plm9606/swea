package algorithm;

public class ShiftCombination {

    public static void main(String[] args) {
        int bit, n = 5, m = 3;

        bit = (1 << m) - 1;

        while (bit < (1 << n)) {
            func(bit, n);
            int temp = bit | ((bit & -bit) - 1);

            bit = (temp + 1) | (((~temp & -~temp) - 1) >> (getZeros(bit) + 1));
        }

    }

    public static void func(int bit, int n) {
        int msb = 1 << (n - 1);
        while (msb != 0) {
            System.out.printf("%d ", (msb & bit));
            msb >>= 1;
        }
        System.out.println();
    }

    public static int getZeros(int n) {
        int num = 0;
        while ((n & 1) == 0) {
            num++;
            n >>= 1;
        }

        return num;
    }
}
