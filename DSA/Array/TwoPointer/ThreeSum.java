package Array.TwoPointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ThreeSum {
    private static List<List<Integer>> threeSum(int[] arr, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if(arr==null || arr.length==0){
            return result;
        }
        Arrays.sort(arr);

        for(int i=0;i<arr.length-2;i++){
            // Dynamic Early Stopping:
            // Since array is sorted, if 3 * current smallest number > target,
            // it's mathematically impossible for any subsequent sum to reach 'target'.
            if((long)arr[i]*3>target){
              break;
            }
            // Skip duplicate values for the outer loop element
            if(i>0 && arr[i]==arr[i-1]){
            continue;
            }
            int left = i+1;
            int right = arr.length-1;
            while(left<right){
                long sum=arr[i]+arr[left]+arr[right];
                if(sum==target){
                    result.add(new ArrayList<>(Arrays.asList(arr[i],arr[left],arr[right])));
                    /*
                    ***Note:-
                    * If you remove left < right from the inner loop, our code will throw an
                      ArrayIndexOutOfBoundsException when skipping duplicates at the edge of
                      an array.

                      Here is why that bounds check is mandatory inside the inner while loops.
                     */
                    // Skip duplicates for the left pointer
                    while(left<right && arr[left]==arr[left+1]){
                        left++;
                    }
                    // Skip duplicates for the right pointer
                    while(left<right && arr[right]==arr[right-1]){
                        right--;
                    }
                    left++;
                    right--;
                }else if(sum<target){
                    left++; // Sum is too small -> advance left pointer
                }else {
                    right--; // Sum is too large -> regress right pointer
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
        int target1=sc.nextInt();
        System.out.println("Enter 1st target of 3-sum: " + target1 );
        System.out.println("Enter 2nd target of 3-sum: " );
        int target2=sc.nextInt();
        System.out.println("Enter 2nd target of 3-sum: " + target2 );

        // Test with Target = 0
        System.out.println("Target 0: " + threeSum(arr, target1));
        // Output: [[-1, -1, 2], [-1, 0, 1]]

        // Test with Positive Target = 2
        System.out.println("Target 2: " + threeSum(arr, target2));
        // Output: [[-1, 1, 2], [0, 1, 1]] (if duplicates exist) or [[-1, 1, 2]]
    }
}
