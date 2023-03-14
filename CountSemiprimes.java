// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {
    public int[] solution(int N, int[] P, int[] Q) {
        // Implement your solution here
        int[] factorization = new int[N + 1];
        int factor = 2;
        while (factor * factor <= N) {
            if (factorization[factor] == 0) {
                int k = factor * factor;
                while (k <= N) {
                    if (factorization[k] == 0) {
                        factorization[k] = factor;
                    }
                    k += factor;
                }
            }
            factor += 1;
        }

        int[] pSum = new int[N + 1];
        for (int i = 1; i < pSum.length; i++) {
            pSum[i] = pSum[i - 1];

            if (factorization[i] > 0 && factorization[i / factorization[i]] == 0) {
                    pSum[i] += + 1;
            }
        }

        int[] ans = new int[P.length];
        for (int i = 0; i < P.length; i++) {
            ans[i] = pSum[Q[i]] - pSum[P[i] - 1];
        }

        return ans;
    }
}
