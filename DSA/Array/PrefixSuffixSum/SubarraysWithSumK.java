package Array.PrefixSuffixSum;

import java.util.*;

public class SubarraysWithSumK {
    private static void getSubarraysWithSumK(int[] arr, int sum) {
        // prefixSum -> all indexes where this prefix sum occurred
        Map<Integer, List<Integer>> map = new HashMap<>();

        // Empty prefix before index 0
        // It represents a virtual index -1
        map.put(0, new ArrayList<>());
        map.get(0).add(-1);

        int prefixSum = 0;

        for(int i = 0; i < arr.length; i++) {
            // Calculate current prefix sum
            prefixSum += arr[i];

             /*
             1  We want a subarray ending at index i with sum K.
             2. currentPrefix - previousPrefix = K
             3. Therefore:
             4. previousPrefix = currentPrefix - K
             */
            int requiredPrefix = prefixSum - sum;

            // Have we seen this required prefix before?
            if (map.containsKey(requiredPrefix)){

                // Get ALL previous indexes having this prefix sum
               List<Integer> previousIndexes =map.get(requiredPrefix);
               for(int previousIndex: previousIndexes){
                   int startIndex = previousIndex+1;
                   int endIndex = i;
                   System.out.println(
                           "Subarray: " +
                                   Arrays.toString(
                                           Arrays.copyOfRange(
                                                   arr,
                                                   startIndex,
                                                   endIndex + 1
                                           )
                                   )
                   );
               }
           }
            // Store the current prefix sum and its index
            map.put(prefixSum, new ArrayList<>());
            map.get(prefixSum).add(i);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the sum: ") ;
        int sum = sc.nextInt();
        //sum=4;

        int[] arr = {2, 3, 1, 4};
        // int[] arr = {3, 2, 1, 4};
        getSubarraysWithSumK(arr, sum);

    }
}
