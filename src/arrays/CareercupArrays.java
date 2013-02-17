package arrays;

/**
 * Created with IntelliJ IDEA.
 * User: U0117190
 * Date: 2/15/13
 * Time: 8:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class CareercupArrays {

    public static void main(String[] args) {

    }

    /**
     You are given two sorted arrays, A and B, and A has a large enough buffer at the end to hold B. Write a method to merge B into A in sorted order.
     This code is a part of the standard merge-sort code. We merge A and B from the back, by comparing each element.
     */
    public static void merge(int[] a, int[] b, int n, int m) {
        int k = m + n - 1; // Index of last location of array b
        int i = n - 1; // Index of last element in array b
        int j = m - 1; // Index of last element in array a

// Start comparing from the last element and merge a and b
        while (i >= 0 && j >= 0) {
            if (a[i] > b[j]) {
                a[k--] = a[i--];
            } else {
                a[k--] = b[j--];
            }
        }
        while (j >= 0) {
            a[k--] = b[j--];
        }
    }
}
