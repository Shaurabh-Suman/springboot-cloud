package Array.TwoPointer;

import java.util.*;

public class TwoSumUsingHashMap {
    private static List<List<Integer>> twoSumUsingHashMap(int[] arr, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for(int num: arr){
            int complement = target - num;
            if(map.containsKey(complement)){
               result.add(Arrays.asList(num, complement));
            }
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String choice;

        do {
            System.out.println("Enter the array length:");
            int arrayLength = sc.nextInt();

            if (arrayLength <= 0) {
                System.out.println("Entered number should be greater than 0.");
                System.out.println(
                        "Do you want to try again? Enter 'yes' to continue, or anything else to exit:"
                );

                choice = sc.next();
                continue; //If the input was invalid. Don't execute the remaining code in this iteration. Start the next iteration of the do-while loop
            }

            int[] arr = new int[arrayLength];

            System.out.println("Enter the elements in the array:");

            for (int i = 0; i < arrayLength; i++) {
                arr[i] = sc.nextInt();
            }

            System.out.println("Enter the target:");
            int target = sc.nextInt();

            List<List<Integer>> result = twoSumUsingHashMap(arr, target);

            System.out.println("Pairs: " + result);

            System.out.println("Do you want to try again? Enter 'yes' to continue, or anything else to exit:");
            choice = sc.next();

        } while (choice.equalsIgnoreCase("yes"));

        sc.close();
    }
}