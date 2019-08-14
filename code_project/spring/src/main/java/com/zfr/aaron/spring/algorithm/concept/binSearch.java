package com.zfr.aaron.spring.algorithm.concept;

public class binSearch {

    public static void main(String[] args) {
        System.out.println(bsearch(new int[]{1,3,4,5,6,8,8,8,11,18},10,8));
    }

    public static int bsearch(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] >= value) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        if (low < n && a[low]==value) {
            return low;
        } else {
            return -1;
        }
    }


}
