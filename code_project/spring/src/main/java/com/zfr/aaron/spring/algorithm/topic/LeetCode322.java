package com.zfr.aaron.spring.algorithm.topic;

import java.util.Arrays;

public class LeetCode322 {
    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 5};
        System.out.println(coinChange(a,11));
    }
    public static int coinChange(int[] coins, int amount) {
        if(null == coins || coins.length == 0){
            return 0;
        }
        int max = amount +1;
        int[] dp = new int[max];
        Arrays.fill(dp, max);
        dp[0]=0;
        for(int i =1; i<= amount ; i++){
            for(int j =0;j< coins.length;j++){
                if(coins[j] <= i){
                    dp[i]= Math.min(dp[i],dp[i-coins[j]]+1);
                }
            }
        }
        return (dp[amount] > amount) ? -1 : dp[amount];
    }
}
