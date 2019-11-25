package com.zfr.aaron.spring.algorithm.topic;

import java.util.Arrays;

/**
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 *
 * 示例:
 *
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 说明:
 *
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n2) 。
 * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
 *
 */
public class LeetCode300 {

    public static void main(String[] args) {

        int[] a = new int[]{10,9,2,5,3,7,101,18};

        System.out.println(lengthOfLIS(a));
    }
    /**
     * 动态规划
     * @param nums
     * @return
     */
    public int lengthOfLIS1(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }

        int res =1;
        int[] dp = new int[nums.length -1];

        for(int i = 0; i <nums.length;i++){
            dp[i]=1;
        }
        for(int i =1 ; i<nums.length; ++i){
            for(int j =0; j<i;++j){
                if(nums[j]<nums[i]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
            res = Math.max(res,dp[i]);
        }


        return res;

    }
     public static int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;
        for (int num : nums) {
            int i = Arrays.binarySearch(dp, 0, len, num);
            if (i < 0) {
                i = -(i + 1);
            }
            dp[i] = num;
            if (i == len) {
                len++;
            }
        }
        return len;
    }


}
