package Array.TwoPointer.SameDirection;

import java.util.Arrays;

public class MoveZeroes {

    private static void moveZeroes(int[] nums) {
        // slow = position where the next non-zero should go
        int slow = 0;

        // fast = scans every element
        for(int fast = 0; fast < nums.length; fast++) {

            // fast found a non-zero element
            if(nums[fast] != 0) {

                // Put this non-zero element at slow
                int temp = nums[slow];
                nums[slow] = nums[fast];
                nums[fast] = temp;

                // Next non-zero should go to the next position
                slow++;
            }
        }
    }
    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12};
        moveZeroes(nums);
       System.out.println("Modified array is: ");
        int count = 0;
        for (int num : nums) {
            System.out.print(num);
            if (count < nums.length - 1) {
                System.out.print(", ");
            }
            count++;
        }
    }
}
