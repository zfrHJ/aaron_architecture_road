package com.zfr.aaron.spring.algorithm.topic;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。

 说明：解集不能包含重复的子集。

 示例:

 输入: nums = [1,2,3]
 输出:
 [
 [3],
   [1],
   [2],
   [1,2,3],
   [1,3],
   [2,3],
   [1,2],
   []
 ]
 */
public class LeetCode78 {


    public static void main(String[] args) {
        int[] a = new int[]{1,2,3};
        System.out.println(enumerate(a));
    }

        public List<List<Integer>> subsets(int[] nums) {

            if(null == nums || nums.length == 0){
                return null;
            }




            return null;
        }

    /**
     * 循环枚举
     */
    public static List<List<Integer>> enumerate(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        for (Integer n : nums) {
            int size = res.size();
            for (int i = 0; i < size; i++) {
                List<Integer> newSub = new ArrayList<>(res.get(i));
                newSub.add(n);
                res.add(newSub);
            }
        }
        return res;
    }


}
