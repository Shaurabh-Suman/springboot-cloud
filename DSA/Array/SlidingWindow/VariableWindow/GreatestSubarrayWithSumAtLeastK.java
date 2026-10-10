package Array.SlidingWindow.VariableWindow;

public class GreatestSubarrayWithSumAtLeastK {
    private static int longestSubarraySumLessThanEqualK(int[] nums, int target) {
        int left = 0;
        int currentSum = 0;
        int maxLength = 0;

        for (int right = 0; right < nums.length; right++) {
            // Expand the window
            currentSum += nums[right];

            // If the sum exceeds K, shrink from the left until valid
            while (currentSum > target) {
                currentSum -= nums[left];
                left++;
            }

            // Track the maximum length of a valid window
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
    public static void main(String[] args) {
        int[] nums = {2, 5, 1, 7, 10};
        int K = 14;
        System.out.println("Longest Subarray Length: " + longestSubarraySumLessThanEqualK(nums, K));
        // Output: 3 (Subarray [2, 5, 1] has sum 8 <= 14, or [5, 1, 7] has sum 13 <= 14)
    }
}

