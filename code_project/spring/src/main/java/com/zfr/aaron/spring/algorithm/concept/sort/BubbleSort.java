package com.zfr.aaron.spring.algorithm.concept.sort;

/**
 * 冒泡排序
 */
public class BubbleSort {

    public static void main(String[] args) {

        int[] ints = bubbleSort(new int[]{1, 8, 9, 6, 4, 1, 5, 6, 4, 10, 4, 44, 55}, 13);

        for( int i =0 ;i < ints.length ; i++){
            System.out.println(ints[i]);
        }



    }

    public static int[] bubbleSort(int[] a , int n){

        if(n <= 1){
            return a;
        }
        for(int i  =0 ; i <n ;++i){

            //提前退出冒泡循环的标志位
            boolean flag = false;

            for(int j =0 ; j< n -i -1 ; ++j){

                if(a[j] > a[j+1]){

                    int tmp = a[j];

                    a[j] = a[j+1];

                    a[j+1] = tmp;

                    //表示有数据交换
                    flag = true;
                }

            }
            if(!flag){
                break;
            }
        }
        return a;
    }


}
