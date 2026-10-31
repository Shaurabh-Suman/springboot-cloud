package Array.SlidingWindow.VariableWindow;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeating {

    public static int lengthOfLongestSubstring(String s) {
        // Map to store each character and its most recent index
        Map<Character, Integer> charIndexMap = new HashMap<>();
        int maxLen = 0;
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);

            // If character is seen and is within the current window
            /*
             2nd condition, must need here because i.e.,
             left must never move backwards, because every character before left has already been excluded from the current window.
             e.g., "abba"
             */
            if (charIndexMap.containsKey(currentChar) && charIndexMap.get(currentChar) >= left) {
                left = charIndexMap.get(currentChar) + 1;
            }

            // Update the character's latest index
            charIndexMap.put(currentChar, right);

            // Calculate max length
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb")); // Output: 3 ("abc")
        System.out.println(lengthOfLongestSubstring("bbbbb"));    // Output: 1 ("b")
        System.out.println(lengthOfLongestSubstring("pwwkew"));   // Output: 3 ("wke")
    }
}

/*
For "abba"
Iteration 4: right = 3, character = a

Now look carefully.

Map:
a → 0
b → 2


Check:
charIndexMap.containsKey('a')


Result: true.

Because we removed the second condition, Java directly executes:
left = charIndexMap.get('a') + 1;


That means:
left = 0 + 1;
left = 1;


Here is the problem!
Previously, left was 2.

Now left has become 1.

Look at the string:
Index:       0    1    2    3
Character:   a    b    b    a
                  ↑         ↑
                left      right


Our new window is:
b b a


But this window contains two b characters!

We have accidentally included a duplicate character in our window.

Now calculate:
maxLen = Math.max(2, 3 - 1 + 1);
maxLen = 3;


Incorrect answer = 3
 */

/*
Operation	              WITH second condition      	WITHOUT second condition
Previous index of a       	0	                           0
Current left	            2	                           2
Check 0 >= 2	            False	                       No check
Execute assignment?      	No	                           Yes
New left	                2	                           1
Window	                    ba	                           bba
Window length	            2	                           3

Now the most important question:

Why should we NOT move left from 2 to 1?

Because index 0 contains the old a. That old a was already outside our current window, which starts at index 2.

We don't want an old occurrence of a character to disturb our current window.

That's exactly why we need:
charIndexMap.get(currentChar) >= left


It checks whether the previous occurrence is still inside our current window.
- If yes, move left.
- If no, leave left where it is.
 */