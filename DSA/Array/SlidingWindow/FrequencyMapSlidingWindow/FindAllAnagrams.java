package Array.SlidingWindow.FrequencyMapSlidingWindow;

import java.util.ArrayList;
import java.util.List;
/*
How the Sliding Window Logic WorksFixed Window Size: Since an anagram must be an exact rearrangement of pattern p, the window size is permanently locked to p.length().Frequency Tracking: We use two arrays of size 26 for lowercase English letters: one for the target pattern (pCount) and one for our rolling window in the main string (sCount).The Slide:As the window shifts right, we add the incoming character's frequency (sCount[s.charAt(i) - 'a']++).We drop the outgoing character's frequency from the left side (sCount[s.charAt(i - pLen) - 'a']--).Recording Indices: Whenever sCount matches pCount, the start index of the current window is i - pLen + 1. We append this index to our result list.Complexity AnalysisTime Complexity: $O(n)$, where $n$ is the length of string s. We traverse s once, and comparing the 26-element array takes a constant $O(26)$ time per shift.Space Complexity: $O(1)$ auxiliary space (excluding the output list), because the frequency arrays are constrained to a fixed size of 26.
 */
public class FindAllAnagrams {

    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        int sLen = s.length();
        int pLen = p.length();

        // If the pattern is longer than the string, no anagrams can exist
        if (sLen < pLen) {
            return result;
        }

        int[] pCount = new int[26];
        int[] sCount = new int[26];

        // Step 1: Populate frequency arrays for pattern (p) and the first window of s
        for (int i = 0; i < pLen; i++) {
            pCount[p.charAt(i) - 'a']++;
            sCount[s.charAt(i) - 'a']++;
        }

        // Check if the very first window is an anagram
        if (matches(pCount, sCount)) {
            result.add(0);
        }

        // Step 2: Slide the window across s
        for (int i = pLen; i < sLen; i++) {
            // Add the new character entering the window on the right
            sCount[s.charAt(i) - 'a']++;

            // Remove the old character leaving the window from the left
            sCount[s.charAt(i - pLen) - 'a']--;

            // Calculate the start index of the current window and check for a match
            if (matches(pCount, sCount)) {
                result.add(i - pLen + 1);
            }
        }

        return result;
    }

    // Helper function to compare two frequency arrays of size 26
    private static boolean matches(int[] a, int[] b) {
        for (int i = 0; i < 26; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(findAnagrams("cbaebabacd", "abc")); // Output: [0, 6]
        System.out.println(findAnagrams("abab", "ab"));          // Output: [0, 1, 2]
    }
}

/*

# Find All Anagrams – Sliding Window

Objective: Find all starting indexes in string `s` where an anagram of pattern `p` exists.

Example:

```
s = "cbaebabacd";
p = "abc";
```

Anagrams of `"abc"` include `"abc"`, `"bca"`, `"cab"`, etc.

Output:

```
[0, 6]
```

## 1. Initialize variables

```
List<Integer> result = new ArrayList<>();
int sLen = s.length();
int pLen = p.length();
```

- `result`: Stores starting indexes of anagrams.
- `sLen`: Length of original string.
- `pLen`: Length of pattern.

```
if (sLen < pLen) {
    return result;
}
```

If the original string is shorter than the pattern, an anagram cannot exist.

## 2. Create frequency arrays

```
int[] pCount = new int[26];
int[] sCount = new int[26];
```

- `pCount`: Stores character frequencies of pattern `p`.
- `sCount`: Stores character frequencies of the current window in `s`.
- Index `0` represents `a`, index `1` represents `b`, and so on.

## 3. Populate frequency arrays

```
for (int i = 0; i < pLen; i++) {
    pCount[p.charAt(i) - 'a']++;
    sCount[s.charAt(i) - 'a']++;
}
```

This loop performs two operations:

1. Counts characters in pattern `p`.
2. Counts characters in the first window of `s`.

For our example:

```
p = abc
First window = cba
```

Both frequency arrays contain:

| Character | pCount | sCount |
| --------- | ------ | ------ |
| a         | 1      | 1      |
| b         | 1      | 1      |
| c         | 1      | 1      |

## 4. Check the first window

```
if (matches(pCount, sCount)) {
    result.add(0);
}
```

- `matches()` compares both frequency arrays.
- If all 26 positions match, the current window is an anagram.
- `0` is added because the first window starts at index `0`.

## 5. Slide the window

```
for (int i = pLen; i < sLen; i++) {
```

Starts sliding from index `3` because the first window already contains indexes `0, 1, 2`.

A. Add the entering character

```
sCount[s.charAt(i) - 'a']++;
```

Increases the frequency of the new character entering from the right.

B. Remove the leaving character

```
sCount[s.charAt(i - pLen) - 'a']--;
```

Decreases the frequency of the old character leaving from the left.

C. Check for anagram

```
if (matches(pCount, sCount)) {
    result.add(i - pLen + 1);
}
```

If frequencies match, add the starting index of the current window.

Starting index formula:

\\[ i-pLen+1 \\]

## 6. Understand the matches() method

```
private static boolean matches(int[] a, int[] b) {
    for (int i = 0; i < 26; i++) {
        if (a[i] != b[i]) {
            return false;
        }
    }
    return true;
}
```

- Compares all 26 array positions.
- If even one frequency differs, returns `false`.
- If all frequencies match, returns `true`.

## 7. Complete execution example

For:

```
s = "cbaebabacd";
p = "abc";
```

Sliding window of size 3

cba

Index 0

Anagram

bae

Index 1

No match

aeb

Index 2

No match

eba

Index 3

No match

bab

Index 4

No match

aba

Index 5

No match

bac

Index 6

Anagram

acd

Index 7

No match

Final result

# [0, 6]

## 8. Complexity

| Complexity | Explanation      |
| ---------- | ---------------- |
| Time       | O(26 × n) = O(n) |
| Space      | O(26) = O(1)     |

Here, `n` is the length of `s`. The frequency arrays always have 26 elements.

## 9. Remember the overall logic

1. Create two frequency arrays.
2. Count characters in the pattern.
3. Count characters in the first window.
4. Compare both frequency arrays.
5. Slide the window one position at a time.
6. Add the entering character's frequency.
7. Subtract the leaving character's frequency.
8. Compare both frequency arrays again.
9. Store the starting index whenever frequencies match.

Key difference from Permutation in String:

- Permutation in String → Return `true` or `false` (whether any permutation exists).
- Find All Anagrams → Return all starting indexes where anagrams exist.
 */