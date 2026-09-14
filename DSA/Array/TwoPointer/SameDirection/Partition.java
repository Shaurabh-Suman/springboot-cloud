package Array.TwoPointer.SameDirection;

public class Partition {

    private static void partitionUsingTwoPointers(int[] arr, int pivot) {
        int slow = 0;
        for(int fast = 0; fast < arr.length; fast++){
            if(arr[fast] < pivot){
                int temp = arr[fast];
                arr[fast] = arr[slow];
                arr[slow] = temp;
                slow++;
            }
        }
    }
    public static void main(String[] args) {
        int[] arr = {8, 3, 7, 2, 5, 1};
        //Put all elements < 5 on the left and the rest on the right.
        int pivot=5;

        partitionUsingTwoPointers(arr, pivot);

        System.out.print("Modified Array: ");
        for (int j : arr) {
            System.out.print(j + " ");
        }
    }
}
