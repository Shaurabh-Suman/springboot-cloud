package Array.SlidingWindow.AdvanceSlidingWindow;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/*
Remember:

Sliding Window Maximum → Decreasing Deque.

Sliding Window Minimum → Increasing Deque.
 */
public class SlidingWindowMinimum {

    public static int[] minSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }

        int n = nums.length;
        int[] result = new int[n - k + 1];
        int resIndex = 0;

        // Deque stores *indices*, maintaining an INCREASING order of their values
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            // 1. Remove indices that have fallen out of the current window (i - k + 1)
            if (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }

            // 2. Remove elements from the back that are GREATER than the current element
            // They are useless because the current element is smaller and will stay longer in the window
            while (!deque.isEmpty() && nums[deque.peekLast()] > nums[i]) {
                deque.pollLast();
            }

            // 3. Add the current element's index to the back
            deque.offerLast(i);

            // 4. The front of the deque always holds the index of the minimum element
            if (i >= k - 1) {
                result[resIndex++] = nums[deque.peekFirst()];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        System.out.println(Arrays.toString(minSlidingWindow(nums, k)));
        // Output: [-1, -3, -3, -3, 3, 6]
    }
}

/*
why the order changes using the same example.

The only difference is:
- Sliding Window Maximum: We want the biggest number.
- Sliding Window Minimum: We want the smallest number.
1. Sliding Window Maximum → Decreasing Deque
Suppose:
int[] nums = {2, 5, 1, 4};
int k = 3;


Our first window is:
[2, 5, 1]


We want the maximum, which is 5.

Let's build our Deque:

Step 1: Add 2.
Deque: [2]


Step 2: Add 5.

Compare 5 with 2.

Is 5 bigger than 2? Yes.

So remove 2 because 5 is a better maximum candidate.
Deque: [5]


Step 3: Add 1.

Is 1 bigger than 5? No.

Keep both.
Deque: [5, 1]


Decreasing order
5 > 1


The biggest element is always at the front.

When the next window arrives, we can quickly get its maximum from the front.
2. Sliding Window Minimum → Increasing Deque
Now use exactly the same array:
int[] nums = {2, 5, 1, 4};
int k = 3;


First window:
[2, 5, 1]


This time, we want the minimum, which is 1.

Let's build our Deque:

Step 1: Add 2.
Deque: [2]


Step 2: Add 5.

Is 5 smaller than 2? No.

Keep both.
Deque: [2, 5]


Step 3: Add 1.

Compare 1 with 5.

Is 1 smaller than 5? Yes. Remove 5.

Compare 1 with 2.

Is 1 smaller than 2? Yes. Remove 2.

Now add 1.
Deque: [1]


Increasing order
1


The smallest element is always at the front.
********************************************
3. Compare both approaches
Same input: [2, 5, 1]
Maximum
5
Deque values
[5, 1]
Decreasing order


Minimum
1
Deque values
[1]
Increasing order
************************************

4. The actual logic in Java
For maximum:
while (!deque.isEmpty() &&
       nums[deque.peekLast()] < nums[i]) {
    deque.pollLast();
}


Remove smaller elements because we want the biggest.

For minimum:
while (!deque.isEmpty() &&
       nums[deque.peekLast()] > nums[i]) {
    deque.pollLast();
}


Remove bigger elements because we want the smallest.

Remember this simple rule:
- Maximum → remove smaller candidates → decreasing Deque.
- Minimum → remove bigger candidates → increasing Deque.
The reason is simple: Whatever answer you want, keep that best candidate at the front.
 */