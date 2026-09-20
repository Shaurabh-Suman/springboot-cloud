package Array.PrefixSuffixSum;

import java.util.Scanner;

public class RangeSum {
    private static int rangeSum(int[] arr, int left, int right) {
        // 1.Build prefix sum array to avoid calculating the range sum repeatedly
        int[] prefixSumArr=buildPrefixSum(arr);
        // If range starts from index 0, prefix[right] itself is the required sum
            if(left==0) {
                return prefixSumArr[right];
            }
        // Subtract the sum before 'left' from the total sum up to 'right'
        return prefixSumArr[right]-prefixSumArr[left-1];
    }
    private static int[] buildPrefixSum(int[] arr) {
        int[] prefix=new int[arr.length];
        prefix[0]=arr[0];
        for(int i=1;i<arr.length;i++){
            // Add current element to the sum calculated so far from LEFT to RIGHT
            prefix[i]=prefix[i-1]+arr[i];
        }
        return prefix;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int[] arr= {3, 2, 5, 1, 4};

        System.out.println("Original Array: ");
        for (int num : arr) {
            System.out.print(num + "  ");
        }
        System.out.print(System.lineSeparator());
        System.out.println("Enter the range of indexes between which you want to find the sum:");
        System.out.print("Enter 1st i.e, but not more than " + (arr.length-1) + ":");
        int left=sc.nextInt();
        System.out.print("Enter 2nd i.e, left index, but not more than " + (arr.length-1) + ":");
        int right=sc.nextInt();
        if(left>arr.length-1 || right>arr.length-1) {
            System.out.println("Invalid Input");
            return;
        }
        System.out.print(System.lineSeparator());
        System.out.println("Required Range Sum: " +  rangeSum(arr,left,right));
    }
}
