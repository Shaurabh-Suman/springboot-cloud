package Array.SlidingWindow.FixedWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DistinctElementsInWindow {

    public static List<Integer> countDistinct(int[] arr, int k) {
        List<Integer> result = new ArrayList<>();
        if (arr == null || k <= 0 || k > arr.length) {
            return result;
        }

        Map<Integer, Integer> freqMap = new HashMap<>();

        // 1. Process the first window of size K
        for (int i = 0; i < k; i++) {
            freqMap.put(arr[i], freqMap.getOrDefault(arr[i], 0) + 1);
        }
        result.add(freqMap.size());

        // 2. Slide the window
        for (int right = k; right < arr.length; right++) {
            // Remove/decrement the element sliding OUT (left side)
            int leftElement = arr[right - k];
            if (freqMap.get(leftElement) == 1) {
                freqMap.remove(leftElement);
            } else {
                freqMap.put(leftElement, freqMap.get(leftElement) - 1);
            }

            // Add/increment the element sliding IN (right side)
            int rightElement = arr[right];
            freqMap.put(rightElement, freqMap.getOrDefault(rightElement, 0) + 1);

            // Record unique count for current window
            result.add(freqMap.size());
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 1, 3, 4, 2, 3};
        int k = 4;

        List<Integer> distinctCounts = countDistinct(arr, k);
        System.out.println("Distinct element count in each window: " + distinctCounts);
    }
}
