import java.util.*;

public class Solution {
    private static long max = -1;
    private static Set<Long> enc = new HashSet<>();
    
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
        max = -1;
        enc = new HashSet<>();
        int N = in.nextInt();
        
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < N; ++i) {
            int toAdd = in.nextInt();
            int freq = in.nextInt();
            
            
            for(int j = 0; j < freq; ++j) {
                list.add(toAdd);
            }
        }

        
        if(list.size() == 2) {
            if(list.get(0) == list.get(1)) {
                return list.get(0);
            }
            return 0;
        }
        
        recursive(list, 0);
        
        if(max < 0) {
            return 0;
        }
        return max;
    }
    
    private static void swap(List<Integer> list, int i, int j) {
        if (i == j) {
            return;
        }
        int x = list.get(i);
        list.set(i, list.get(j));
        list.set(j, x);
    }
    
    private static long sum(List<Integer> list) {
        long sum = 0;
        for(Integer num : list) {
            sum += num;
        }
        return sum;
    }
    
    private static long prod(List<Integer> list) {
        long prod = list.get(0);
        for(int i = 1; i < list.size(); ++i) {
            prod *= list.get(i);
        }
        return prod;
    }
    
    private static void recursive(List<Integer> list, int start) {

        if (start >= list.size() - 1) {
            // this is a finalized permutation of the array
            for(int i = 1; i < list.size() - 1; ++i) {
                List<Integer> a = list.subList(0, i);
                List<Integer> b = list.subList(i, list.size());
                
                long prod = prod(a);
                
                if(!enc.add(prod)) {
                    continue;
                }
                
                if(prod < max) {
                    continue;
                }
                
                long sum = sum(b);
                if(prod == sum && max < prod) {
                    max = prod;
                }
            }
            return;
        }

        for (int i = start; i < list.size(); ++i) {
            swap(list, start, i);
            recursive(list, start + 1);
            swap(list, start, i);
        }
    }
    
    static void printList(List<Integer> a) {
        for(int i = 0; i < a.size(); i++)
            System.out.print(a.get(i) + " ");
        System.out.println();
    }
}