// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {
    public int solution(int[] A, int[] B) {
        // Implement your solution here
        int Z = A.length;
        int ans = 0;
        for (int i = 0; i < Z; i++) {
            int gcd = A[i] >= B[i] ? gcd(A[i], B[i]) : gcd(B[i], A[i]);
            if (A[i] == B[i] || (checkIfHavingSamePrimeDivisors(gcd, A[i]) && checkIfHavingSamePrimeDivisors(gcd, B[i]))) {
                ans++;
            }
        }
        return ans;
    }

    private boolean checkIfHavingSamePrimeDivisors(int gcd, int num) {
        if (gcd == 1) {
            return false;
        } 

        if (gcd == num) {
            return true;
        }
        
        num /= gcd;
        gcd = gcd >= num ? gcd(gcd, num) : gcd(num, gcd);
        return checkIfHavingSamePrimeDivisors(gcd, num);
    }

    private int gcd (int a, int b) {
        if (a % b == 0) {
            return b;
        } else {
            return gcd(b, a % b);
        }
    }
}
