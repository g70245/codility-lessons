// you can also use imports, for example:
import java.util.*;
import java.util.stream.Collectors;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {

    public int solution(int[] A) {
        // Implement your solution here
        int dest = A.length + 1;
        List<Integer> fibs = prepareFibs(dest);
        if (fibs.contains(dest)) {
            return 1;
        }

        List<List<Integer>> routes = new ArrayList<>();
        routes.add(Arrays.asList(dest));

        while (routes.size() > 0) {
            System.out.print(routes.size() + " ");
            List<List<Integer>> nextProgress = new ArrayList<>();
            for (List<Integer> route : routes) {
                if (fibs.contains(route.get(0))) {
                    return route.size();
                }

                int start = 0;
                int end = fibs.size() - 1;
                while (fibs.get(--end) > route.get(0)) { }

                while (start < end) {
                    plotNewRoute(A, route, fibs.get(start++), nextProgress);
                    plotNewRoute(A, route, fibs.get(end--), nextProgress);
                }

                if (start == end) {
                    plotNewRoute(A, route, fibs.get(start), nextProgress);
                }
            }
            routes = nextProgress;
        }

        return -1;
    }

    private void plotNewRoute(int[] A, List<Integer> route, int fib, List<List<Integer>> nextProgress) {
        if (A[route.get(0) - fib - 1] == 1) {
            List<Integer> newRoute = new ArrayList<>(route);
            newRoute.set(0, route.get(0) - fib);
            newRoute.add(fib);
            nextProgress.add(newRoute);
        }
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
