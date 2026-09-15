package Array.TwoPointer.OppositeDirection;

public class ContainerWithMostWater {
    /**
     * Finds the maximum area of water a container can store.
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     */
    private static int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;
        while (left < right) {
            // Calculate current width and height
            int width = (right - left);
            int currentHeight = Math.min(height[left], height[right]);
            //int area = Math.min(height[left], height[right]) * (right - left);
            int currentArea = width * currentHeight;
            // Update maximum area
            maxArea = Math.max(maxArea, currentArea);
            // Move the pointer pointing to the shorter line inward
            if (height[left] < height[right]) {
                left++;
            }else{
                right--;
            }
        }
        return maxArea;
    }
    public static void main(String[] args) {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println("Max Area: " + maxArea(height)); // Output: 49
    }
}
