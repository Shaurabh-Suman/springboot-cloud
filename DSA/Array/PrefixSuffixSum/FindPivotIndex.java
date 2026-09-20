package Array.PrefixSuffixSum;

public class FindPivotIndex {
    private static int findPivotIndex(int[] arr) {
        if(arr.length == 0) return -1;
        int totalSum = 0;
        for(int num : arr){
            totalSum += num;
        }
        int leftSum = 0;
       // int pivotIndex = -1;
        // Try every index as a candidate pivot
        for(int i = 0; i < arr.length; i++){
            // Remove LEFT and CURRENT from TOTAL.
            // What remains is the RIGHT sum.
            int rightSum=totalSum-leftSum-arr[i];

            // Is the current index a pivot?
            if(leftSum==rightSum){
                /*
                pivotIndex = i;
                return pivotIndex;
                 */
                return i;
            }
            // Current element now becomes part of the LEFT side
            leftSum+=arr[i];
        }
        // No pivot index found
       // return pivotIndex;
        return -1;
    }
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 2, 2};
        System.out.println("Required pivot index is :  " + findPivotIndex(arr)); // Output: 49
    }
}
