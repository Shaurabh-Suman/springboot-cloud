package Array.SlidingWindow.FixedWindow;

import java.util.Scanner;

public class AverageOfSubarrays {
    private static void averageOfSubarrays(int[] arr, int k) {
        // 1. Guard Clauses for Edge Cases
        if (arr == null || k <= 0 || k > arr.length) {
            System.out.println("Invalid input: Array is null or k is out of bounds.");
            return;
        }
        // 2. Use 'long' to prevent integer overflow during sum accumulation
         long windowSum = 0;

        // Calculate sum of the first window
        for (int i = 0; i < k; i++) {
            windowSum += arr[i];
        }
        // Print average for first window
        System.out.println((double) windowSum / k);

        // Slide the window across the remaining elements
        for (int rightIndex = k; rightIndex < arr.length; rightIndex++) {
            windowSum += arr[rightIndex]; // Add incoming element
            windowSum -= arr[rightIndex - k]; // Remove outgoing element

            System.out.println((double) windowSum / k);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the size of the sub-array : ");
        int subArrSize = sc.nextInt();
        int[] arr = {1, 3, 2, 6, -1, 4, 1, 8, 2};
        averageOfSubarrays(arr, subArrSize);
    }
}

/*
Enter the size of the sub-array : 5
2.2
2.8
2.4
3.6
2.8
 */