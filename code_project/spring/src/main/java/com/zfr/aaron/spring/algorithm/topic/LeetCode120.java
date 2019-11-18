package com.zfr.aaron.spring.algorithm.topic;

import java.util.List;

public class LeetCode120 {

    public static void main(String[] args) {

    }


    /**
     * 这个解法是有问题的
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        if(null == triangle){
            return 0;
        }
        if(triangle.size() <= 0){
            return 0;
        }

        int[] mini = new int[triangle.size()];

        for (int i = triangle.size() -2; i>=0 ; --i){
            for(int j= 0; j< triangle.get(i).size(); ++i){
                mini[j] = triangle.get(i).get(j) + Math.min( mini[j], mini[j+1]);
            }
        }

        return mini[0];
    }

    /**
     * 这个解法是正确的
     * @param triangle
     * @return
     */
    public int min(List<List<Integer>> triangle){
        int row = triangle.size();
        int[] minlen = new int[row+1];
        for (int level = row-1;level>=0;level--){
            for (int i = 0;i<=level;i++){   //第i行有i+1个数字
                //为什么是有一个一位数组主要是为了减轻存储空间
                minlen[i] = Math.min(minlen[i], minlen[i+1]) + triangle.get(level).get(i);
            }
        }
        return minlen[0];


    }
}
