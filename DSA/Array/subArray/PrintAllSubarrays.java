package Array.subArray;

/*
First, understand how we generate subarrays.
1. Imagine an array of 3 boxes
Index:    0     1     2
        ┌─────┬─────┬─────┐
Array:  │  10 │  20 │  30 │
        └─────┴─────┴─────┘


Our job is to print every possible continuous group of elements.

There are two decisions:
1. Where should my subarray START?
2. Where should my subarray END?
That's why we need two loops.
2. First decision: Choose the starting position
Suppose we choose index 0.
        ┌─────┬─────┬─────┐
Array:  │  10 │  20 │  30 │
        └─────┴─────┴─────┘
          ↑
        START


Now our subarray must start from 10.

What possible subarrays can we create?
- Start at 10, end at 10 → [10]
- Start at 10, end at 20 → [10, 20]
- Start at 10, end at 30 → [10, 20, 30]
So we have:
[10]
[10, 20]
[10, 20, 30]


Notice something important: We keep the starting position fixed and move only the ending position.
3. Now change the starting position
Move START from index 0 to index 1.
        ┌─────┬─────┬─────┐
Array:  │  10 │  20 │  30 │
        └─────┴─────┴─────┘
                ↑
              START


Now we cannot go backwards to 10.

Our subarrays are:
[20]
[20, 30]


Again, starting position stays fixed while ending position moves forward.
4. Finally, starting position = 2
        ┌─────┬─────┬─────┐
Array:  │  10 │  20 │  30 │
        └─────┴─────┴─────┘
                      ↑
                    START


Only one subarray is possible:
[30]

5. Now connect this concept to Java
int[] arr = {10, 20, 30};

for (int start = 0; start < arr.length; start++) {

    for (int end = start; end < arr.length; end++) {

        for (int i = start; i <= end; i++) {
            System.out.print(arr[i] + " ");
        }

        System.out.println();
    }
}


Focus only on this line:
for (int end = start; end < arr.length; end++)

***************************************************************************
Why end = start?

Because the ending position must begin at the starting position.

For example:
start	Possible end values
0	    0, 1, 2
1	    1, 2
2	    2
****************************************************************************
This generates all possible subarrays without going backwards.

***Fix the starting index, then move the ending index.
 */
public class PrintAllSubarrays {

    public static void main(String[] args) {

        int[] arr = {1, 2, 3};

        // Select the starting index
        for (int start = 0; start < arr.length; start++) {

            // Select the ending index
            for (int end = start; end < arr.length; end++) {

                // Print elements between start and end
                for (int i = start; i <= end; i++) {
                    System.out.print(arr[i] + " ");
                }

                System.out.println();
            }
        }
    }
}
/*
How It WorksOuter Loop (start): Fixes the starting index of the subarray.Middle Loop (end): Fixes the ending index of the subarray, expanding it one element to the right with each iteration.Inner Loop (i): Iterates from start to end to print the elements inside the current subarray boundary.For an array of size $n$, this approach generates $\frac{n(n + 1)}{2}$ total subarrays.Complexity AnalysisTime Complexity: O(n^3)There are two loops to choose the bounds (O(n^2) pairs), and the inner loop takes up to O(n) time to print/process each element. (If you remove the inner printing loop and just accumulate sums, it drops to O(n^2)).Space Complexity: O(1) auxiliary space, as it only uses variables for iteration.Why Sliding Window ExistsWhile this brute-force method checks every single possible subarray, Sliding Window optimizes problems where we care about a specific condition (like a sum, target character count, or distinct elements). Instead of starting over for every start index, a sliding window slides its boundaries dynamically in O(n)$ time.
 */
