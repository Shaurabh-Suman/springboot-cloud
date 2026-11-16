package Array.subArray;
import java.util.Arrays;

public class PrintEqualSumSubarrays {

    public static void printEqualSumSubarrays(int[] nums) {
        if (nums == null || nums.length < 2) {
            System.out.println("Array too small to partition.");
            return;
        }

        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        int leftSum = 0;

        // Iterate and find the split point between i and i + 1
        for (int i = 0; i < nums.length - 1; i++) {
            leftSum += nums[i];
            int rightSum = totalSum - leftSum;

            if (leftSum == rightSum) {
                // Slice the array into two subarrays using Java's copyOfRange
                int[] leftSubarray = Arrays.copyOfRange(nums, 0, i + 1);
                int[] rightSubarray = Arrays.copyOfRange(nums, i + 1, nums.length);

                System.out.println("Equal sum partition found!");
                System.out.println("Left Subarray: " + Arrays.toString(leftSubarray) + " (Sum: " + leftSum + ")");
                System.out.println("Right Subarray: " + Arrays.toString(rightSubarray) + " (Sum: " + rightSum + ")");
                return;
            }
        }

        System.out.println("No equal sum partition possible.");
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 5};
        printEqualSumSubarrays(nums);
        // Output:
        // Equal sum partition found!
        // Left Subarray: [1, 2, 3, 4] (Sum: 10)
        // Right Subarray: [5, 5] (Sum: 10)
    }
}