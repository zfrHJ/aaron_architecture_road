package com.zfr.aaron.spring.algorithm.topic;

public class LeetCode231 {

    /**
     * 给定一个整数，编写一个函数来判断它是否是 2 的幂次方。
     *
     * 示例 1:
     *
     * 输入: 1
     * 输出: true
     * 解释: 20 = 1
     * 示例 2:
     *
     * 输入: 16
     * 输出: true
     * 解释: 24 = 16
     * 示例 3:
     *
     * 输入: 218
     * 输出: false
     *
     * @param n
     * @return
     */
    public static boolean isPowerOfTwo(int n) {

        if(n <= 0){
            return false;
        }

        int i = n & (n - 1);

        if( i == 0){
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        int[] ints = countBits(5);
        System.out.println(ints);
    }

    /**
     * 338 :给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
     *
     * 示例 1:
     *
     * 输入: 2
     * 输出: [0,1,1]
     * 示例 2:
     *
     * 输入: 5
     * 输出: [0,1,1,2,1,2]
     *
     * @param num
     * @return
     */
    public static int[] countBits(int num) {

        int[] bits = new int[num +1];

        for(int i =1; i<= num ;i++){
            bits[i] += bits[i &(i-1)] +1;
        }

        return bits;
    }
}
