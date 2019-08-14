package com.zfr.aaron.spring.algorithm.concept.sort;

/**
 * 插入排序
 */
public class InsertSort {


    public static void main(String[] args) {

        int[] ints = insertSort(new int[]{1, 8, 9, 6, 4, 1, 5, 6, 4, 10, 4, 44, 55}, 13);

        for( int i =0 ;i < ints.length ; i++){
            System.out.println(ints[i]);
        }
    }
    public static int[] insertSort(int[] a, int n){
        if(n <= 1){
            return a;
        }
        for(int i =1 ; i < n ; ++i){

            int value = a[i];

            int j = i-1;

            //查找插入位置
            for(;j >= 0 ;--j){

                if(a[j]> value){
                    a[j+1] = a[j];
                }else {
                    break;
                }

            }
            //插入数据
            a[j+1] = value;
        }
        return a ;
    }


}
