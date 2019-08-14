package com.zfr.aaron.spring.algorithm.concept.sort;

public class QuickSort {


    public static void main(String[] args) {
        int[] ints = {1, 8, 9, 6, 4, 1, 5, 6, 4, 10, 4, 44, 55};
        quickSortInternally(ints, 0,12);

       for( int i =0 ;i < ints.length ; i++){
            System.out.println(ints[i]);
        }
    }

    /**快速排序递归函数，p,r为下标
     *
     * @param a
     * @param p
     * @param r
     */
    private static void quickSortInternally(int[] a, int p, int r) {
        if (p >= r) {
            return;
        }
        // 获取分区点
        int q = partition(a, p, r);
        quickSortInternally(a, p, q-1);
        quickSortInternally(a, q+1, r);
    }

    private static int partition(int[] a, int p, int r) {
        int pivot= a[r];

        int i =p;

        for(int j =p;j<r;++j){
            if(a[j] <pivot){
                if(i == j){
                    ++i;
                }else{
                    int tmp = a[i];
                    a[i++] = a[j];
                    a[j] = tmp;
                }
            }
        }
        int tmp = a[i];
        a[i] = a[r];
        a[r] = tmp;
        return i;

    }

}
