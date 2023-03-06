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
        List<Integer> landingPlace = getLandingPlace(A);
        int[] dp = new int[landingPlace.size()];
        int NO_FIB_JUMPS_VALUE = Integer.MAX_VALUE;
        for (int i = 1; i < dp.length; i++) {
            dp[i] = fibs.contains(landingPlace.get(i)) ? 1 : NO_FIB_JUMPS_VALUE;
        }
        
        for (int i = 1 ; i < dp.length - 1; i++) {
            for (int j = i + 1; j < dp.length; j++) {
                int leftPart = dp[i];
                if (leftPart == NO_FIB_JUMPS_VALUE) {
                    continue;
                }
                
                int rightPart = fibs.contains(landingPlace.get(j) - landingPlace.get(i)) ? 1 : NO_FIB_JUMPS_VALUE;
                if (rightPart == NO_FIB_JUMPS_VALUE) {
                    continue;
                }

                dp[j] = Math.min(dp[j], leftPart + rightPart);
            }
        }

        return dp[dp.length - 1] == NO_FIB_JUMPS_VALUE ? -1 : dp[dp.length - 1];
    }

    private List<Integer> getLandingPlace(int[] A) {
        List<Integer> landingPlace = new ArrayList<>();
        landingPlace.add(0);
        for (int i = 0; i < A.length; i++) {
            if (A[i] == 1) {
                landingPlace.add(i + 1);
            }
        }
        landingPlace.add(A.length + 1);
        return landingPlace;
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
