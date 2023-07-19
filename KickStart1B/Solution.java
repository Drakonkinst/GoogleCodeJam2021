import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();

        for (int i = 1; i <= T; ++i) {
            String result = "" + processInput(in);
            System.out.println("Case #" + i + ": " + result);
        }

        in.close();
    }

    private static long processInput(Scanner in) {
        long Z = in.nextLong();
        List<Integer> pds = new ArrayList<>();
        long product = 0;

        for (int i = 0; i * i <= Z; ++i) {
            if (isPrime(i)) {
                pds.add(i);
                if (pds.size() >= 2) {
                    long prod = pds.get(i) * pds.get(i - 1);
                    if (prod <= Z) {
                        product = prod;
                    } else {
                        return product;
                    }
                }
            }
        }
        return product;

    }

    private static boolean isPrime(long n) {
        if (n == 2) {
            return true;
        }

        if (n % 2 == 0 || n < 1) {
            return false;
        }

        for (long i = 3; i * i < n; ++i) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }
}