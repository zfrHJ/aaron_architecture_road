package com.zfr.aaron.spring.algorithm.topic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Topic102 {
    public List<List<Integer>> levelOrder(Topic98.TreeNode root) {

        List<List<Integer>> list  = new ArrayList<>();

        if(root == null){
            return list;
        }

        Queue<Topic98.TreeNode> q = new LinkedList<>();

        q.add(root);

        while(!q.isEmpty()){

            int size = q.size();

            List<Integer> data = new ArrayList<>();

            for(int i = 0; i < size; i++ ){

                Topic98.TreeNode node = q.poll();

                data.add(node.val);

                if(null != node.left){
                    q.add(node.left);
                }
                if(null != node.right){
                    q.add(node.right);
                }
            }
            list.add(data);

        }

        return list;




    }
}
