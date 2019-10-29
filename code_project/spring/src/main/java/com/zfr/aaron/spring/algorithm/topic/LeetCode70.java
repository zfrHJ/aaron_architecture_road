package com.zfr.aaron.spring.algorithm.topic;

public class LeetCode70 {

    /**
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     *
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     *
     * 注意：给定 n 是一个正整数。
     *
     * 示例 1：
     *
     * 输入： 2
     * 输出： 2
     * 解释： 有两种方法可以爬到楼顶。
     * 1.  1 阶 + 1 阶
     * 2.  2 阶
     * 示例 2：
     *
     * 输入： 3
     * 输出： 3
     * 解释： 有三种方法可以爬到楼顶。
     * 1.  1 阶 + 1 阶 + 1 阶
     * 2.  1 阶 + 2 阶
     * 3.  2 阶 + 1 阶
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        int[] nums = new int[n];
        if(n ==0){

            return 0;
        }
        if(n ==1){
            return 1;
        }
        if(n ==2){
            return 2;
        }
        nums[0] =1;

        nums[1] = 2;

        for(int i=2; i< n;i++){
            nums[i] = nums[i-1] + nums[i-2];
        }

        return nums[n-1];
    }

    public int climbStairs1(int n) {
        if( n <= 2){
            return n;
        }
        int one_step = 2;

        int two_step = 1;

        int all_ways = 0;

        for( int i =2; i<n; i++){
            all_ways = one_step + two_step;

            two_step = one_step;

            one_step = all_ways;

        }
        return all_ways;

    }
}
