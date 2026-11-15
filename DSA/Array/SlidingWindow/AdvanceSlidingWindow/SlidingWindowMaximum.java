package Array.SlidingWindow.AdvanceSlidingWindow;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Arrays;
/*
Classic Sliding Window Maximum problem using a Monotonic Deque (Double-Ended Queue) in Java.

Problem Overview
Given an array of integers nums and a sliding window of size k that moves from the very left of the array to the very right, return the maximum element in each sliding window.
 */

public class SlidingWindowMaximum {

    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }

        int n = nums.length;
        int[] result = new int[n - k + 1];
        int resIndex = 0;

        // Deque stores the *indices* of elements, maintaining a decreasing order of their values
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            // 1. Remove indices that have fallen out of the current window (i - k + 1)
            if (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }

            // 2. Remove elements from the back of the deque that are smaller than the current element
            // They are useless because the current element is larger and will stay longer in the window
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            // 3. Add the current element's index to the back
            deque.offerLast(i);

            // 4. The front of the deque always holds the index of the maximum element for the current window
            // Record the result once we have built a full window of size k
            if (i >= k - 1) {
                result[resIndex++] = nums[deque.peekFirst()];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        System.out.println(Arrays.toString(maxSlidingWindow(nums, k)));
        // Output: [3, 3, 5, 5, 6, 7]
    }
}
