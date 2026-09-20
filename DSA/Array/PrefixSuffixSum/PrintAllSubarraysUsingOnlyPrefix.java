package Array.PrefixSuffixSum;


import java.util.Arrays;

public class PrintAllSubarraysUsingOnlyPrefix {
    private static void printAllSubarrays(int[] arr) {
        int[] prefixSum = new int[arr.length];
        prefixSum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + arr[i];
        }
        for(int start=0;start<arr.length;start++){
            for(int end=start;end<arr.length;end++){
                int sum=0;
               if(start==0){
                   sum=prefixSum[end];
               }else{
                   sum=prefixSum[end]-prefixSum[start-1];
               }
               /*
               Step 1 — Arrays.copyOfRange()
Arrays.copyOfRange(arr, start, end + 1)

becomes:

Arrays.copyOfRange(arr, 0, 2)

Remember: the second index is exclusive.

So it takes:

index:   0   1   2   3
array:  [2,  3,  1,  4]
         ↑   ↑
         └───┘

Result:

[2, 3]
                */
                System.out.println(
                        Arrays.toString(
                                Arrays.copyOfRange(arr, start, end + 1)
                        ) + " -> sum = " + sum
                );
            }
        }
    }
    public static void main(String[] args) {
        int[] arr = {2, 3, 1, 4};
        // int[] arr = {3, 2, 1, 4};
        printAllSubarrays(arr);
    }
}
