// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {
    public int solution(int[] A) {
        // Implement your solution here
        int N = A.length;
        if (N == 3) {
            return 0;
        }

        /*  
            When a new integer, denoted as "a", enters to the double slices, 
            the maximal sum of the double slices will be the maximum of these values:
            1. p + q + a
            2. p + q + m
            3. q
            4. 0
            
            Test cases:
            [0, 1, 2, 2, 2, 2, 0]
            [0, 2, 2, 2, 2, 1, 0]
            [0, 2, 2, 1, 2, 2, 0]
            [0, -2, -1, -1, -1, -1, 0]
            [0, -1, -1, -1, -1, -2, 0]
            [0, -2, -1, -2, -1, -1, 0]
            [0, 4, -2, 2, -3, -2, 0]
            [0, 5, -4, 2, -3, -10, 0]
            [0, 6, -4, 2, -3, -10, 0]
            [0, 4, -4, -2, -3, -10, 0]
        */
        int maxDoubleSliceSum = 0, maxEnding = 0, p = 0, m = A[1];
        for (int i = 2; i < N -1; i++) {
            int a = A[i];

            maxEnding = Math.max(0, maxEnding + Math.max(a, Math.max(m, - p)));
            // maxEnding + a <= q → maxEnding + a <= maxEnding - p → a <= -p
            if (a < m || a + p <= 0 || maxEnding <= 0) {
                p = maxEnding;
                m = a;
            }
            maxDoubleSliceSum = Math.max(maxDoubleSliceSum, maxEnding);
            // System.out.printf("A[%d]=%d, (p,m,q)=(%d,%d,%d), max=%d\n", i, a, p, m, maxEnding - p, maxEnding);
        }
        return maxDoubleSliceSum;
    }
}
