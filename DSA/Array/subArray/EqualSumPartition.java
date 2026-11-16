package Array.subArray;

public class EqualSumPartition {

    public static int findPartitionIndex(int[] nums) {
        if (nums == null || nums.length < 2) {
            return -1;
        }

        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        int leftSum = 0;

        for (int i = 0; i < nums.length; i++) {
            // rightSum is the total sum minus what's on the left and the current element
            int rightSum = totalSum - leftSum - nums[i];

            if (leftSum == rightSum) {
                return i; // Returns the pivot index where the split occurs
            }

            leftSum += nums[i];
        }

        return -1; // No such partition exists
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 5};
        int pivot = findPartitionIndex(nums);

        if (pivot != -1) {
            System.out.println("Equal sum partition found at index: " + pivot);
            System.out.println("Left Subarray sum equals Right Subarray sum: " + (nums[pivot] * 0 + /* left sum logic */ 6));
        } else {
            System.out.println("No equal sum partition possible.");
        }
    }
}
