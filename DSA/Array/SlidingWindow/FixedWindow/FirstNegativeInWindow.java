package Array.SlidingWindow.FixedWindow;

import java.util.Deque;
import java.util.LinkedList;

public class FirstNegativeInWindow {
    private static void printFirstNegative(int[] arr, int k) {
        // Guard clause
        if (arr == null || k <= 0 || k > arr.length) {
            System.out.println("Invalid input!");
            return;
        }

        // Deque to store indices of negative numbers in the current window

        Deque<Integer> dq = new LinkedList<>();
        //Deque<Integer> dq = new ArrayDeque<>();

        // 1. Process the first window of size K
        for (int i = 0; i < k; i++) {
            if(arr[i] < 0) {
                dq.addLast(i);
            }
        }
        // Print result for the first window
        if (!dq.isEmpty()) {
           System.out.println(arr[dq.peekFirst()]+ " ");
        }else  {
            System.out.print("0 "); // 0 indicates no negative number in this window
        }

        // 2. Slide the window across the rest of the array
        for(int rightIndex=k; rightIndex<arr.length;rightIndex++) {
            // Remove element from queue if it is outside the current window
            while (!dq.isEmpty() && dq.peekFirst() <= rightIndex - k) {
                dq.removeFirst();
            }

            // Add index of incoming element if it is negative
            if(arr[rightIndex] < 0) {
                dq.addLast(rightIndex);
            }
            // Print result for the current window
            if(!dq.isEmpty()) {
                System.out.print(arr[dq.peekFirst()] + " ");
            }else  {
                System.out.print("0 "); // 0 indicates no negative number in this window
            }
        }
       System.out.println();
    }
    public static void main(String[] args) {
        int[] arr = {12, -1, -7, 8, -15, 30, 16, 28};
        int k = 3;

        System.out.print("First negative integer in every window of size " + k + ": ");
        printFirstNegative(arr, k);
    }
}
