/*
Requirements
All four methods must be solved recursively: do not use for, while or do-while loops. You may add your own private static helper methods (for example, one with extra parameters such as an index), as long as the public methods keep the signatures below.

1. static int sumDigits(int n)
Returns the sum of the decimal digits of n.

You may assume n >= 0.

Example: sumDigits(1234) returns 1 + 2 + 3 + 4 = 10.

2. static int sum(ArrayList<Integer> list)
Returns the sum of all elements in list.

The sum of an empty list is 0.

The list must not be modified.

3. static boolean isPalindrome(String s)
Returns true if s reads the same forwards and backwards, and false otherwise.

The comparison is case-sensitive ("Abba" is not a palindrome) and every character counts, including spaces.

The empty string and any single-character string are palindromes.

You may assume s is not null.

4. static Integer greatestUpTo(ArrayList<Integer> sorted, int x)
sorted is a list of integers in strictly increasing order (no repeated elements). Returns the largest element of the list that is less than or equal to x.

If the list is empty, or every element is larger than x, returns null.

The method must be efficient: each recursive call must halve the part of the list that is still being searched, so that the number of recursive calls grows as
O
(
log
⁡
n
)
O(logn), where
n
n is the size of the list. It must work for lists with one million elements.

The list must not be modified.
 */
public class test {
    public static int sumDigits(int n) {
        // Start your code
        if (n <= 0) return 0;
        if (n / 10 == 0) return n;
        // End your code
        return sumDigits(n / 10) + n % 10; // delete it if you want
    }

    public static int sum(ArrayList<Integer> list) {
        if (list.isEmpty()) return 0;
        return s(list, 0);
    }

    private static int s(ArrayList<Integer> list, int index) {
        if (index == list.size() - 1) return list.get(index);
        return s(list, index + 1) + list.get(index);
    }

    public static boolean isPalindrome(String s) {
        // Start your code
        if (s == null || s.length() == 0) return true;
        // End your code
        return isP(s, 0, s.length() - 1);// delete it if you want
    }

    private static boolean isP(String s, int i, int j) {
        if (i > j) return true;
        return s.charAt(i) == s.charAt(j) && isP(s, i + 1, j - 1);
    }


    public static Integer greatestUpTo(ArrayList<Integer> sorted, int x) {
        // Start your code
        // End your code
        if (sorted.isEmpty()) return null;
        int j = sorted.size();
        return find(sorted, x, 0, j); // delete it if you want
    }

    /*
     i j m      j
     m   i
     1 3 5 7 9   x=4
    */
    // Start your code (optional private helper methods)
    private static Integer find(ArrayList<Integer> sorted, int x, int i, int j) {
        if (i > j) {
            return sorted.get(i - 1);
        }
        int m = (i + j) >>> 1;
        if (sorted.get(m) == x) {
            return x;
        } else if (sorted.get(m) < x) {
            return find(sorted, x, m + 1, j);
        } else {
            return find(sorted, x, i, m - 1);
        }
    }
    // End your code
}


public static Integer greatestUpTo(ArrayList<Integer> sorted, int x) {
    if (sorted.isEmpty()) return null;

    return find(sorted, x, 0, sorted.size() - 1);
}

private static Integer find(ArrayList<Integer> sorted, int x, int i, int j) {
    if (i > j) {
        if (j < 0) return null;
        return sorted.get(j);
    }

    int m = (i + j) >>> 1;

    if (sorted.get(m) == x) {
        return x;
    } else if (sorted.get(m) < x) {
        return find(sorted, x, m + 1, j);
    } else {
        return find(sorted, x, i, m - 1);
    }
}

void main() {
}
