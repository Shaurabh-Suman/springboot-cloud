package Array.PrefixSuffixSum;

import java.util.Arrays;

public class PrefixSuffixMaximum {

    public static int[] prefixMaximum(int[] arr) {

        int[] prefixMax = new int[arr.length];

        prefixMax[0] = arr[0];

        for (int i = 1; i < arr.length; i++) {

            prefixMax[i] =
                    Math.max(prefixMax[i - 1], arr[i]);
        }

        return prefixMax;
    }


    public static int[] suffixMaximum(int[] arr) {

        int n = arr.length;

        int[] suffixMax = new int[n];

        suffixMax[n - 1] = arr[n - 1];

        for (int i = n - 2; i >= 0; i--) {

            suffixMax[i] =
                    Math.max(suffixMax[i + 1], arr[i]);
        }

        return suffixMax;
    }


    public static void main(String[] args) {

        int[] arr = {4, 2, 7, 1, 5};

        System.out.println(
                "Prefix Max: " +
                        Arrays.toString(prefixMaximum(arr))
        );

        System.out.println(
                "Suffix Max: " +
                        Arrays.toString(suffixMaximum(arr))
        );
    }
}
/*
Core logic

At every index, we want to know:

What is the maximum value on my LEFT?

or

What is the maximum value on my RIGHT?

So we build:

Prefix Maximum → maximum from LEFT → current index

Suffix Maximum → maximum from RIGHT → current index

Example:

arr = [4, 2, 7, 1, 5]

Prefix maximum:

index:     0  1  2  3  4
arr:       4  2  7  1  5
prefixMax: 4  4  7  7  7

Suffix maximum:

suffixMax: 7  7  7  5  5
 */


/*
Output:

Prefix Max: [4, 4, 7, 7, 7]
Suffix Max: [7, 7, 7, 5, 5]

Complexity:

Time  = O(N)
Space = O(N)

This technique becomes especially useful in problems like Trapping Rain Water.
 */