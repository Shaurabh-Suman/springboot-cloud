package Array.KadaneAlgorithm;
/*
How the Code WorkscurrentSum = Math.max(nums[i], currentSum + nums[i]):
This single line captures the entire philosophy of Kadane's algorithm.
If currentSum drops below zero, adding it to the next element would only drag it down.
Thus, it cuts its losses and resets the window starting right at
nums[i].maxSum = Math.max(maxSum, currentSum): Keeps a running record of the highest sum
encountered across any valid subarray during the scan.
**************************************************************************************
int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

The most important concept: Continue or Restart?
We currently have:
Previous number: -2
Current number:   1


We have two choices.
Choice A: Continue the old subarray
-2
1
-1

Previous sum + current number = -1

Choice B: Forget the old subarray and start fresh
1
1

Current number alone = 1

Choice B wins
We choose 1 because 1 is greater than -1.

Do you see what happened?

We discarded -2 because carrying it forward was making our sum smaller.

This is the actual idea behind Kadane's Algorithm.
4. Now look at the Java code
currentSum = Math.max(nums[i], currentSum + nums[i]);


Let's translate it into normal English:
currentSum = maximum of:

1. Current number alone
2. Previous sum + current number


That's all!

We are not randomly using Math.max().

We are making a decision: continue the previous subarray or restart from the current element.
**************************************************************************************

 Complexity AnalysisTime Complexity: O(n) — It performs a single pass through the array.
 Space Complexity: O(1) — It only uses two integer variables (currentSum and maxSum),
 requiring zero extra memory.
 */
public class MaximumSubarray {

    public static int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // Initialize both currentSum and maxSum with the first element
        int currentSum = nums[0];
        int maxSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // Kadane's Core Decision:
            // 1. Should we extend the previous subarray? (currentSum + nums[i])
            // 2. Or is the baggage too heavy, so we start fresh from the current element? (nums[i])
            currentSum = Math.max(nums[i], currentSum + nums[i]);

            // Update the global maximum sum found so far
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Maximum Subarray Sum: " + maxSubArray(nums));
        // Output: 6 (Subarray: [4, -1, 2, 1])
    }
}
