package Array.PrefixSuffixSum;

import java.util.Arrays;

public class DifferenceArray {

    public static int[] rangeUpdates(
            int n,
            int[][] updates) {

        int[] diff = new int[n + 1];

        // Mark every range update
        for (int[] update : updates) {

            int start = update[0];
            int end = update[1];
            int value = update[2];

            diff[start] += value;
            diff[end + 1] -= value;
        }

        // Prefix sum reconstructs final array
        int[] result = new int[n];

        int current = 0;

        for (int i = 0; i < n; i++) {

            current += diff[i];

            result[i] = current;
        }

        return result;
    }


    public static void main(String[] args) {

        int n = 5;

        // {start, end, value}
        int[][] updates = {
                {1, 3, 5}
        };

        System.out.println(
                Arrays.toString(
                        rangeUpdates(n, updates)
                )
        );
    }
}
/*
Core logic

Difference Array is mainly used when you have many range updates.

Instead of doing:

Add 5 to every element from index 2 to 5

we mark only the start and end+1:

diff[start] += value
diff[end + 1] -= value

Then a prefix sum reconstructs the final array.

Example

Start:

arr = [0, 0, 0, 0, 0]

Operation:

Add 5 to indexes 1 → 3

Instead of:

arr[1] += 5
arr[2] += 5
arr[3] += 5

we do:

diff[1] += 5
diff[4] -= 5
diff = [0, 5, 0, 0, -5]

Now prefix sum:

0
0 + 5 = 5
5 + 0 = 5
5 + 0 = 5
5 - 5 = 0

Result:

[0, 5, 5, 5, 0]
 */


/*
Output:

[0, 5, 5, 5, 0]
Backbone
Range Update
     ↓
Mark START       +value
Mark END + 1     -value
     ↓
Prefix Sum
     ↓
Final Array

This is extremely useful when there are many range updates.
 */