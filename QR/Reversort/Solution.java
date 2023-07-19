package Reversort;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        
        for(int i = 1; i <= T; ++i) {
            String result = processInput(in);
            System.out.println("Case #" + i + ": " + result);
        }
        
        in.close();
    }
    
    private static String processInput(Scanner in) {
        int N = in.nextInt();
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < N; ++i) {
            list.add(in.nextInt());
        }
        return "" + reversort(list);
    }
    
    private static int reversort(List<Integer> list) {
        int totalCost = 0;
        int n = list.size();
        
        // Naive approach: simulate reversort
        for(int i = 0; i < n - 1; ++i) {
            int minIndex = findMinIndex(list, i);
            
            // need to reverse
            reverseList(list, i, minIndex);
            totalCost += minIndex - i + 1;
        }
        
        return totalCost;
    }
    
    private static int findMinIndex(List<Integer> list, int start) {
        int min = list.get(start);
        int index = start;
        for(int i = start + 1; i < list.size(); ++i) {
            if(list.get(i) < min) {
                index = i;
                min = list.get(i);
            }
        }
        return index;
    }
    
    private static void reverseList(List<Integer> list, int start, int end) {
        while(start < end) {
            int temp = list.get(start);
            list.set(start, list.get(end));
            list.set(end, temp);
            ++start;
            --end;
        }
    }
}