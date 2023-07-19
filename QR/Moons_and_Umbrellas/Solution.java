package Moons_and_Umbrellas;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();

        for (int i = 0; i < T; ++i) {
            String result = processInput(in);
            System.out.println("Case #" + (i + 1) + ": " + result);
        }

        in.close();
    }

    private static String processInput(Scanner in) {
        int X = in.nextInt();
        int Y = in.nextInt();
        String S = in.next();
        return "" + solution(X, Y, S);
    }

    private static int solution(int X, int Y, String S) {
        // Skip any enclosed ? since they can be optimized out
        int index;
        do {
            index = S.indexOf("???");
            if(index > -1) {
                S = S.replace("???", "??");
            }
        } while(index > -1);
        
        return minCost(X, Y, S);
    }

    private static int minCost(int X, int Y, String S) {
        int nextQ = S.indexOf('?');
        if (nextQ > -1) {
            String p1 = S.substring(0, nextQ) + "C" + S.substring(nextQ + 1);
            String p2 = S.substring(0, nextQ) + "J" + S.substring(nextQ + 1);
            return min(minCost(X, Y, p1), minCost(X, Y, p2));
        } else {
            return getCost(X, Y, S);
        }
    }

    private static int min(int a, int b) {
        if (a < b) {
            return a;
        }
        return b;
    }

    private static int getCost(int X, int Y, String S) {
        int totalCost = 0;
        int start = 0;
        int nextCJ;
        int nextJC;

        do {
            nextCJ = S.indexOf("CJ", start);
            if (nextCJ > -1) {
                totalCost += X;
                start = nextCJ + 1;
            }
        } while (nextCJ > -1);

        start = 0;
        do {
            nextJC = S.indexOf("JC", start);
            if (nextJC > -1) {
                totalCost += Y;
                start = nextJC + 1;
            }
        } while (nextJC > -1);

        return totalCost;
    }
}