package com.zfr.aaron.spring.algorithm.topic;

public class Topic104 {
    public int minDepth(Topic98.TreeNode root) {

        if(root == null){
            return 0;
        }else{

            int minLeft = minDepth(root.left);
            int minRight= minDepth(root.right);

            return  (minLeft == 0 || minRight == 0) ? minLeft + minRight + 1 : Math.max(minLeft,minRight) + 1;

        }

    }
}
