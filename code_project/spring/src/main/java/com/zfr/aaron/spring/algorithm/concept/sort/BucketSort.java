package com.zfr.aaron.spring.algorithm.concept.sort;

import java.util.HashMap;
import java.util.Map;

/**
 * 桶排序
 */
public class BucketSort {

    public static void main(String[] args) {

        Map<String, Object> stringObjectHashMap = new HashMap<>();


        System.out.println( ("1" == null) ? 0 : ("fhdjh".hashCode()) ^ ("fhdjh".hashCode() >>> 16));

        int[] ints = {1, 8, 9, 6, 4, 1, 5, 6, 4, 10, 4, 44, 55};

        bucketSort(ints,13);

        for( int i =0 ;i < ints.length ; i++){
            System.out.println(ints[i]);
        }
    }
    public static void bucketSort(int[] arr, int bucketSize) {

        if(arr.length < 2){
            return;
        }

        //数组最小值
        int minValue = arr[0];

        //数组最大值
        int maxValue = arr[1];

        //计算出数组真实大小的最大值、最小值
        for(int i=0 ; i <arr.length ; i++){
            if (arr[i] < minValue) {
                minValue = arr[i];
            } else if (arr[i] > maxValue) {
                maxValue = arr[i];
            }

        }
        // 桶数量
        int bucketCount = (maxValue - minValue) / bucketSize + 1;
        int[][] buckets = new int[bucketCount][bucketSize];
        int[] indexArr = new int[bucketCount];
        //将数组中值分配到各个桶里
        for (int i = 0; i < arr.length; i++) {
            int bucketIndex = (arr[i] - minValue) / bucketSize;
            if (indexArr[bucketIndex] == buckets[bucketIndex].length) {
                //数组扩容
                ensureCapacity(buckets, bucketIndex);
            }
            buckets[bucketIndex][indexArr[bucketIndex]++] = arr[i];
        }

        //对每个桶进行排序，这里使用了快速排序
        int k = 0;
        for (int i = 0; i < buckets.length; i++) {
            if (indexArr[i] == 0) {
                continue;
            }
            quickSortC(buckets[i], 0, indexArr[i] - 1);
            for (int j = 0; j < indexArr[i]; j++) {
                arr[k++] = buckets[i][j];
            }
        }

    }

    private static int quickSortC(int[] arr, int p, int r) {
        int pivot = arr[r];
        int i = p;
        for (int j = p; j < r; j++) {
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

    private static void ensureCapacity(int[][] buckets, int bucketIndex) {
        int[] tempArr = buckets[bucketIndex];
        int[] newArr = new int[tempArr.length * 2];
        for (int j = 0; j < tempArr.length; j++) {
            newArr[j] = tempArr[j];
        }
        buckets[bucketIndex] = newArr;

    }


}
