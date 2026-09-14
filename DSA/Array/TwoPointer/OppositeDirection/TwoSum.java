package Array.TwoPointer.OppositeDirection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoSum {
    private static List<List<Integer>>  twoPointerSum(int[] arr, int target) {
        List<List<Integer>> result = new ArrayList<>();
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int sum = arr[left] + arr[right];
            if (sum == target) {
                result.add(Arrays.asList(arr[left], arr[right]));

                /*
                //arr=[2, 2, 3, 3], target = 5.
                 Result without skipping: [[2, 3], [2, 3]] (Incorrect if unique pairs are required).

                 Result with skipping: [[2, 3]] (Correct).
                 */
                // Skip duplicate values for left pointer
                while (left < right && arr[left] == arr[left + 1]) {
                    left++;
                }
                // Skip duplicate values for right pointer
                while (left < right && arr[right] == arr[right - 1]) {
                    right--;
                }
                left++;
                right--;
            }else if (sum < target) {
                left++;
            }else {
                right--;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int arr[]={2, 6, 8, 9, 12, 15, 16, 18, 22}; //Must needed sorted array for 2-ppointer Technique
        int target = 24;
        System.out.println("The possible 2-Sum pairs are : " + twoPointerSum(arr, target));
    }
}
