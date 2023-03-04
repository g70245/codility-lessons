// you can also use imports, for example:
import java.util.*;
import java.util.stream.Collectors;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {

    private Set<Integer> fibs;
    
    public Solution() {
        fibs = generateFibs(100001);
    }

    public int solution(int[] A) {
        // Implement your solution here
        int riverWidth = A.length + 1;
        Map<Integer, Integer> dp = new HashMap<>();
        int minJumps = findMinJumps(A, riverWidth, dp);
        return minJumps == Integer.MAX_VALUE ? -1 : minJumps;
    }

    private int findMinJumps(int[] A, int currentEndPos, Map<Integer, Integer> dp) {
        if(dp.containsKey(currentEndPos)) {
            return dp.get(currentEndPos);
        }

        if (fibs.contains(currentEndPos)) {
            return 1;
        }

        int minJumps = Integer.MAX_VALUE;
        for (int i = currentEndPos - 1; i >= 1; i--) {
            if(A[i - 1] == 1 && fibs.contains(currentEndPos - i)) {
                minJumps = Math.min(minJumps, findMinJumps(A, i, dp));
            }
        }
        minJumps = minJumps == Integer.MAX_VALUE ? Integer.MAX_VALUE : minJumps + 1;
        dp.put(currentEndPos, minJumps);
        return minJumps;
    }

    private Set<Integer> generateFibs(int num) {
        List<Integer> fibs = new ArrayList<>();
        fibs.add(0);
        fibs.add(1);
        while(fibs.get(fibs.size() - 1) < num) {
            fibs.add(fibs.get(fibs.size() - 1) + fibs.get(fibs.size() - 2));
        }
        return fibs.stream().collect(Collectors.toSet());
    }

}
