package utils;

public class Helper {

    public static long getFibonacciValueByIndex(int n) {
        if (n == 0) {
            return 0;
        }
        if (n < 0 || n > 92) {
            throw new IndexOutOfBoundsException();
        }
        long a = 0;
        long b = 1;
        for (int i = 2; i <= n; i++) {
            long c = a + b;
            a = b;
            b = c;
        }
        return b;
    }
}
