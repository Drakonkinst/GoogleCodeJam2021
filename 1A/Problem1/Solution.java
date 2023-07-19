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
        int N = in.nextInt();
        
        List<Long> list = new ArrayList<>();
        for(int i = 0; i < N; ++i) {
            long toAdd = in.nextLong();
            list.add(toAdd);
        }
        
        long numDigits = 0;
        for(int i = 1; i < N; ++i) {
            long prev = list.get(i - 1);
            long current = list.get(i);
            
            if(current > prev) {
                continue;
            }
            
            long successor = successor(prev, current);
            System.out.println(successor);
            int diff = getIntLength(successor) - getIntLength(current);
            numDigits += diff;
            list.set(i, successor);
        }
        
        return numDigits;
    }
    
    private static int getIntLength(long num) {
        return ("" + num).length();
    }
    
    private static long successor(long num, long startsWith) {
        // get smallest number higher than num that starts with startsWith
        while(startsWith <= num) {
            startsWith *= 10;
            int len = getIntLength(startsWith);
            int numLen = getIntLength(num);
            int start = 0;
            if(numLen >= len) {
                int digit = Integer.parseInt("" + ("" + num).charAt(len - 1));
                start = digit;
                /*
                if(digit == 9) {
                    startsWith *= 10;
                } else {
                    startsWith += digit + 1;
                }*/
            }
            
            for (int i = start; i < 10; ++i) {
                if (startsWith + i > num) {
                    return startsWith + i;
                } else {
                    //System.out.println("NUM: " + num + ", LEN: " + len);
                    int origLen = getIntLength(num);
                    long firstLen = num;
                    if(origLen > len) {
                        firstLen = Long.parseLong(("" + num).substring(0, len));
                    }
                    
                    if(startsWith + i > firstLen) {
                        startsWith += i;
                        break;
                    }
                    
                }
            }
        }
        return startsWith;
    }
}