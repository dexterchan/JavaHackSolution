//I have never seen such idea of reusing standart digits dp, cool :)
import java.io.*;
import java.util.*;

public class luckyNumber {

    BufferedReader in;
    PrintWriter out;
    StringTokenizer tok = new StringTokenizer("");

    public static void main(String[] args) {
        new luckyNumber().run();
    }

    public void run() {
        try {
            long t1 = System.currentTimeMillis();
            in = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);

            Locale.setDefault(Locale.US);
            solve();
            in.close();
            out.close();
            long t2 = System.currentTimeMillis();
            System.err.println("Time = " + (t2 - t1));
        } catch (Throwable t) {
            t.printStackTrace(System.err);
            System.exit(-1);
        }
    }

    String readString() throws IOException {
        while (!tok.hasMoreTokens()) {
            tok = new StringTokenizer(in.readLine());
        }
        return tok.nextToken();
    }

    int readInt() throws IOException {
        return Integer.parseInt(readString());
    }

    long readLong() throws IOException {
        return Long.parseLong(readString());
    }

    double readDouble() throws IOException {
        return Double.parseDouble(readString());
    }
    // solution
    boolean[] prime;
    int c = 0;

    private boolean isPrime(int x) {
        if (x <= 1)
            return false;
        for (int i = 2; i * i <= x; i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }

    private void debug(Object... args) {
        System.out.println(Arrays.deepToString(args));
    }

    void solve() throws IOException {
        long[][][] f = precalcF();
        int n = readInt();
        for (int i = 0; i < n; i++) {
            long a = readLong(), b = readLong();
            long res = calculate(b, f) - calculate(a - 1, f);
            //    long slow = runSlowSolution((int) a, (int) b);
            out.println((calculate(b, f) - calculate(a - 1, f)));
            //    if (res == slow)
            //        debug("ok",res);
            //    else
            //        debug(res, " should be ", slow);
        }
    }

    private long calculate(long r, long[][][] f) {
        if (r <= 1) {
            return 0;
        }
        long res = 0;
        int[] digits = new int[18];
        for (int i = 17; i >= 0; i--) {
            digits[i] = (int) (r % 10);
            r /= 10;
        }
        int sum = 0;
        int sumSq = 0;
        for (int i = 0; i < 18; i++) {
            for (int nextDigit = 0; nextDigit < digits[i]; nextDigit++) {
                res += f[i + 1][sum + nextDigit][sumSq + nextDigit * nextDigit];
            }
            sum += digits[i];
            sumSq += digits[i] * digits[i];
        }
        if (prime[sum] && prime[sumSq]) {
            res++;
        }
        //debug(r, " ", res);
        return res;
    }

    private long[][][] precalcF() {
        int n = 18;
        prime = new boolean[81 * n + 1];
        for (int i = 2; i < prime.length; i++) {
            if (isPrime(i)) {
                prime[i] = true;
            }
        }
        long[][][] f = new long[n + 1][9 * n + 1][81 * n + 1];
        for (int sum = 0; sum <= 9 * n; sum++) {
            for (int sumSq = 0; sumSq <= 81 * n; sumSq++) {
                if (prime[sum] && prime[sumSq]) {
                    f[n][sum][sumSq] = 1;
                }
            }
        }
        for (int i = n; i > 0; i--) {
            for (int sum = 0; sum <= 9 * n; sum++) {
                for (int sumSq = 0; sumSq <= 81 * n; sumSq++) {
                    if (f[i][sum][sumSq] > 0) {
                        for (int nextDigit = 0; nextDigit <= 9; nextDigit++) {
                            if (sum - nextDigit >= 0) {
                                if (sumSq - nextDigit * nextDigit >= 0) {
                                    f[i - 1][sum - nextDigit][sumSq - nextDigit * nextDigit] += f[i][sum][sumSq];
                                }
                            }
                        }
                    }
                }
            }
        }
        return f;
    }

    private long runSlowSolution(int a, int b) {
        int res = 0;
        for (int i = a; i <= b; i++) {
            if (isLuckyNumber(i)) {
                res++;
            }
        }
        return res;
    }

    boolean isLuckyNumber(int x) {
        if (x <= 1)
            return false;
        int sum = 0, sumSq = 0;
        while (x > 0) {
            sum += x % 10;
            sumSq += (x % 10) * (x % 10);
            x /= 10;
        }
        return (isPrime(sum) && isPrime(sumSq));
    }
}