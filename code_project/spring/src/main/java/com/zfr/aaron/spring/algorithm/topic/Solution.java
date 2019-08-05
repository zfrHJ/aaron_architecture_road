package com.zfr.aaron.spring.algorithm.topic;

/**
 * 求两数之和
 */
public class Solution {


    public static void main(String[] args) {

        int[] a = {2, 7, 11, 15};

        int[] ints = twoSum1(a, 9);
        System.out.println(ints);

        System.out.println();
    }

    static public int[] twoSum1(int[] numbers, int target) {

        int low = 0, high = numbers.length - 1;
        while (low < high) {
            int sum = numbers[low] + numbers[high];
            if (sum == target)
                return new int[]{low + 1, high + 1};
            else if (sum < target)
                ++low;
            else
                --high;

        }
        return new int[]{-1, -1};
    }



    //方法一
    static public int[] twoSum(int[] nums, int target) {

        for(int i =0; i< nums.length ; i++){
            for(int j = i +1 ; j < nums.length ; j++){
                if(target == nums[i] + nums[j]){
                    return new int[]{i,j};
                }
            }
        }
        return new int[0];
    }
}
