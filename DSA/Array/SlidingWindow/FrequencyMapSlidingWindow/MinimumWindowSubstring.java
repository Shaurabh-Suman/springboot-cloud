package Array.SlidingWindow.FrequencyMapSlidingWindow;

import java.util.HashMap;
import java.util.Map;
/*
Problem Overview
Given two strings s and t, return the minimum window substring of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return an empty string "".
i.e.,
Objective: Find the smallest substring of s that contains all characters of t, including their required frequencies.

Example:
s = "ADOBECODEBANC";
t = "ABC";


Output:
BANC


BANC contains A, B and C, and is the smallest valid window.
 */
public class MinimumWindowSubstring {

    public static String minWindow(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0) {
            return "";
        }

        // Map to keep track of character frequencies required from t
        Map<Character, Integer> dictT = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            dictT.put(c, dictT.getOrDefault(c, 0) + 1);
        }

        // Number of unique characters in t that need to be matched in the window
        int required = dictT.size();

        // Counter to keep track of how many unique characters are currently matched
        int formed = 0;

        // Map to keep track of character frequencies in the current window
        Map<Character, Integer> windowCounts = new HashMap<>();

        // Holder for the result: {window length, left pointer, right pointer}
        int[] ans = {-1, 0, 0};
        int left = 0;
        int right = 0;

        while (right < s.length()) {
            char c = s.charAt(right);
            windowCounts.put(c, windowCounts.getOrDefault(c, 0) + 1);

            // If the current character's frequency matches its required count in t, increment 'formed'
            if (dictT.containsKey(c) && windowCounts.get(c).intValue() == dictT.get(c).intValue()) {
                formed++;
            }

            // Try and shrink the window from the left as much as possible while it remains valid
            while (left <= right && formed == required) {
                char leftChar = s.charAt(left);

                // Update our minimum window if a smaller one is found
                if (ans[0] == -1 || (right - left + 1) < ans[0]) {
                    ans[0] = right - left + 1;
                    ans[1] = left;
                    ans[2] = right;
                }

                // Remove the character from the left of the window
                windowCounts.put(leftChar, windowCounts.get(leftChar) - 1);

                // If the character falling off breaks the requirement, decrement 'formed'
                if (dictT.containsKey(leftChar) && windowCounts.get(leftChar).intValue() < dictT.get(leftChar).intValue()) {
                    formed--;
                }

                left++;
            }

            right++;
        }

        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
    }

    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC")); // Output: "BANC"
        System.out.println(minWindow("a", "a"));             // Output: "a"
        System.out.println(minWindow("a", "aa"));            // Output: ""
    }
}
/*
# Minimum Window Substring – Sliding Window

Objective: Find the smallest substring of `s` that contains all characters of `t`, including their required frequencies.

Example:

```
s = "ADOBECODEBANC";
t = "ABC";
```

Output:

```
BANC
```

`BANC` contains A, B and C, and is the smallest valid window.

## 1. Build the required frequency map

```
Map<Character, Integer> dictT = new HashMap<>();

for (int i = 0; i < t.length(); i++) {
    char c = t.charAt(i);
    dictT.put(c, dictT.getOrDefault(c, 0) + 1);
}
```

- `dictT` stores the frequency of each character required from `t`.
- `getOrDefault(c, 0)` returns the existing frequency or 0.
- `+1` increases the frequency.

For `t = "ABC"`:

```
A = 1
B = 1
C = 1
```

## 2. Initialize tracking variables

```
int required = dictT.size();
int formed = 0;

Map<Character, Integer> windowCounts = new HashMap<>();

int[] ans = {-1, 0, 0};
int left = 0;
int right = 0;
```

| Variable       | Purpose                                                            |
| -------------- | ------------------------------------------------------------------ |
| `required`     | Number of unique characters needed                                 |
| `formed`       | Number of unique characters whose required frequencies are matched |
| `windowCounts` | Frequencies of characters in current window                        |
| `ans[0]`       | Minimum window length                                              |
| `ans[1]`       | Starting index of minimum window                                   |
| `ans[2]`       | Ending index of minimum window                                     |
| `left`         | Left boundary                                                      |
| `right`        | Right boundary                                                     |

For `"ABC"`, `required = 3`.

## 3. Expand the window

```
while (right < s.length()) {
    char c = s.charAt(right);
    windowCounts.put(c, windowCounts.getOrDefault(c, 0) + 1);
```

- Get the character entering from the right.
- Increase its frequency in `windowCounts`.

## 4. Update formed

```
if (dictT.containsKey(c) &&
    windowCounts.get(c).intValue() == dictT.get(c).intValue()) {
    formed++;
}
```

Increase `formed` when:

1. The character is required in `t`.
2. Its current window frequency exactly matches its required frequency.

Example:

```
Required: A = 1
Window:   A = 1

formed = 1
```

Important: `formed` counts matched unique characters, not total character occurrences.

## 5. Shrink the window

```
while (left <= right && formed == required) {
```

This means all required character frequencies are currently satisfied.

Now try to make the window smaller.

### A. Update minimum window

```
if (ans[0] == -1 || (right - left + 1) < ans[0]) {
    ans[0] = right - left + 1;
    ans[1] = left;
    ans[2] = right;
}
```

If the current valid window is smaller than the previous answer, save its length and indexes.

### B. Remove the left character

```
char leftChar = s.charAt(left);
windowCounts.put(leftChar, windowCounts.get(leftChar) - 1);
```

Decrease the frequency of the character leaving the window.

### C. Check whether the window becomes invalid

```
if (dictT.containsKey(leftChar) &&
    windowCounts.get(leftChar).intValue() < dictT.get(leftChar).intValue()) {
    formed--;
}
```

If removing the character causes its frequency to fall below the required frequency, decrease `formed`.

### D. Move left forward

```
left++;
```

Repeat shrinking while the window remains valid.

## 6. Move right forward

```
right++;
```

Expand the window again to search for another valid, potentially smaller window.

## 7. Return the result

```
return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
```

- If no valid window exists, return `""`.
- Otherwise, return the smallest substring.

## Overall logic to remember

1. Build a frequency map of required characters (`t`).
2. Expand the window using `right`.
3. Update character frequencies in `windowCounts`.
4. Increase `formed` when a required character reaches its required frequency.
5. When `formed == required`, the window is valid.
6. Save the smallest valid window.
7. Shrink from the left.
8. If a required frequency falls below its target, decrease `formed`.
9. Continue expanding until the string ends.
10. Return the smallest valid substring.

## Complexity

| Metric | Complexity |
| ------ | ---------- |
| Time   | O(S + T)   |
| Space  | O(S + T)   |

Where `S` is the length of `s` and `T` is the length of `t`.

The most important concept:

- `required` = How many unique characters must be satisfied?
- `formed` = How many unique characters have currently satisfied their required frequencies?
- `formed == required` = We have a valid window and can start shrinking it.

This is the central logic of Minimum Window Substring.
 */


/*
How the Sliding Window WorksExpand (right pointer): We sweep through s using the right pointer, adding characters to our windowCounts map. We track how many unique required characters from t have reached their target frequencies using the formed variable.Shrink (left pointer): The moment formed == required, our window is valid (it contains all characters of t). We immediately record the window length, and then try to shrink it from the left (left++) to see if we can find a smaller valid window while keeping formed == required.Optimality: The outer loop continues expanding until the end of the string, while the inner loop efficiently squeezes the window down to its minimal form every time a valid state is achieved.Complexity AnalysisTime Complexity: $O(N + M)$, where $N$ is the length of s and $M$ is the length of t. Both pointers (left and right) traverse s at most once.Space Complexity: $O(M)$ (or $O(1)$ if considering a fixed character set like ASCII/Unicode), used by the hash maps to store character frequencies.
 */