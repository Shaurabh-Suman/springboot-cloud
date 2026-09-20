package Array.PrefixSuffixSum;

import java.util.Arrays;

public class ProductExceptSelf {

    public static int[] productExceptSelf(int[] arr) {

        int n = arr.length;
        int[] answer = new int[n];

        // Step 1: Store product of elements to the LEFT
        answer[0] = 1;

        for (int i = 1; i < n; i++) {
            answer[i] = answer[i - 1] * arr[i - 1];
        }

        // Step 2: Multiply by product of elements to the RIGHT
        int suffixProduct = 1;

        for (int i = n - 1; i >= 0; i--) {

            answer[i] = answer[i] * suffixProduct;

            suffixProduct *= arr[i];
        }

        return answer;
    }

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4};

        System.out.println(
                Arrays.toString(productExceptSelf(arr))
        );
    }
}
/*
Output:

[24, 12, 8, 6]

Complexity:

Time  = O(N)
Space = O(1) extra
 */


/*
Because **Product Except Self** means:

> For each index, multiply every element except the element at that index.

Given:


arr = [1, 2, 3, 4]

Let's calculate each position manually.

### Index 0

Element is `1`.

Exclude `1`:

2 × 3 × 4 = 24


So:

answer[0] = 24


### Index 1

Element is `2`.

Exclude `2`:

1 × 3 × 4 = 12


So:

answer[1] = 12

### Index 2

Element is `3`.

Exclude `3`:

1 × 2 × 4 = 8

So:

answer[2] = 8

### Index 3

Element is `4`.

Exclude `4`:

1 × 2 × 3 = 6

So:

answer[3] = 6

Therefore:


arr     = [1,  2,  3,  4]
           ↓   ↓   ↓   ↓
answer  = [24, 12,  8,  6]


### The core logic

For every index:


answer[i]
=
product of everything LEFT
×
product of everything RIGHT


For example, for `3`:

[1, 2] [3] [4]
  ↓     ↓    ↓
left  exclude right

1 × 2 × 4 = 8

So **`[24, 12, 8, 6]` is simply the product of all other elements for each position.**

 */