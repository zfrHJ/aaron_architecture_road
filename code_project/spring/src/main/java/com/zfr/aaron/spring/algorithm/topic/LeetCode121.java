package com.zfr.aaron.spring.algorithm.topic;

public class LeetCode121 {




    public int maxProfit(int prices[]) {
        if(null == prices){
            return 0;
        }
        if(prices.length <= 0){
            return 0;
        }
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice)
                minPrice = prices[i];
            else if (prices[i] - minPrice > maxProfit)
                maxProfit = prices[i] - minPrice;
        }
        return maxProfit;
    }



}
