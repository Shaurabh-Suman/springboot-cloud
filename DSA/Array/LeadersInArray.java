package Array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class LeadersInArray{

    // Function to find the leaders in an array
    static ArrayList<Integer> leaders(int[] arr) {
        ArrayList<Integer> result = new ArrayList<>();
        int n = arr.length;
        // Start with the rightmost element
        int maxRight = arr[n - 1];

        // Rightmost element is always a leader
        result.add(maxRight);

        // Traverse the array from right to left
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] >= maxRight) {
                maxRight = arr[i];
                result.add(maxRight);
            }
        }

        // Reverse the result list to maintain
        // original order
        Collections.reverse(result);

        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the length of the array.");
        int arrLen = sc.nextInt();
        int[] arr = new int[arrLen];
       // int[] arr = { 16, 17, 4, 3, 5, 2 };
        System.out.println("Enter the elements of the array.");
        for (int i = 0; i < arrLen; i++) {
            System.out.println("Enter the element " + (i + 1) + ".");
            arr[i] = sc.nextInt();
        }
        ArrayList<Integer> result = leaders(arr);
        System.out.println("The leaders in array are:");
        for (int res : result) {
            System.out.print(res + " ");
        }
        System.out.println();
        sc.close();
    }
}