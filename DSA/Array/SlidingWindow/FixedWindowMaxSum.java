package Array.SlidingWindow;

public class FixedWindowMaxSum {
    private static int maxSum(int[] arr, int k) {
        if (k == 0 || arr.length <= 2) return 0;
        int leftIndex = 0;
        int windowSum = 0;
        int maxSum = Integer.MIN_VALUE;
        for (int rightIndex = 0; rightIndex < arr.length; rightIndex++) {
            // Expand window
            windowSum += arr[rightIndex];

            // Window has exactly k elements
            if( rightIndex - leftIndex + 1 == k){

                // Use current window
                maxSum = Math.max(windowSum, maxSum);

                // Remove leftmost element
                windowSum-=arr[leftIndex];

                // Slide window
                leftIndex++;
            }
        }
    return maxSum;
    }
    public static void main(String[] args) {
        int[] arr = {2, 1, 5, 1, 3, 2};
        int k = 3;

        System.out.println(maxSum(arr, k));
    }
}
