package Array.TwoPointer.OppositeDirection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FourSum {
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return result;
        }

        // 1. Sort array to enable two-pointer traversal
        Arrays.sort(nums);
        int n = nums.length;

        // First outer loop: Fix first number (nums[i])
        for (int i = 0; i < n - 3; i++) {
            // Skip duplicates for first element
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            // Dynamic Early Stopping 1: Smallest possible sum > target
            if ((long) nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) break;

            // Dynamic Early Stopping 2: Largest possible sum with nums[i] < target
            if ((long) nums[i] + nums[n - 1] + nums[n - 2] + nums[n - 3] < target) continue;

            // Second outer loop: Fix second number (nums[j])
            for (int j = i + 1; j < n - 2; j++) {
                // Skip duplicates for second element
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;

                // Dynamic Early Stopping 3: Smallest possible sum with nums[i] & nums[j] > target
                if ((long) nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) break;

                // Dynamic Early Stopping 4: Largest possible sum with nums[i] & nums[j] < target
                if ((long) nums[i] + nums[j] + nums[n - 1] + nums[n - 2] < target) continue;

                int left = j + 1;
                int right = n - 1;

                // Two-pointer search for the remaining two numbers
                while (left < right) {
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];

                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        // Skip duplicates for left pointer (requires left < right check!)
                        while (left < right && nums[left] == nums[left + 1]) left++;

                        // Skip duplicates for right pointer (requires left < right check!)
                        while (left < right && nums[right] == nums[right - 1]) right--;

                        left++;
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return result;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the length of the array.");
        int arrLen = sc.nextInt();
        int[] arr = new int[arrLen];
        // int[] arr = { 16, 17, 4, 3, 5, 2 };
        System.out.println("Enter the elements of the array.");
        for (int i = 0; i < arrLen; i++) {
            System.out.println("Enter the element " + (i + 1) + ".");
            arr[i] = sc.nextInt();
        }
        System.out.println("Enter 1st target of 3-sum: " );
        int target=sc.nextInt();
        System.out.println("Enter 1st target of 3-sum: " + target);
        System.out.println(fourSum(arr, target));
    }
}
