package Array.KadaneAlgorithm;
/*
1. First, what is a circular subarray?
In a normal array, we move from left to right. We cannot jump from the last element back to the first element.

But in a circular array, the last element is connected to the first element.

Consider:
int[] nums = {5, -3, 5};


Normal array
5

-3

5


We can select [5, -3, 5], but cannot jump across the boundary.

Circular array
5, -3, 5


We can select the last 5 and then wrap around to the first 5.
5 + 5 = 10

So, the maximum circular subarray sum is 10.

Normal Kadane would give us 7 (5 + -3 + 5).

We need something extra to handle the circular case.
 */
public class MaximumCircularSubarray {

    public static int maxSubarraySumCircular(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int totalSum = 0;
        int maxSum = nums[0];
        int currMax = nums[0];

        int minSum = nums[0];
        int currMin = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];

            // 1. Standard Kadane for Maximum Subarray Sum
            currMax = Math.max(num, currMax + num);
            maxSum = Math.max(maxSum, currMax);

            // 2. Modified Kadane for Minimum Subarray Sum
            currMin = Math.min(num, currMin + num);
            minSum = Math.min(minSum, currMin);

            totalSum += num;
        }
        totalSum += nums[0]; // Don't forget to include the first element in total sum

        // Edge case: If all numbers are negative, maxSum will be negative.
        // In this scenario, a circular wrap-around doesn't apply, so return maxSum.
        if (maxSum < 0) {
            return maxSum;
        }

        // The answer is either the max normal subarray or the max circular wrap-around subarray
        return Math.max(maxSum, totalSum - minSum);
    }

    public static void main(String[] args) {
        int[] nums = {5, -3, 5};
        System.out.println("Maximum Circular Subarray Sum: " + maxSubarraySumCircular(nums));
        // Output: 10 (Subarray: [5] + [5], wrapping around -3)
    }
}