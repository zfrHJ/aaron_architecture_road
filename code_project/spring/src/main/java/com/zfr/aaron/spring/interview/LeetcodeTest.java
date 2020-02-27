package com.zfr.aaron.spring.interview;

import java.util.ArrayList;
import java.util.List;

public class LeetcodeTest {

    public static void main(String[] args) {

        int[] nums = new  int[]{1,2,3};

        System.out.println(enumerate(nums));
    }
    public static List<List<Integer>> enumerate(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        res.add(new ArrayList<Integer>());
        for (Integer n : nums) {
            int size = res.size();
            for (int i = 0; i < size; i++) {
                List<Integer> newSub = new ArrayList<Integer>(res.get(i));
                newSub.add(n);
                res.add(newSub);
            }
        }
        return res;
    }

}
