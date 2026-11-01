package Array.SlidingWindow.VariableWindow;

import java.util.HashMap;
import java.util.Map;
/*
. What does "exactly K distinct characters" mean?
Find the length of the longest continuous substring that contains
 exactly K different character types. Each type may occur multiple times.
 */

public class LongestSubstringExactKDistinct {

    public static int lengthOfLongestSubstringExactK(String s, int k) {
        if (s == null || s.length() == 0 || k <= 0) {
            return 0;
        }

        // If the entire string has fewer than K distinct characters, it's impossible
        if (countDistinctCharacters(s) < k) {
            return 0;
        }

        // The longest substring with *at most* K distinct characters
        // will naturally have *exactly* K distinct characters.
        return lengthOfLongestSubstringAtMostK(s, k);
    }

    private static int lengthOfLongestSubstringAtMostK(String s, int k) {
        Map<Character, Integer> charCountMap = new HashMap<>();
        int maxLen = 0;
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);
            charCountMap.put(currentChar, charCountMap.getOrDefault(currentChar, 0) + 1);

            // Shrink the window from the left if distinct characters exceed K
            while (charCountMap.size() > k) {
                char leftChar = s.charAt(left);
                charCountMap.put(leftChar, charCountMap.get(leftChar) - 1);

                if (charCountMap.get(leftChar) == 0) {
                    charCountMap.remove(leftChar);
                }
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    private static int countDistinctCharacters(String s) {
        boolean[] seen = new boolean[256];
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (!seen[s.charAt(i)]) {
                seen[s.charAt(i)] = true;
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstringExactK("eceba", 2)); // Output: 3 ("ece")
        System.out.println(lengthOfLongestSubstringExactK("aa", 1));     // Output: 2 ("aa")
        System.out.println(lengthOfLongestSubstringExactK("aa", 2));     // Output: 0 (less than 2 distinct chars)
    }
}
/*
Here is a breakdown of the conceptual and logical differences between the "At Most K Distinct Characters" scenario and the "Exactly K Distinct Characters" scenario.

1. Conceptual Difference (The Goal)
At Most K Distinct Characters:
Goal: Find the longest substring where the number of unique characters is anywhere from 1 up to K (i.e., K).
Example ("eceba", K=2): Valid windows include "e" (1), "ec" (2), "ece" (3), "eb" (2), "ba" (2). The longest is "ece" (length 3).


Exactly K Distinct Characters:
Goal: Find the longest substring where the number of unique characters is strictly equal to K (i.e., =K). If a string contains fewer than K distinct characters overall, it's invalid.
Example ("aa", K=2): The string only has 1 unique character ('a'). Therefore, finding a substring with exactly 2 distinct characters is impossible (Output: 0).

2. Code Logic Difference
While "At Most K" requires a direct sliding window, "Exactly K" relies on a clever mathematical property of maximum lengths.

How "At Most K" Logic Works (The Engine)
Condition to shrink: while (charCountMap.size() > k)
Mechanism: The window expands freely until unique characters exceed K. Then, it shrinks from the left until it is valid again (K). Because we track maxLen at every step, it naturally captures the peak window size.
How "Exactly K" Logic Works (The Wrapper)
Because we are looking for the longest substring:

Any window that gives the maximum length under the "At Most K" constraint will naturally contain exactly K distinct characters. (A window with fewer than K characters would be shorter and thus wouldn't beat our maximum length anyway).
Therefore, the code for Exactly K reuses the At Most K function as a helper, with one extra safety check upfront:


Java


// If the whole string doesn't even have K distinct characters, return 0
if (countDistinctCharacters(s) < k) {
    return 0;
}
return lengthOfLongestSubstringAtMostK(s, k);
 */