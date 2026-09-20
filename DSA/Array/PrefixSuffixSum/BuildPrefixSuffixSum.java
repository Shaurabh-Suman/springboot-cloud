package Array.PrefixSuffixSum;

public class BuildPrefixSuffixSum {
    private static int[] buildPrefixSum(int[] arr) {
        int[] prefix=new int[arr.length];
        prefix[0]=arr[0];
        for(int i=1;i<arr.length;i++){
            // Add current element to the sum calculated so far from LEFT to RIGHT
            prefix[i]=prefix[i-1]+arr[i];
        }
        return prefix;
    }
    private static int[] buildSuffixSum(int[] arr) {
        int[] suffix=new int[arr.length];
        suffix[arr.length-1]=arr[arr.length-1];
        for(int i=arr.length-2;i>=0;i--){
            // Add current element to the sum calculated so far from RIGHT to LEFT
            suffix[i]=suffix[i+1]+arr[i];
        }
        return suffix;
    }
    public static void main(String[] args) {
    int[] arr= {3, 2, 5, 1, 4};
    System.out.println("Original Array: ");
    for (int num : arr) {
        System.out.print(num + "  ");
    }
    System.out.print(System.lineSeparator());
    System.out.println("Required Prefix Sum: ");
        for(int num : buildPrefixSum(arr)) {
            System.out.print(num + "  ");
        }

        System.out.print(System.lineSeparator());
        System.out.println("Required Suffix Sum: ");
        for (int num : buildSuffixSum(arr)) {
            System.out.print(num + "  ");
        }
   }
}
