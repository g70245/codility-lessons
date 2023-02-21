// you can also use imports, for example:
import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {
    public int solution(int[] A) {
        // Implement your solution here
        int N = A.length;
        if (N < 3) {
            return 0;
        }
        
        int[] peaksPrefixSum = new int[N + 1];
        for (int i = 1; i < N - 1; i++) {
            if (A[i] > A[i - 1] && A[i] > A[i + 1]) {
                peaksPrefixSum[i + 1] = peaksPrefixSum[i] + 1;
            } else {
                peaksPrefixSum[i + 1] = peaksPrefixSum[i];
            }
        }
        peaksPrefixSum[N] = peaksPrefixSum[N - 1];
        if (peaksPrefixSum[N] < 2) {
            return peaksPrefixSum[N];
        }

        int maxBlocks = peaksPrefixSum[N];
        while (maxBlocks > 1) {
            if (N % maxBlocks != 0) {
                maxBlocks--;
                continue;
            }

            int elementNumber = N / maxBlocks;
            boolean doesContainPeak = true;
            for (int i = 1; i <= maxBlocks; i++) {
                if (peaksPrefixSum[i * elementNumber] - peaksPrefixSum[(i - 1) * elementNumber] == 0) {
                    maxBlocks--;
                    doesContainPeak = false;
                    break;
                }
            }

            if (doesContainPeak) {
                return maxBlocks;
            }
        }

        return 1;
    }
}
