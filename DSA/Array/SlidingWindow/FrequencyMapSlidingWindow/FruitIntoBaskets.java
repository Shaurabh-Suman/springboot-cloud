package Array.SlidingWindow.FrequencyMapSlidingWindow;

import java.util.HashMap;
import java.util.Map;
/*
Problem OverviewYou are visiting a farm with a row of fruit trees represented by an integer array fruits (where fruits[i] is the type of fruit the $i$-th tree produces).You have two baskets, and each basket can hold any single type of fruit. There is no limit on the amount of fruit each basket can hold. Starting from any tree, you must pick exactly one fruit from every tree (moving left to right) while stopping as soon as you hit a tree with a fruit type that doesn't fit in your baskets.In short: Find the length of the longest contiguous subarray that contains at most 2 distinct numbers. (Notice how this maps directly to our earlier discussion on "At Most $K$ Distinct Characters" where $K = 2$!)Java Implementation
 i.e.,
 ***************************************************************************************

# 11. Fruit Into Baskets – Sliding Window

Let's first understand the problem statement. No code yet.

## 1. Problem statement

You are given an integer array `fruits`.

Each element represents a type of fruit growing on a tree.

You have two baskets, and each basket can hold only one type of fruit.

Rules:

1. You can collect fruits from consecutive trees only.
2. You can carry at most two different types of fruit.
3. Each basket can hold any number of fruits, but only one type.
4. Once you start collecting, you must continue to the right without skipping trees.

Your task: Find the maximum number of fruits you can collect.

## 2. Example

```
int[] fruits = {1, 2, 1, 2, 3};
```

Imagine each number represents a fruit type.

Fruit trees

# 1

Index 0

# 2

Index 1

# 1

Index 2

# 2

Index 3

# 3

Index 4

Two baskets

Basket 1

# Type 1

Basket 2

# Type 2

You can collect:

```
1, 2, 1, 2
```

- Basket 1 holds fruit type `1`.
- Basket 2 holds fruit type `2`.

At index `4`, we encounter fruit type `3`.

We cannot collect it because we already have two different types.

Therefore:

```
Maximum fruits = 4
```

## 3. Another example

```
int[] fruits = {1, 2, 3, 2, 2};
```

Possible collection:

```
       1  2  3  2  2
          |--------|
```

We collect:

```
2, 3, 2, 2
```

- Basket 1 → Type `2`
- Basket 2 → Type `3`

Total = `4`.

Output: 4

## 4. What is the actual problem asking?

It is NOT asking:

- Find two fruits.
- Find two different numbers.
- Count the total number of unique fruits in the entire array.

It IS asking:

Find the longest contiguous subarray containing at most two distinct fruit types.

## 5. Connection with Sliding Window

This problem uses:

- `left` → Starting boundary of the window.
- `right` → Ending boundary of the window.
- `HashMap` → Tracks the frequency of each fruit type in the current window.
- Expand the window while there are at most two distinct fruit types.
- If there are more than two distinct types, shrink from the left.
- Track the maximum window length.

The key condition:

[ Number of distinct fruit types=< 2]

That's the fundamental concept of Fruit Into Baskets.

Next, we can understand why we need a HashMap and trace the Java code step by step.
 ****************************************************************************************
 */
public class FruitIntoBaskets {

    public static int totalFruit(int[] fruits) {
        if (fruits == null || fruits.length == 0) {
            return 0;
        }

        // Map to store fruit types and their respective counts in the current window
        Map<Integer, Integer> fruitCountMap = new HashMap<>();
        int maxFruits = 0;
        int left = 0;

        for (int right = 0; right < fruits.length; right++) {
            int currentFruit = fruits[right];
            fruitCountMap.put(currentFruit, fruitCountMap.getOrDefault(currentFruit, 0) + 1);

            // If we have more than 2 types of fruits, shrink the window from the left
            while (fruitCountMap.size() > 2) {
                int leftFruit = fruits[left];
                fruitCountMap.put(leftFruit, fruitCountMap.get(leftFruit) - 1);

                // Remove the fruit type completely from the map if its count drops to 0
                if (fruitCountMap.get(leftFruit) == 0) {
                    fruitCountMap.remove(leftFruit);
                }
                left++;
            }

            // Update the maximum number of fruits collected so far
            maxFruits = Math.max(maxFruits, right - left + 1);
        }

        return maxFruits;
    }

    public static void main(String[] args) {
        System.out.println(totalFruit(new int[]{1, 2, 1}));          // Output: 3 ([1, 2, 1])
        System.out.println(totalFruit(new int[]{0, 1, 2, 2}));       // Output: 3 ([1, 2, 2])
        System.out.println(totalFruit(new int[]{1, 2, 3, 2, 2}));    // Output: 4 ([2, 3, 2, 2])
    }
}
/*
## Fruit Into Baskets – Sliding Window

Goal: Find the longest continuous subarray containing at most 2 distinct fruit types.

For example:

```
fruits = [1, 2, 3, 2, 2]
```

We can collect `[2, 3, 2, 2]`, which contains 4 fruits and only 2 types.

Answer = 4

### 1. Variables

| Variable        | Purpose                                  |
| --------------- | ---------------------------------------- |
| `left`          | Starting index of the window             |
| `right`         | Ending index of the window               |
| `fruitCountMap` | Stores each fruit type and its frequency |
| `maxFruits`     | Maximum valid window length              |

### 2. How the code works

Step 1: Expand the window

```
int currentFruit = fruits[right];

fruitCountMap.put(
    currentFruit,
    fruitCountMap.getOrDefault(currentFruit, 0) + 1
);
```

Add the fruit at `right` to the map and increase its frequency.

Step 2: Check the number of fruit types

```
while (fruitCountMap.size() > 2)
```

If the map contains more than 2 distinct fruit types, our window is invalid.

Step 3: Shrink the window

```
int leftFruit = fruits[left];
fruitCountMap.put(leftFruit, fruitCountMap.get(leftFruit) - 1);
```

Decrease the frequency of the fruit leaving the window.

```
if (fruitCountMap.get(leftFruit) == 0) {
    fruitCountMap.remove(leftFruit);
}
left++;
```

If its frequency becomes zero, remove that fruit type from the map. Then move `left` forward.

Step 4: Update the maximum length

```
maxFruits = Math.max(maxFruits, right - left + 1);
```

Calculate the current window length and keep the maximum.

### 3. Dry run

Input:

```
fruits = [1, 2, 3, 2, 2]
```

| `right` | Fruit added | Map after adjustment | Valid window | `maxFruits` |
| ------- | ----------- | -------------------- | ------------ | ----------- |
| 0       | 1           | `{1=1}`              | `[1]`        | 1           |
| 1       | 2           | `{1=1, 2=1}`         | `[1,2]`      | 2           |
| 2       | 3           | `{2=1, 3=1}`         | `[2,3]`      | 2           |
| 3       | 2           | `{2=2, 3=1}`         | `[2,3,2]`    | 3           |
| 4       | 2           | `{2=3, 3=1}`         | `[2,3,2,2]`  | 4           |

At `right = 2`, three types appear: `{1, 2, 3}`.

So we shrink from the left:

- Remove fruit `1`.
- Its count becomes zero.
- Remove type `1` from the map.
- Move `left` to index 1.

Now only two types remain.

Output: 4

### Remember the core rule

At most 2 distinct types = `fruitCountMap.size() <= 2`

This is a variable-size sliding window:

- Expand `right` to include fruits.
- Shrink `left` whenever distinct types exceed 2.
- Update the maximum valid window length.
 */