package Reversort_Engineering;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        
        for(int i = 0; i < T; ++i) {
            String result = processInput(in);
            System.out.println("Case #" + (i + 1) + ": " + result);
        }
        
        in.close();
    }
    
    private static String processInput(Scanner in) {
        int N = in.nextInt();
        int C = in.nextInt();
        List<Integer> list = new ArrayList<>();
        
        for(int i = 0; i < N; ++i) {
            list.add(i + 1);
        }
        
        // step through all permutations of array
        String[] ans = new String[1];
        ans[0] = "";
        recursive(list, 0, C, ans);
        if(ans[0].length() > 0) {
            return ans[0];
        }
        return "IMPOSSIBLE";
    }
    
    private static void recursive(List<Integer> list, int start, int C, String[] ans) {
        
        if(start >= list.size() - 1) {
            // this is a finalized permutation of the array
            int cost = reversort(new ArrayList<>(list));
            if(cost == C) {
                ans[0] = listAsString(list);
            }
            return;
        }
        
        for(int i = start; i < list.size(); ++i) {
            swap(list, start, i);
            recursive(list, start + 1, C, ans);
            swap(list, start, i);
        }
    }
    
    private static void swap(List<Integer> list, int i, int j) {
        if(i == j) {
            return;
        }
        int x = list.get(i);
        list.set(i, list.get(j));
        list.set(j, x);
    }
    
    private static String listAsString(List<Integer> list) {
        String s = "";
        for(int i = 0; i < list.size(); ++i) {
            if(i < list.size() - 1) {
                s += list.get(i) + " ";
            } else {
                s += list.get(i);
            }
        }
        return s;
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