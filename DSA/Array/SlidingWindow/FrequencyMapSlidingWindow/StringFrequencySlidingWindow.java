package Array.SlidingWindow.FrequencyMapSlidingWindow;

public class StringFrequencySlidingWindow {

    public static void countFrequenciesUsingSlidingWindow(String s, int windowSize) {
        if (s == null || s.length() == 0 || windowSize <= 0 || windowSize > s.length()) {
            System.out.println("Invalid string or window size.");
            return;
        }

        // Frequency array to store counts of characters 'a' through 'z'
        int[] charCount = new int[26];
        int left = 0;

        System.out.println("Processing string: \"" + s + "\" with Window Size = " + windowSize);
        System.out.println("------------------------------------------------------------------");

        for (int right = 0; right < s.length(); right++) {
            // 1. EXPAND: Add the new character entering the window on the right
            char incomingChar = s.charAt(right);
            charCount[incomingChar - 'a']++;

            // 2. SHRINK: If the window exceeds the target size, slide the left pointer forward
            if (right - left + 1 > windowSize) {
                char outgoingChar = s.charAt(left);
                charCount[outgoingChar - 'a']--; // Remove the character leaving from the left
                left++;
            }

            // 3. PROCESS/PRINT: Once the window reaches the exact target size, view the frequencies
            if (right - left + 1 == windowSize) {
                String currentWindow = s.substring(left, right + 1);
                System.out.print("Window [" + left + " -> " + right + "] (\"" + currentWindow + "\") => Frequencies: ");
                printFrequencies(charCount);
            }
        }
    }

    // Helper method to display active character counts
    private static void printFrequencies(int[] charCount) {
        StringBuilder sb = new StringBuilder("{ ");
        boolean first = true;
        for (int i = 0; i < 26; i++) {
            if (charCount[i] > 0) {
                if (!first) {
                    sb.append(", ");
                }
                sb.append((char) (i + 'a')).append(": ").append(charCount[i]);
                first = false;
            }
        }
        sb.append(" }");
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        String text = "banana";
        int k = 3; // Window size

        countFrequenciesUsingSlidingWindow(text, k);
    }
}
/*
How the Sliding Window Counts Frequencies:
right pointer expands the window to the right, adding each character's count instantly to our charCount array (charCount[incomingChar - 'a']++).

left pointer shrinks the window from the left whenever the window exceeds our specified size k, subtracting the outgoing character's count (charCount[outgoingChar - 'a']--).

This ensures that the frequency array always reflects the exact character counts of the current active window without needing to re-scan the string from scratch.
 */