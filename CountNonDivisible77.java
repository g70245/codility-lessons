// you can also use imports, for example:
import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {

    private int N;

    public int[] solution(int[] A) {
        // Implement your solution here
        N = A.length;
        int[] sieve = createSieve(N * 2);

        Map<Integer, Integer> counters = countElements(A);

        Map<Integer, Property> properties = new HashMap<>();
        for (int num: counters.keySet()) {
            getDivisorCount(sieve, counters, num, properties);
        }

        int[] nonDivisors = new int[N];
        for (int i = 0; i < N; i++) {
            nonDivisors[i] = properties.get(A[i]).getNonDivisorCount();
        }

        return nonDivisors;
    }

    private void getDivisorCount(int[] sieve, Map<Integer, Integer> counters, int num, Map<Integer, Property> properties) {
        if (properties.containsKey(num)) {
            return;
        }

        if (sieve[num] == 0) {
            Set<Integer> factors = new HashSet<>();
            factors.add(num);
            factors.add(1);

            int divisorCount = 0;
            for (int factor: factors) {
                divisorCount += counters.getOrDefault(factor, 0);
            }
            properties.put(num, new Property(factors, divisorCount));
        } else {
            int decompositeNum = num / sieve[num];
            if (!properties.containsKey(decompositeNum)) {
                getDivisorCount(sieve, counters, decompositeNum, properties);
            }
            Property decompositeNumProperty = properties.get(decompositeNum);
            Set<Integer> factors = new HashSet<>();    
            factors.addAll(decompositeNumProperty.factors);
            int divisorCount = decompositeNumProperty.divisorCount;
            for (int factor: decompositeNumProperty.factors) {
                int newFactor = factor * sieve[num];
                if (!factors.contains(newFactor)) {
                    factors.add(newFactor);
                    divisorCount += counters.getOrDefault(newFactor, 0);
                }
            }
            properties.put(num, new Property(factors, divisorCount));
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

    private Map<Integer, Integer> countElements(int[] A) {
        Map<Integer, Integer> counters = new HashMap<>();
        for (int i = 0; i < N; i++) {
            counters.merge(A[i], 1, (oldValue, newValue) -> oldValue + 1);
        }
        return counters;
    }

    class Property {
        private Set<Integer> factors = new HashSet<>();
        private int divisorCount;
        private int nonDivisorCount;

        public Property(Set<Integer> factors, int divisorCount) {
            this.factors = factors;
            this.divisorCount = divisorCount;
            this.nonDivisorCount = N - divisorCount;
        }

        public String toString() {
            return String.format("divisorCount:%d  divisors:%s",divisorCount, factors);
        }

        public int getNonDivisorCount() {
            return this.nonDivisorCount;
        }
    }
}
