package Array.subArray;

/*
The Hard Truth About Printing All SubarraysNo matter how you write the code,
printing every single subarray will always require at least O(n^2) or O(n^3) time.
 */
/*
To print all subarrays, think of two things:
1. How many elements should the subarray contain?
2. Where should that subarray start and end?

Step 1: Understand the problem
Suppose:
int[] arr = {10, 20, 30, 40};


All possible subarrays can be grouped by their size:
Subarrays of size 1
[10]  [20]  [30]  [40]



Subarrays of size 2
[10, 20]  [20, 30]  [30, 40]



Subarrays of size 3
[10, 20, 30]  [20, 30, 40]



Subarrays of size 4
[10, 20, 30, 40]



Notice something?
- First, we print all subarrays of size 1.
- Then, all subarrays of size 2.
- Then, all subarrays of size 3.
- Finally, all subarrays of size 4.
That's why we need the first loop.



Step 2: Why k starts from 1 and goes to n
for (int k = 1; k <= n; k++)


Here, k represents the number of elements in the subarray.

For our array, n = 4.

So:
k	Meaning
1	Subarray contains 1 element
2	Subarray contains 2 elements
3	Subarray contains 3 elements
4	Subarray contains 4 elements

We start from 1 because a subarray must contain at least one element.

We stop at n because a subarray cannot contain more elements than the original array.
Step 3: Now, slide the window
Let's fix:
k = 2;


Meaning: We want subarrays containing exactly 2 elements.

Our array:
Index:  0    1    2    3
Value: 10   20   30   40


We want to find every group of 2 consecutive elements.

Step 4: Understand the second loop
for (int i = k - 1; i < n; i++)


We already fixed k = 2.

Why does i start at k - 1?

Because i represents the rightmost index of our window.

For a window of size 2, the first possible window is:
[10, 20]
  0   1


Its rightmost index is 1.

And:
k - 1 = 2 - 1 = 1


That's why we start at index 1.

Then i moves:
i = 1  → [10, 20]
i = 2  → [20, 30]
i = 3  → [30, 40]


We stop before n because the last valid index is n - 1.

So remember the logic, not the code:
The window's right boundary starts at the first position where a complete window can fit, then moves one position at a time until the array ends.

Step 5: Where does left = i - k + 1 come from?
This is the most important formula. Let's derive it instead of memorising it.

We know:
- i = rightmost index.
- k = number of elements in the window.
- left = first index of the window.
For example:
Index:  0    1    2    3
Value: 10   20   30   40

Window:       [20, 30]
Index:          1   2


Here:
i = 2;
k = 2;


We know the right index is 2. We need to move backwards by one position to get the left index.

Formula:
left = i - k + 1;

left = 2 - 2 + 1;

left = 1;


Why +1?

Because the window includes both the left and right indexes.

For example, indexes 1 and 2 contain exactly two elements:
2 - 1 + 1 = 2 elements


Therefore:
\[
\text{Window size} = right-left+1
\]

Rearranging:
\[
\boxed{left = right-k+1}
\]

And since right = i:
\[
\boxed{left = i-k+1}
\]

You have now derived the formula!
Step 6: Why do we need the third loop?
for (int j = left; j <= right; j++) {
    System.out.print(arr[j] + " ");
}


The second loop identifies the window boundaries. The third loop actually prints the elements inside those boundaries.

For example:
left = 1;
right = 2;


The third loop does:
j	What gets printed
1	arr[1] = 20
2	arr[2] = 30
3	Stop, because 3 > right

Output:
20 30
 */
public class SlidingWindowAllSubarrays {

    public static void printAllSubarraysUsingSlidingWindow(int[] arr) {
        int n = arr.length;

        // Outer loop: Vary the window size 'k' from 1 to n
        for (int k = 1; k <= n; k++) {
            System.out.println("--- Subarrays of size " + k + " ---");

            // Slide the window of size k across the array
            // 'i' represents the right boundary of the window
            for (int i = k - 1; i < n; i++) {
                int left = i - k + 1;  // Left boundary of the window
                int right = i;         // Right boundary of the window

                // Print the current sliding window elements
                System.out.print("[ ");
                for (int j = left; j <= right; j++) {
                    System.out.print(arr[j] + " ");
                }
                System.out.println("]");
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        printAllSubarraysUsingSlidingWindow(arr);
    }
}