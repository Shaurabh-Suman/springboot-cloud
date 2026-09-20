package Array.PrefixSuffixSum;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CountSubarraysWithSumK {
    private static int countSubarraysWithSumK(int[] arr, int sum) {
        // prefixSum -> how many times this prefix sum has appeared
        Map<Integer, Integer> map = new HashMap<>();

        // Empty prefix before index 0
        map.put(0, 1);

        int count=0;
        int prefixSum =0;
        for(int i = 0; i < arr.length; i++){
            // Current prefix sum
            prefixSum +=arr[i];
            /*
             1  We want a subarray ending at index i with sum K.
             2. currentPrefix - previousPrefix = K
             3. Therefore:
             4. previousPrefix = currentPrefix - K
             */
            int requiredPrefix =prefixSum -sum;

            // Have we seen that previous prefix before?
            count +=map.getOrDefault(requiredPrefix ,0);

            // Now store the current prefix for future indices
            map.put(prefixSum,map.getOrDefault(prefixSum+1,0)+1);
        }
         return count;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the sum: ") ;
        int sum = sc.nextInt();
        //sum=4;

        int[] arr = {2, 3, 1, 4};
        // int[] arr = {3, 2, 1, 4};
        System.out.println("Required count is: " +  countSubarraysWithSumK(arr, sum));

    }
}
