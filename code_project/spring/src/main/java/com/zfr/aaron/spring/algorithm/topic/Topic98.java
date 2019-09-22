package com.zfr.aaron.spring.algorithm.topic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 *
 * 假设一个二叉搜索树具有如下特征：
 *
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。

 */
public class Topic98 {

    public boolean isValidBST(TreeNode root) {

        if(root == null){
            return false;
        }

        List<Integer> list = new ArrayList();

        List<Integer> list1 = list;

        isTrue(root, list);

        Arrays.sort(list.toArray());


        if(list.toString().equals(list1.toString()) ){
            return true;
        }

        return false;


    }

    public void isTrue(TreeNode root,List<Integer> list){

        if(root==null){
            return;
        }
        isTrue(root.left,list);

        list.add(root.val);

        isTrue(root.right,list);

    }

    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
}
