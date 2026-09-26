package Array.SlidingWindow.FixedWindow;

import java.util.ArrayDeque;
import java.util.Deque;

public class MaxInEveryWindow {
    public static void printMax(int[] arr, int k) {

        // Guard clause
        if (arr == null || k <= 0 || k > arr.length) {
            System.out.println("Invalid input!");
            return;
        }

        // Stores indices.
        // Values are maintained in decreasing order.
        Deque<Integer> dq = new ArrayDeque<>();

        for (int rightIndex = 0; rightIndex < arr.length; rightIndex++) {

            // 1. Remove indices outside the current window
            while (!dq.isEmpty()
                    && dq.peekFirst() <= rightIndex - k) {

                dq.removeFirst();
            }

            // 2. Remove smaller elements from the back
            while (!dq.isEmpty()
                    && arr[dq.peekLast()] <= arr[rightIndex]) {

                dq.removeLast();
            }

            // 3. Add current element's index
            dq.addLast(rightIndex);

            // 4. Window has reached size K
            if (rightIndex >= k - 1) {

                // Front always contains index of maximum
                System.out.print(arr[dq.peekFirst()] + " ");
            }
        }

        System.out.println();
    }

    public static void main(String[] args) {

        int[] arr = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;

        System.out.print(
                "Maximum element in every window of size "
                        + k + ": "
        );

        printMax(arr, k);
    }
}

/*
Maximum element in every window of size 3: 3 3 5 5 6 7
 */