package Array.TwoPointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class kSumHelper {
    // Calling method
    // Only arr, target and K are passed
    private static List<List<Integer>> K_Sum(
            int[] arr,
            int target,
            int kValueSum) {

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();

        Arrays.sort(arr);

        K_SumHelper(
                arr,
                target,
                kValueSum,
                0,
                current,
                result
        );

        return result;
    }


    // Supporting recursive method
    private static void K_SumHelper(
            int[] arr,
            long target,
            int k,
            int start,
            List<Integer> current,
            List<List<Integer>> result) {

        // Base case: Two Sum
        if (k == 2) {

            int left = start;
            int right = arr.length - 1;

            while (left < right) {

                long sum = (long) arr[left] + arr[right];

                if (sum == target) {

                    List<Integer> pair =
                            new ArrayList<>(current);

                    pair.add(arr[left]);
                    pair.add(arr[right]);

                    result.add(pair);

                    left++;
                    right--;

                    // Skip duplicate left values
                    while (left < right &&
                            arr[left] == arr[left - 1]) {

                        left++;
                    }

                    // Skip duplicate right values
                    while (left < right &&
                            arr[right] == arr[right + 1]) {

                        right--;
                    }

                } else if (sum < target) {

                    left++;

                } else {

                    right--;
                }
            }

            return;
        }


        // Fix one element
        for (int i = start; i <= arr.length - k; i++) {

            // Skip duplicate values
            if (i > start && arr[i] == arr[i - 1]) {
                continue;
            }

            // Add selected element
            current.add(arr[i]);

            // Recursive call
            K_SumHelper(
                    arr,
                    target - arr[i],
                    k - 1,
                    i + 1,
                    current,
                    result
            );

            // Backtracking
            current.remove(current.size() - 1);
        }
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String choice;

        do {

            // Ask for array length
            System.out.println("Enter the array length:");
            int arrayLength = sc.nextInt();

            // Validate array length
            if (arrayLength <= 0) {

                System.out.println(
                        "Entered number should be greater than 0."
                );

                System.out.println(
                        "Do you want to try again? "
                                + "Enter 'yes' to continue, "
                                + "or anything else to exit:"
                );

                choice = sc.next();

                // Skip the remaining code of this iteration
                continue;
            }


            // Create array
            int[] arr = new int[arrayLength];

            // Enter array elements
            System.out.println(
                    "Enter the elements in the array:"
            );

            for (int i = 0; i < arrayLength; i++) {
                arr[i] = sc.nextInt();
            }


            // Enter target
            System.out.println("Enter the target sum:");
            int target = sc.nextInt();


            // Enter K
            System.out.println(
                    "Enter the value of K "
                            + "(number of elements in the sum):"
            );

            int kValueSum = sc.nextInt();


            // Validate K
            if (kValueSum < 2 || kValueSum > arrayLength) {

                System.out.println(
                        "K must be between 2 and "
                                + arrayLength
                );

            } else {

                // Call K-Sum
                List<List<Integer>> result =
                        K_Sum(arr, target, kValueSum);

                System.out.println(
                        "The possible "
                                + kValueSum
                                + "-Sum combinations are : "
                                + result
                );
            }


            // Ask whether user wants to try again
            System.out.println(
                    "Do you want to try again? "
                            + "Enter 'yes' to continue, "
                            + "or anything else to exit:"
            );

            choice = sc.next();

        } while (choice.equalsIgnoreCase("yes"));
        sc.close();
    }
}
