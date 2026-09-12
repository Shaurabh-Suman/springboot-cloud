package Array.TwoPointer;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class BinaryArraySegregation {
    private static int[] segregate(int[] arr) {
       int left = 0;
       int right = arr.length - 1;
       while(left < right){
           while(left < right && arr[left] == 0) left++;
           while(left < right && arr[right] == 1) right--;
           if(left < right){
               int temp = arr[left];
               arr[left] = arr[right];
               arr[right] = temp;

             /*
               OR we can also write directly.
                arr[left] = 0;
                arr[right] = 1;
              */
             left++;
             right--;
           }
       }
       return arr;
    }
    public static void main(String[] args) {
        Random random = new Random();
        Scanner sc = new Scanner(System.in);
        int binaryLengthNum;
        String choice;
        do {
            System.out.println("Enter the length of the binary array to generate:");
            binaryLengthNum = sc.nextInt();

            if (binaryLengthNum <= 0) {

                System.out.println("Entered number should be greater than 0.");
                System.out.println("Do you want to try again? Enter 'yes' to continue, or anything else to exit:");

                choice = sc.next();

            } else {

                int[] arr = new int[binaryLengthNum];

                for (int i = 0; i < binaryLengthNum; i++) {
                    arr[i] = random.nextInt(2);
                }

                System.out.println(
                        "Generated binary array: " + Arrays.toString(arr)
                );
                segregate(arr);

                System.out.println("After segregation: "
                        + Arrays.toString(arr));

                break; // to valid input → no need to ask again
            }

        } while (choice.equalsIgnoreCase("yes"));

        System.out.println("Program terminated.");
        sc.close();
    }
}
