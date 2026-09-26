package Array.SlidingWindow.FixedWindow;

import java.util.HashMap;
import java.util.Map;

public class FrequencyInWindow {

    public static void printWindowFrequencies(int[] arr, int k) {
        if (arr == null || k <= 0 || k > arr.length) return;

        Map<Integer, Integer> freqMap = new HashMap<>();

        // First window
        for (int i = 0; i < k; i++) {
            freqMap.put(arr[i], freqMap.getOrDefault(arr[i], 0) + 1);
        }
        System.out.println("Window 1 (indices 0 to " + (k - 1) + "): " + freqMap);

        // Sliding window
        for (int right = k; right < arr.length; right++) {
            // Outgoing element
            int leftElement = arr[right - k];
            if (freqMap.get(leftElement) == 1) {
                freqMap.remove(leftElement);
            } else {
                freqMap.put(leftElement, freqMap.get(leftElement) - 1);
            }

            // Incoming element
            int rightElement = arr[right];
            freqMap.put(rightElement, freqMap.getOrDefault(rightElement, 0) + 1);

            int windowNum = right - k + 2;
            System.out.println("Window " + windowNum + " (indices " + (right - k + 1) + " to " + right + "): " + freqMap);
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 1, 3, 4};
        int k = 3;
        printWindowFrequencies(arr, k);
    }
}