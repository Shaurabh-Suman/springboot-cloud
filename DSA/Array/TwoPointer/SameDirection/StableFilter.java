package Array.TwoPointer.SameDirection;

public class StableFilter {
    private static int stableFilter(int[] arr) {
        int slow = 0;
        for (int fast = 0; fast < arr.length; fast++) {
            if (arr[fast] % 2 == 0) {
                arr[slow] = arr[fast];
                slow++;
            }
        }
        return slow;
    }

    public static void main(String[] args) {
        int[] arr = {5, 2, 8, 3, 4, 7, 6};
        //Keep the elements that satisfy a condition and preserve their original order.
        /*
        output:- [2, 8, 4, 6]
         */
        int newLength = stableFilter(arr);

        System.out.print("Modified Array: ");

        for (int i = 0; i < newLength; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
