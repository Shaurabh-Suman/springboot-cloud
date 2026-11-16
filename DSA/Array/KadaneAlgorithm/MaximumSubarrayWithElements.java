package Array.KadaneAlgorithm;

import java.util.Arrays;

public class MaximumSubarrayWithElements {

    public static int[] findMaximumSubarray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        int maxSum = nums[0];
        int currentSum = nums[0];

        // Pointers to track the subarray boundaries
        int start = 0;
        int end = 0;
        int tempStart = 0;

        for (int i = 1; i < nums.length; i++) {
            // Kadane's core decision: extend or start fresh?
            if (nums[i] > currentSum + nums[i]) {
                currentSum = nums[i];
                tempStart = i; // A new potential subarray begins here
            } else {
                currentSum += nums[i];
            }

            // If we found a new maximum sum, update our global window boundaries
            if (currentSum > maxSum) {
                maxSum = currentSum;
                start = tempStart;
                end = i;
            }
        }

        // Return the actual subarray slice from start to end
        return Arrays.copyOfRange(nums, start, end + 1);
    }

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] result = findMaximumSubarray(nums);

        System.out.println("Maximum Subarray: " + Arrays.toString(result));
        // Output: [4, -1, 2, 1]
    }
}