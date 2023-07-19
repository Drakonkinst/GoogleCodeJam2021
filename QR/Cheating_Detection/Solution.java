package Cheating_Detection;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(System.in);
            int T = in.nextInt();
            in.nextInt();

            for (int i = 0; i < T; ++i) {
                String result = processInput(in);
                System.out.println("Case #" + (i + 1) + ": " + result);
            }

            in.close();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("WRONG ANSWER");
        }
    }

    private static String processInput(Scanner in) {
        final float ep = 1.4e-45f;
        String[] sols = new String[100];
        float[] acc = new float[100];
        // float[] sus = new float[100];

        // acc can be used to determine skill
        for (int i = 0; i < 100; ++i) {
            String s = in.next();
            sols[i] = s;
            acc[i] = getAccuracy(s);
        }

        // qs can be used to determine question difficulty
        float[] qs = new float[10000];
        for (int i = 0; i < 10000; ++i) {
            qs[i] = getQAcc(sols, i);

        }

        float maxSus = 0;
        int susInd = 0;
        for (int i = 0; i < 100; ++i) {
            // anyone with acc > 50% is liable to be a cheater
            float ac = acc[i];
            if (ac > 0.5f) {
                float numSus = 0;
                for (int j = 0; j < 10000; ++j) {
                    char sAns = sols[i].charAt(j);
                    
                    if (qs[j] + ep > ac && sAns == '1') {
                        numSus += Math.abs(qs[j] - ac);
                    } else if (qs[j] - ep < ac && sAns == '0') {
                        numSus += Math.abs(qs[j] - ac);
                    }
                    numSus += Math.abs(qs[j] - ac); 
                }
                //System.out.println(i + " - " + (ac * 100) + "% " + (numSus));
                if (numSus > maxSus) {
                    maxSus = numSus;
                    susInd = i;
                }

            }
        }

        return (susInd + 1) + "";
    }

    private static float getAccuracy(String s) {
        int n = s.length();
        int c = 0;
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == '1') {
                ++c;
            }
        }
        return c * 1.0f / n;
    }

    private static float getQAcc(String[] sols, int index) {
        int c = 0;
        for (int i = 0; i < sols.length; ++i) {
            if (sols[i].charAt(index) == '1') {
                ++c;
            }
        }
        return c * 1.0f / 100;
    }
}