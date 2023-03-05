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
        int N = A.length;
        int width = N + 1;
        if (fibs.contains(width)) {
            return 1;
        }

        Map<Integer, Integer> dp = new HashMap<>();
        for (int i = 1; i <= width; i++) {
            if (i == width || A[i - 1] == 1) {
                if (fibs.contains(i)) {
                    dp.put(i, 1);
                    continue;
                }

                int min = Integer.MAX_VALUE;
                for (Map.Entry<Integer, Integer> e : dp.entrySet()) {
                    if (fibs.contains(i - e.getKey())) {
                        min = Math.min(min, e.getValue());
                    }
                }

                if (min != Integer.MAX_VALUE) {
                    dp.put(i, min + 1);
                }
            }
        }
        return dp.containsKey(width) ? dp.get(width) : -1;
    }

    private Set<Integer> generateFibs(int limit) {
        List<Integer> fibs = new ArrayList<>();
        fibs.add(0);
        fibs.add(1);
        while(fibs.get(fibs.size() - 1) < limit) {
            fibs.add(fibs.get(fibs.size() - 1) + fibs.get(fibs.size() - 2));
        }
        return fibs.stream().collect(Collectors.toSet());
    }
}
