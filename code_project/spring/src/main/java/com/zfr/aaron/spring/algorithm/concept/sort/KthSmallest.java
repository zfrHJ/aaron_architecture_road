package com.zfr.aaron.spring.algorithm.concept.sort;

import java.util.Arrays;

/**
 * 寻找大于k的数
 */
public class KthSmallest {

    public static void main(String[] args) {
        int i = kthSmallest(new int[]{1, 8, 9, 6, 4, 1, 5, 6, 4, 10, 4, 44, 55}, 12);
        System.out.println(i);




    }

    public static  int kthSmallest(int[] arr,int k){

        if(arr == null || arr.length < k ){

            return -1;
        }

        //分区点-获取
        int partition = partition(arr,0,arr.length-1);
        while (partition + 1 != k) {
            if (partition + 1 < k) {
                partition = partition(arr, partition + 1, arr.length - 1);
            } else {
                partition = partition(arr, 0, partition - 1);
            }
        }

        return arr[partition];

    }

    private static int partition(int[] arr, int p, int r) {
        //取最后一个点
        int pivot = arr[r];

        int  i =p ;

        for(int j =p; j <r;j++){
            // 这里要是 <= ，不然会出现死循环，比如查找数组 [1,1,2] 的第二小的元素
            if (arr[j] <= pivot) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, r);

        return i;


    }
    private static void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }

        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
