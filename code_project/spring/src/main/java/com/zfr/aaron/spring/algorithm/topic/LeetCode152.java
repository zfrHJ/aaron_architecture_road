package com.zfr.aaron.spring.algorithm.topic;



public class LeetCode152 {
    public static void main(String[] args) {

        int[] a = new int[]{-1,-2,-9,-6};

        System.out.println(maxProduct(a));
    }
    public int maxProduct1(int[] nums) {
        int curMax = nums[0];
        int curMin = nums[0];
        int lastMax = nums[0];
        int lastMin = nums[0];
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            curMax = Math.max(Math.max(nums[i] * lastMax, nums[i] * lastMin), nums[i]);
            curMin = Math.min(Math.min(nums[i] * lastMax, nums[i] * lastMin), nums[i]);
            lastMin = curMin;
            lastMax = curMax;
            res = Math.max(res, curMax);
        }
        return res;
    }

    public static int maxProduct(int[] nums) {

        if(null == nums){
            return 0;
        }
        if(nums.length <=0){
            return 0;
        }
        int result = nums[0];
        int curMax = nums[0];
        int curMin = nums[0];
        int num;
        for(int i=1 ; i< nums.length ;i++){
            num = nums[i];
            curMax = curMax * num;
            curMin = curMin * num;
            //特别注意这个，如果全部是复数，不加下面这个有bug
            if(num < 0){
                int tmp = curMax;
                curMax = curMin;
                curMin = tmp;
            }
            curMax = Math.max(Math.max(curMax,curMin),num);
            curMin = Math.min(Math.min(curMax,curMin),num);
            if(curMax > result ){
                result = curMax;
            }
        }
        return result;


    }
}
