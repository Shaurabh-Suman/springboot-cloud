package Array.SlidingWindow.FrequencyMapSlidingWindow;
/*
Character Replacement – Sliding Window
Objective: Find the longest substring that can be converted into a string containing the same character by replacing at most k characters.

Example:
s = "AABABBA";
k = 1;


Answer: 4 (AABA → AAAA, replacing B with A).
 */
public class LongestSubStringCharacterReplacement {
    public static int characterReplacement(String s, int k) {
        // Frequency array for uppercase English letters ('A' through 'Z')
        int[] charCount = new int[26];
        int left = 0;
        int maxCount = 0; // Tracks the count of the single most frequent character in the current window
        int maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);

            // 1. ADD: Increment the frequency of the incoming character
            charCount[currentChar - 'A']++;

            // Update maxCount if the new character has the highest frequency so far
            maxCount = Math.max(maxCount, charCount[currentChar - 'A']);

            // 2. CHECK & SHRINK:
            // Total window length - frequency of the most frequent character = number of replacements needed.
            // If replacements needed > k, the window is invalid. Shrink from the left.
            while ((right - left + 1) - maxCount > k) {
                char leftChar = s.charAt(left);
                charCount[leftChar - 'A']--; // Decrement frequency of outgoing character
                left++;
            }

            // 3. UPDATE: Record the maximum valid window length found
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    public static void main(String[] args) {
        System.out.println(characterReplacement("AABABBA", 1)); // Output: 4 ("AABA")
        System.out.println(characterReplacement("ABAB", 2));     // Output: 4 ("AAAA" or "BBBB")
    }
}

/*
# Character Replacement – Sliding Window

Objective: Find the longest substring that can be converted into a string containing the same character by replacing at most `k` characters.

Example:

```
s = "AABABBA";
k = 1;
```

Answer: `4` (`AABA` → `AAAA`, replacing `B` with `A`).

## 1. Initialize variables

```
int[] charCount = new int[26];
int left = 0;
int maxCount = 0;
int maxLen = 0;
```

- `charCount`: Stores the frequency of each uppercase character.
- `left`: Starting index of the current window.
- `maxCount`: Highest frequency of a single character in the current window (tracked maximum).
- `maxLen`: Longest valid window found.

## 2. Expand the window

```
for (int right = 0; right < s.length(); right++) {
    char currentChar = s.charAt(right);
    charCount[currentChar - 'A']++;
```

- `right` expands the window towards the right.
- `currentChar` gets the incoming character.
- `charCount[currentChar - 'A']++` increases its frequency.

## 3. Update maximum frequency

```
maxCount = Math.max(maxCount, charCount[currentChar - 'A']);
```

Tracks the highest character frequency encountered.

Example:

```
Window = AABA

A = 3
B = 1

maxCount = 3
```

## 4. Check whether the window is valid

```
while ((right - left + 1) - maxCount > k) {
```

The formula:

[{Replacements needed} = {Window length} - {Maximum frequency}]

Why?

- We keep the most frequent character.
- We replace all other characters with it.
- If replacements needed exceed `k`, the window is invalid.

Example:

```
Window = AABAB
Length = 5
Maximum frequency = 3
k = 1

Replacements = 5 - 3 = 2
```

Since `2 > 1`, shrink the window.

## 5. Shrink the window

```
char leftChar = s.charAt(left);
charCount[leftChar - 'A']--;
left++;
```

- Identify the character leaving the window.
- Decrease its frequency.
- Move `left` one position forward.

## 6. Update maximum window length

```
maxLen = Math.max(maxLen, right - left + 1);
```

Calculate the current valid window length and update `maxLen` if it is larger.

## 7. Return the answer

```
return maxLen;
```

Returns the longest valid substring length.

## Complete logic to remember

1. Expand the window using `right`.
2. Increase the incoming character's frequency.
3. Track the maximum character frequency.
4. Calculate replacements: `window length - maxCount`.
5. If replacements exceed `k`, shrink using `left`.
6. Update the maximum valid window length.

## Complexity

| Metric | Complexity   |
| ------ | ------------ |
| Time   | O(n)         |
| Space  | O(26) = O(1) |

Important concept: Unlike Anagram problems, here we do not compare two frequency arrays. We use the frequency of the most common character to calculate how many replacements are required.
 */

/*
How Frequency Counting Drives the Sliding WindowThe Incoming Step (right pointer expansion):As the right pointer moves forward, we look up the character's position (s.charAt(right) - 'A') and increment its count in our int[] charCount array in $O(1)$ time.The Optimization (maxCount):Instead of scanning the entire frequency array of size 26 every time to find the most frequent character, we maintain a running maxCount. Whenever a character's count increases, we check if it sets a new peak for the window.The Outgoing Step (left pointer contraction):When the window violates our condition (e.g., requiring more than $k$ replacements), we shrink from the left. We pull out s.charAt(left), decrement its frequency in the array, and increment left.Why an Array over a HashMap?Using a primitive integer array of size 26 (or 256 for ASCII) is significantly faster and uses less memory than a HashMap<Character, Integer> because it avoids object creation, hashing overhead, and boxing/unboxing.
 */