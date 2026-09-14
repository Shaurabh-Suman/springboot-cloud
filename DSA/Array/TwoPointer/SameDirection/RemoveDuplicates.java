package Array.TwoPointer.SameDirection;

public class RemoveDuplicates {
    private static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int slow=0;
        for(int fast=1;fast<nums.length;fast++) {
            if (nums[fast]!=nums[slow]) {
                slow++;
                //nums[slow + 1] = nums[fast];
                nums[slow]=nums[fast];
            }
        }
        return slow + 1;
    }
    public static void main(String[] args) {
            int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};

            int newLength = removeDuplicates(nums);

            System.out.println("New Length: " + newLength);
            System.out.print("Modified Array: ");
            for (int i = 0; i < newLength; i++) {
                System.out.print(nums[i] + " ");
            }
            // Output:
            // New Length: 5
            // Modified Array: 0 1 2 3 4
    }
}
