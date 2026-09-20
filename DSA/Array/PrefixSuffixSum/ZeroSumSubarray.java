package Array.PrefixSuffixSum;

import java.util.*;

public class ZeroSumSubarray {

    // =========================================================
    // 1. EXISTENCE
    // =========================================================
    // Question:
    // Does at least one subarray have sum = 0?
    //
    // Idea:
    // If the same prefix sum appears twice,
    // the elements between them have sum = 0.
    public static boolean existsZeroSumSubarray(int[] arr) {

        Set<Integer> seenPrefixSums = new HashSet<>();

        // Empty prefix before index 0
        seenPrefixSums.add(0);

        int prefixSum = 0;

        for (int num : arr) {

            prefixSum += num;

            // Same prefix sum appeared before
            if (seenPrefixSums.contains(prefixSum)) {
                return true;
            }

            seenPrefixSums.add(prefixSum);
        }

        return false;
    }


    // =========================================================
    // 2. COUNT
    // =========================================================
    // Question:
    // How many subarrays have sum = 0?
    //
    // HashMap:
    // prefixSum -> frequency
    public static int countZeroSumSubarrays(int[] arr) {

        Map<Integer, Integer> map = new HashMap<>();

        // Empty prefix before index 0
        map.put(0, 1);

        int prefixSum = 0;
        int count = 0;

        for (int num : arr) {

            prefixSum += num;

            // If prefixSum occurred before,
            // each occurrence creates a zero-sum subarray.
            count += map.getOrDefault(prefixSum, 0);

            // Store/increase frequency
            map.put(
                    prefixSum,
                    map.getOrDefault(prefixSum, 0) + 1
            );
        }

        return count;
    }


    // =========================================================
    // 3. PRINT ALL
    // =========================================================
    // Question:
    // Print every subarray having sum = 0.
    //
    // HashMap:
    // prefixSum -> ALL indexes where it occurred
    public static void printZeroSumSubarrays(int[] arr) {

        Map<Integer, List<Integer>> map = new HashMap<>();

        // Empty prefix before index 0
        // Virtual index = -1
        map.put(0, new ArrayList<>());
        map.get(0).add(-1);

        int prefixSum = 0;

        for (int i = 0; i < arr.length; i++) {

            prefixSum += arr[i];

            // Same prefix sum appeared before
            if (map.containsKey(prefixSum)) {

                List<Integer> previousIndexes =
                        map.get(prefixSum);

                // Every previous occurrence creates
                // one zero-sum subarray.
                for (int previousIndex : previousIndexes) {

                    int start = previousIndex + 1;
                    int end = i;

                    System.out.println(
                            Arrays.toString(
                                    Arrays.copyOfRange(
                                            arr,
                                            start,
                                            end + 1
                                    )
                            )
                    );
                }
            }

            // Store current prefix sum and its index
            map.putIfAbsent(prefixSum, new ArrayList<>());
            map.get(prefixSum).add(i);
        }
    }


    // =========================================================
    // MAIN
    // =========================================================
    public static void main(String[] args) {

        int[] arr = {4, 2, -6, 3, -3};

        // 1. Existence
        System.out.println(
                "Exists: " +
                        existsZeroSumSubarray(arr)
        );

        // 2. Count
        System.out.println(
                "Count: " +
                        countZeroSumSubarrays(arr)
        );

        // 3. Print all
        System.out.println("Zero-sum subarrays:");

        printZeroSumSubarrays(arr);
    }
}
/*
Output
Exists: true
Count: 2

Zero-sum subarrays:
[4, 2, -6]
[3, -3]

 */
