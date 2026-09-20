package Array.PrefixSuffixSum;

public class FindPivotIndex {
    private static int findPivotIndex(int[] arr) {
        if(arr.length == 0) return -1;
        int totalSum = 0;
        for(int num : arr){
            totalSum += num;
        }
        int leftSum = 0;
        int pivotIndex = -1;
        for(int i = 0; i < arr.length; i++){
           int rightSum=totalSum-leftSum-arr[i];
            if(leftSum==rightSum){
                pivotIndex = i;
            }
            leftSum+=arr[i];
        }
        return pivotIndex;
    }
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 2, 2};
        System.out.println("Required pivot index is :  " + findPivotIndex(arr)); // Output: 49
    }
}
