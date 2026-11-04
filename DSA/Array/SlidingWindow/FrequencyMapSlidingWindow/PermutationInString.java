package Array.SlidingWindow.FrequencyMapSlidingWindow;
/*
The complete logic in one view
1. Build frequency arrays for s1 and the first window of s2.
2. Compare their frequencies. If they match, return true.
3. Slide the window by adding one character on the right and removing one on the left.
4. Compare frequencies after every slide.
5. If any window matches, return true; otherwise, return false after all windows are checked.
 */

/*

1. `int[] s2Count = new int[26];` creates an integer array of 26 elements, initially all `0`.

2. We decide the mapping:

- Index `0` represents character `a`.
- Index `1` represents character `b`.
- Index `2` represents character `c`.
- Index `3` represents character `d`.
- ...
- Index `25` represents character `z`.

3. This mapping is created by us as programmers. Java does not automatically know it.

4. `s2.charAt(i)` retrieves the character at index `i` from the original string `s2`.

5. Suppose `s2 = "zzbabcz"` and `i = 3`.

6. `s2.charAt(3)` returns `'a'` because index `3` contains `a`.

7. `'a' - 'a'` returns `0`. This converts character `a` into array index `0`.

8. Therefore, `s2Count[s2.charAt(i) - 'a']++` becomes `s2Count[0]++`.

9. `s2Count[0]` represents the frequency of character `a`, according to our mapping.

10. `s2Count[0]++` increases the frequency of `a` by one.

11. If its previous value was `0`, its new value becomes `1`.

12. Remember: The character `a` comes from the original string `s2`. The array `s2Count` stores only its frequency, not the character itself.
 */
public class PermutationInString {

    public static boolean checkInclusion(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();

        // If s1 is longer than s2, a permutation cannot exist inside s2
        if (len1 > len2) {
            return false;
        }

        int[] s1Count = new int[26];
        int[] s2Count = new int[26];

        // Step 1: Populate frequency arrays for s1 and the first window of s2
        for (int i = 0; i < len1; i++) {
            s1Count[s1.charAt(i) - 'a']++;
            s2Count[s2.charAt(i) - 'a']++;
        }

        // Check if the very first window matches
        if (matches(s1Count, s2Count)) {
            return true;
        }

        // Step 2: Slide the window across s2
        for (int i = len1; i < len2; i++) {
            // Add the new character entering the window on the right
            s2Count[s2.charAt(i) - 'a']++;

            // Remove the old character leaving the window from the left
            s2Count[s2.charAt(i - len1) - 'a']--;

            // Check if frequencies match after the slide
            if (matches(s1Count, s2Count)) {
                return true;
            }
        }

        return false;
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
        System.out.println(checkInclusion("ab", "eidbaooo")); // Output: true ("ba")
        System.out.println(checkInclusion("ab", "eidboaoo")); // Output: false
    }
}


/*
Q). How can s2Count[0]++ increase the count of a when there is no a written anywhere in s2Count[0]?

The answer is: Because we use the array index as a mapping to a character.

Let's prove it with actual Java code.
1. We create an array
int[] s2Count = new int[26];


This array contains only integers, not characters.

Initially:
Index:  0  1  2  3  4  5 ...
Value:  0  0  0  0  0  0 ...


2. We establish a mapping
As programmers, we decide:
s2Count[0]  = frequency of a
s2Count[1]  = frequency of b
s2Count[2]  = frequency of c
s2Count[3]  = frequency of d
...
s2Count[25] = frequency of z


Notice: This mapping is in our logic. Java does not automatically know it.
3. Now, where does a come from?
Look at your original code:
s2Count[s2.charAt(i) - 'a']++;


Suppose:
s2 = "zzbabcz";
i = 3;


First, Java executes:
s2.charAt(3)


Result:
'a'


Next:
'a' - 'a'


Result:
0


Therefore, Java executes:
s2Count[0]++;


4. NOW CONNECT THE TWO
We already decided that:
s2Count[0] = frequency of a;


So when Java executes:
s2Count[0]++;


It increases the frequency of a.

For example:
Before
0
a frequency


Execute
s2Count[0]++;



After
1
a frequency

One occurrence of a has been counted.

The final distinction you must understand
There are two different places:
1. s2 contains actual characters: z, z, b, a, b, c, z.
2. s2Count contains numbers representing the frequencies of those characters.
The character a comes from s2.charAt(i).

The number 0 tells us which frequency box to update in s2Count.

a is not stored inside s2Count[0]. Only its frequency is stored there.

That's the complete connection.
 */

//*********************************************************************
/*
How the Sliding Window WorksUnlike the previous problems where windows had variable sizes, this problem uses a fixed-size sliding window because any permutation of s1 must have the exact same length as s1.Frequency Array Setup: We use integer arrays of size 26 (representing lowercase letters a through z) to keep track of character counts. We map characters using char - 'a'.Initial Window: We count frequencies for s1 and the first window in s2 of length s1.length().Sliding Mechanism: As the window shifts one step to the right:We add the new character entering at the right index.We remove the old character falling off the left side (i - len1).Comparison: Instead of rebuilding the map every time, we check if our s2Count array matches s1Count in constant time ($O(26) \approx O(1)$).Complexity AnalysisTime Complexity: $O(n)$, where $n$ is the length of s2. We traverse s2 once, and comparing the 26-element array takes constant time $O(26)$.Space Complexity: $O(1)$ because the frequency arrays are fixed at a constant size of 26 regardless of the input string lengths.
 */

//*********************************************************************