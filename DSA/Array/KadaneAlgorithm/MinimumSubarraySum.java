package Array.KadaneAlgorithm;

public class MinimumSubarraySum {

    public static int minSubArraySum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int currentMin = nums[0];
        int globalMin = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // Kadane's decision for minimum:
            // 1. Extend the previous running minimum sum (currentMin + nums[i])
            // 2. Or start fresh from the current element (nums[i])
            currentMin = Math.min(nums[i], currentMin + nums[i]);

            // Update the global minimum sum found so far
            globalMin = Math.min(globalMin, currentMin);
        }

        return globalMin;
    }

    public static void main(String[] args) {
        int[] nums = {3, -4, 2, -3, -1, 2, -5};
        System.out.println("Minimum Subarray Sum: " + minSubArraySum(nums));
        // Output: -9 (Subarray: [-4, 2, -3, -1, 2, -5])
    }
}
