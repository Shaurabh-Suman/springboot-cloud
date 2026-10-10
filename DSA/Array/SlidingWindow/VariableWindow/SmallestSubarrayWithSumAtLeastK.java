package Array.SlidingWindow.VariableWindow;

public class SmallestSubarrayWithSumAtLeastK {
    private static int minSubArrayLen(int target, int[] nums) {
        int leftIndex=0;
        int currentSum=0;
        int minLength=Integer.MAX_VALUE;
        // Expand the right boundary of the window
        for(int rightIndex=0;rightIndex<nums.length;rightIndex++){
            currentSum+=nums[rightIndex];
            // Shrink the window from the left as long as the condition holds true
            while(currentSum>=target){
                minLength=Math.min(minLength, rightIndex-leftIndex+1);
                currentSum-=nums[leftIndex];
                leftIndex++; // Shrink window
            }
        }
        // If minLength was not updated, return 0 (no valid subarray found)
        return (minLength == Integer.MAX_VALUE) ? 0 : minLength;
    }
    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 2, 4, 3};
        int K = 7;
        System.out.println("Smallest Subarray Length: " + minSubArrayLen(K, nums));
        // Output: 2
    }
}
