// you can also use imports, for example:
import java.util.*;
import java.util.stream.Collectors;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {

    private static final int CAN_NOT_REACH_VALUE = Integer.MAX_VALUE;

    public int solution(int[] A) {
        // Implement your solution here
        int dest = A.length + 1;
        List<Integer> fibs = prepareFibs(dest);
        if (fibs.contains(dest)) {
            return 1;
        }

        List<List<Integer>> routes = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < fibs.size() && fibs.get(i) < dest; i++) {
            int nextDest = dest - fibs.get(i);            
            if (A[nextDest - 1] == 1) {
                routes.add(Arrays.asList(nextDest, fibs.get(i)));
            }
        }

        while (routes.size() > 0) {
            List<List<Integer>> nextProgress = new ArrayList<>();
            for (List<Integer> route : routes) {
                if (ans.size() != 0 && route.size() + 1 >= ans.size()) {
                    continue;
                }

                for (int i = 0; i < fibs.size() && fibs.get(i) <= route.get(0); i++) {
                    int nextDest = route.get(0) - fibs.get(i);
                    if (nextDest == 0) {
                        ans = new ArrayList<>(route);
                        ans.add(fibs.get(i));
                        break;
                    }
                    
                    if (A[nextDest - 1] == 1) {
                        List<Integer> newRoute = new ArrayList<>(route);
                        newRoute.set(0, nextDest);
                        newRoute.add(fibs.get(i));
                        nextProgress.add(newRoute);
                    }
                }
            }
            routes = nextProgress;
        }

        return ans.size() - 1;
    }

    private List<Integer> prepareFibs(int limit) {
        List<Integer> fibs = new ArrayList<>();
        fibs.add(0);
        fibs.add(1);
        while(fibs.get(fibs.size() - 1) < limit) {
            fibs.add(fibs.get(fibs.size() - 1) + fibs.get(fibs.size() - 2));
        }
        fibs.remove(0);
        return fibs.stream().distinct().collect(Collectors.toList());
    }
}
