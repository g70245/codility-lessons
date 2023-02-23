// you can also use imports, for example:
import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {
    public int[] solution(int[] A) {
        // Implement your solution here
        int N = A.length;
        int[] sieve = createSieve(N * 2);
        

        Map<Integer, Integer> counters = new HashMap<>();
        for (int i = 0; i < N; i++) {
            counters.put(A[i], counters.getOrDefault(A[i], 0) + 1);
        }

        Map<Integer, Property> properties = new HashMap<>();
        int[] nonDivisors = new int[N];
        for (int i = 0; i < N; i++) {
            nonDivisors[i] = N - getDivisorCount(sieve, counters, A[i], properties);
            // System.out.println(properties.get(A[i]));
        }

        return nonDivisors;
    }

    private int getDivisorCount(int[] sieve, Map<Integer, Integer> counters, int num, Map<Integer, Property> properties) {
        Property proterty = new Property();
        if (sieve[num] == 0) {
            proterty.factors.add(num);
            proterty.factors.add(1);
            for (int factor: proterty.factors) {
                proterty.divisorCount += counters.getOrDefault(factor, 0);
            }
            properties.put(num, proterty);
            return proterty.divisorCount;
        } else {
            int decompositeNum = num / sieve[num];
            getDivisorCount(sieve, counters, decompositeNum, properties);
            Property decompositeNumProperty = properties.get(decompositeNum);

            proterty.factors.addAll(decompositeNumProperty.factors);
            proterty.divisorCount = decompositeNumProperty.divisorCount;
            for (int factor: properties.get(decompositeNum).factors) {
                int newFactor = factor * sieve[num];
                if (!proterty.factors.contains(newFactor)) {
                    proterty.factors.add(newFactor);
                    proterty.divisorCount += counters.getOrDefault(newFactor, 0);
                }
            }
            properties.put(num, proterty);
            return proterty.divisorCount;
        }
    }

    private int[] createSieve(int N) {
        int i = 2;
        int[] sieve = new int[N + 1];
        while (i * i <= N) {
            if (sieve[i] == 0) {
                int k = i * i;
                while (k <= N) {
                    if (sieve[k] == 0) {
                        sieve[k] = i;
                    }
                    k += i;
                }
            }
            i += 1;
        }
        return sieve;
    }

    class Property {
        Set<Integer> factors = new HashSet<>();
        int divisorCount = 0;

        public String toString() {
            return String.format("divisorCount:%d  divisors:%s",divisorCount, factors);
        }
    }
}
