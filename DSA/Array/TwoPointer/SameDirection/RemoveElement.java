package Array.TwoPointer.SameDirection;

public class RemoveElement {
    private static int removeElement(int[] arr, int removeElement) {
        if (arr.length == 0) return 0;
        int slow=0;
        for(int fast=0;fast<arr.length;fast++){
            if(arr[fast]!=removeElement){
                arr[slow]=arr[fast];
                slow++;
            }
        }
        return slow;
    }
    public static void main(String[] args) {
        int[] arr = {3, 2, 2, 3, 4};
        int removeElement=3;

        int newLength = removeElement(arr, removeElement);

        System.out.println("New Length: " + newLength);
        System.out.print("Modified Array: ");
        for (int i = 0; i < newLength; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
