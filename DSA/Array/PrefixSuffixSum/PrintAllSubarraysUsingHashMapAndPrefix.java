package Array.PrefixSuffixSum;
import java.util.*;

public class PrintAllSubarraysUsingHashMapAndPrefix {

    public static void printAllSubarrays(int[] arr) {

        Map<Integer, List<Integer>> map = new HashMap<>();

        int prefixSum = 0;

        // Prefix sum 0 exists before index 0
        map.put(0, new ArrayList<>());
        map.get(0).add(-1);

        for (int i = 0; i < arr.length; i++) {

            prefixSum += arr[i];

            // Store current prefix sum and its index
            map.putIfAbsent(prefixSum, new ArrayList<>());
            map.get(prefixSum).add(i);
        }

        // Generate all possible start/end combinations
        for (int start = 0; start < arr.length; start++) {

            for (int end = start; end < arr.length; end++) {

                System.out.println(
                        Arrays.toString(
                                Arrays.copyOfRange(arr, start, end + 1)
                        )
                );
            }
        }
    }

    public static void main(String[] args) {

        int[] arr = {1, 2, 3};

        printAllSubarrays(arr);
    }
}
