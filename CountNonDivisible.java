// you can also use imports, for example:
import java.util.*;
import java.util.stream.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {

    public int[] solution(int[] A) {
        // Implement your solution here
        int N = A.length;
        Map<Integer, Property> properties = countElements(A);
        List<Integer> sortedDistincts = properties.keySet().stream().sorted().collect(Collectors.toList());

        int divisor = 1;
        while (divisor * divisor <= sortedDistincts.get(sortedDistincts.size() - 1)) {
            int anotherDivisor = divisor;
            int dividend = divisor * anotherDivisor;
            while (dividend <= sortedDistincts.get(sortedDistincts.size() - 1)) {
                if (properties.containsKey(dividend)) {
                    if (properties.containsKey(divisor)) {
                        properties.get(dividend).nonDivisorCount -= properties.get(divisor).count;
                    }

                    if (anotherDivisor != divisor && properties.containsKey(anotherDivisor)) {
                        properties.get(dividend).nonDivisorCount -= properties.get(anotherDivisor).count;
                    }
                }
                dividend += divisor;
                anotherDivisor++;
            }
            divisor += 1;
        }

        int[] nonDivisors = new int[N];
        for (int i = 0; i < N; i++) {
            nonDivisors[i] = properties.get(A[i]).nonDivisorCount;
        }

        return nonDivisors;
    }

    private Map<Integer, Property> countElements(int[] A) {
        int N = A.length;
        Map<Integer, Property> properties = new HashMap<>();
        for (int i = 0; i < N; i++) {
            Property property = properties.getOrDefault(A[i], new Property(N));
            property.count++;
            properties.putIfAbsent(A[i], property);
        }
        return properties;
    }

    class Property {
        int count;
        int nonDivisorCount;

        public Property(int nonDivisorCount) {
            this.nonDivisorCount = nonDivisorCount;
        }

        public String toString() {
            return String.format("nonDivisors: %d", nonDivisorCount);
        }
    }
}
