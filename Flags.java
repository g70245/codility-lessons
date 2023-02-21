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

        List<Integer> peaks = findPeaks(A);
        int peakNum = peaks.size();
        if (peakNum <= 2) {
            return peakNum;
        }

        int K = countMaxPossibleFlags(peakNum, peaks.get(peakNum - 1) - peaks.get(0));

        int maxFlags = 0;
        while (K >= maxFlags) {
            maxFlags = Math.max(maxFlags, countFlags(peaks, K));
            K--;
        }

        return maxFlags;
    }

    private int countFlags(List<Integer> peaks, int K) {
        int maxFlags = 1;
        int accumulatedDistance = 0;
        for (int i = 1; i < peaks.size(); i++) {
            accumulatedDistance += peaks.get(i) - peaks.get(i - 1);
            if (accumulatedDistance >= K) {
                maxFlags++;
                accumulatedDistance = 0;
            }
        }
        // System.out.printf("K: %d, Flags:%d\n", K, Math.min(maxFlags, K));
        return Math.min(maxFlags, K);
    }

    private List<Integer> findPeaks(int[] A) {
        List<Integer> peaks = new ArrayList<>();
        int mntCoordinate = 1;
        while (mntCoordinate < A.length - 1) {
            if (A[mntCoordinate] < A[mntCoordinate + 1]) {
                mntCoordinate += 1;
                continue;
            }
            
            if  (A[mntCoordinate] > A[mntCoordinate - 1] && A[mntCoordinate] > A[mntCoordinate + 1]) {
                peaks.add(mntCoordinate);
            }

            mntCoordinate += 2;
        }
        return peaks;
    }

    private int countMaxPossibleFlags(int peakNum, int greatestDistance)
    {
        return Math.min((int)Math.sqrt(greatestDistance) + 1, peakNum);
    }
}
