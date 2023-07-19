package Median_Sort;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        int N  = in.nextInt();
        int Q = in.nextInt();
        
        for(int i = 0; i < T; ++i) {
            solution(in, N, Q / T);
        }
        
        in.close();
    }
    
    private static void solution(Scanner in, int N, int Q) {
        
    }
}