package Array.PrefixSuffixSum;

public class PrefixSum2D {

    public static int[][] buildPrefixSum(int[][] matrix) {

        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] prefix = new int[rows + 1][cols + 1];

        for (int r = 1; r <= rows; r++) {

            for (int c = 1; c <= cols; c++) {

                prefix[r][c] =
                        matrix[r - 1][c - 1]
                                + prefix[r - 1][c]
                                + prefix[r][c - 1]
                                - prefix[r - 1][c - 1];
            }
        }

        return prefix;
    }


    public static int getRegionSum(
            int[][] prefix,
            int r1,
            int c1,
            int r2,
            int c2) {

        return prefix[r2 + 1][c2 + 1]
                - prefix[r1][c2 + 1]
                - prefix[r2 + 1][c1]
                + prefix[r1][c1];
    }


    public static void main(String[] args) {

        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        int[][] prefix = buildPrefixSum(matrix);

        // Sum rectangle:
        // (row 1, col 1) → (row 2, col 2)
        //
        // 5 6
        // 8 9
        //
        // Answer = 28

        int sum = getRegionSum(
                prefix,
                1, 1,
                2, 2
        );

        System.out.println("Region Sum = " + sum);
    }
}
/*
This is the same Prefix Sum idea, but for a matrix.

Core logic

For a 1D array:

prefix[i] = sum from 0 → i

For a 2D matrix:

prefix[i][j]
=
sum of everything from
top-left (0,0)
to (i,j)

Example:

matrix:

1  2  3
4  5  6
7  8  9

2D prefix sum:

1   3   6
5  12  21
12 21  45

For example:

prefix[1][1] = 12

because:

1 + 2
4 + 5
-----
 12
 */


/*
Output:

Region Sum = 28
The formula to remember
        c1       c2
         ↓        ↓
    ┌───────────────┐
r1  │       +       │
    │               │
r2  │               │
    └───────────────┘

Using the 2D prefix:

Region Sum
=
bottom-right
- area above
- area left
+ overlapping area

The + at the end is important because the top-left overlapping area was subtracted twice.
 */