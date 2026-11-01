package Array.SlidingWindow.VariableWindow;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringKDistinct {
    /*
Complete algorithm as a set of rules
This is what your entire code is doing:
1. Expand the window

   Move right one position forward and include that character.
2. Update character frequency

   Add the character to the HashMap or increase its existing frequency.
3. Check distinct character count

   If charCountMap.size() > k, the current window is invalid.
4. Shrink the window

   Decrease the frequency of the character at left. If its frequency becomes zero, remove its key. Move left forward.
5. Calculate the valid window length

   Once the window is valid:
   [right-left+1]
6. Update the maximum

   Keep the larger value between the previous maximum and current window length.
 */
    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0 || k <= 0) {
            return 0;
        }

        // Map to store character frequencies in the current window
        Map<Character, Integer> charCountMap = new HashMap<>();
        int maxLen = 0;
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);
            charCountMap.put(currentChar, charCountMap.getOrDefault(currentChar, 0) + 1);

            // If distinct characters exceed K, shrink the window from the left
            while (charCountMap.size() > k) {
                char leftChar = s.charAt(left);
                charCountMap.put(leftChar, charCountMap.get(leftChar) - 1);

                // Remove the character completely from the map if its count drops to 0
                if (charCountMap.get(leftChar) == 0) {
                    charCountMap.remove(leftChar);
                }
                left++;
            }

            // Update the maximum length found so far
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstringKDistinct("eceba", 2)); // Output: 3 ("ece")
        System.out.println(lengthOfLongestSubstringKDistinct("aa", 1));     // Output: 2 ("aa")
    }
}

/*
Find the length of the longest continuous substring that contains at most K distinct (unique) character types. Each character type may occur multiple times in that substring.

For example:
String = "aaabac"
K = 2

Consider these substrings:
Substring	Distinct characters	      Length	  Valid?
"aaa"	       1 (a)	                3	       Yes
"aaaba"	       2 (a, b)	                5	       Yes
"aaabac"	   3 (a, b, c)	            6	       No
"ab"	       2 (a, b)             	2	       Yes

Answer = 5
The longest valid substring is "aaaba".

The fundamental rule:
- Duplicate characters are allowed.
- Only the number of different character types is restricted.
- Characters must be continuous.
- The substring length counts every character, including duplicates.
In other words, K restricts character variety, not character frequency.
 */