package com.zfr.aaron.spring.algorithm.concept.sort;

/**
 * 合并排序
 */
public class MergeSort {


    public static void main(String[] args) {
        int[] ints = mergeSortInternally(new int[]{1, 8, 9, 6, 4, 1, 5, 6, 4, 10, 4, 44, 55}, 0,12);

        for( int i =0 ;i < ints.length ; i++){
            System.out.println(ints[i]);
        }
    }

    public static int[] mergeSortInternally(int[] a , int p , int r){

        //递归终止条件
        if(p >= r){
            return null;
        }
        //取p到r之间的中间位置，防止(p+r)的和超过int类型最大值
        int q = p+(r-p)/2;

        mergeSortInternally(a,p,q);
        mergeSortInternally(a,q+1,r);
        //合并
        merge(a,p,q,r);


        return a;
    }

    private static void merge(int[] a, int p, int q, int r) {
        int i = p;
        int j = q+1;
        //初始化变量i,j,k
        int k =0;
        //申请一个大小跟a[p...r]一样的临时数组
        int[] tmp = new int[r-p+1];
        while(i<= q && j<=r){
            if(a[i] <= a[j]){
                tmp[k++] = a[i++];
            }else{
                tmp[k++] = a[j++];
            }
        }
        // 判断哪个子数组中有剩余的数据
        int start = i;
        int end = q;
        if(j <= r){
            start = j;
            end =r;
        }
        // 将剩余的数据拷贝到临时数组tmp
        while(start <= end){
            tmp[k++] = a[start++];
        }
        // 将tmp中的数组拷贝回a[p...r]
        for( i =0;i <= r-p; ++i){
            a[p+i] = tmp[i];
        }

    }


}
