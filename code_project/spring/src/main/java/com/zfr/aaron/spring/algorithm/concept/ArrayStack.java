package com.zfr.aaron.spring.algorithm.concept;

import lombok.Data;

/**
 * 用数组实现栈
 */
@Data
public class ArrayStack {

    /**
     * 数组
     */
    private String[] items;
    /**
     * 栈中元素个数
     */
    private int count;
    /**
     * 栈的大小
     */
    private int n;

    /**初始化数组，申请一个大小为n的数组空间
     *
     * @param n
     */
    public ArrayStack(int n){
        this.items = new String[n];
        this.n = n;
        this.count = 0;
    }

    /**
     * 入栈
     * @param item
     * @return
     */
    public boolean push(String item){
        //数组空间不够了，直接返回false，入栈失败
        if(count == n){
            return false;
        }
        //将item放到下标为count的位置，并且count 加一
        items[count] = item;

        ++count;

        return true;

    }

    /** 出栈操作
     *
     * @return
     */
    public String pop() {
        // 栈为空，则直接返回 null
        if (count == 0){
            return null;
        }
        // 返回下标为 count-1 的数组元素，并且栈中元素个数 count 减一
        String tmp = items[count-1];
        --count;
        return tmp;
    }

    public static void main(String[] args) {

        ArrayStack arrayStack = new ArrayStack(5);

        arrayStack.push("1");
        arrayStack.push("2");
        arrayStack.push("3");
        arrayStack.push("4");
        arrayStack.push("5");

        for(int i=0 ,l = 5;i < l;i++){
            System.out.println(arrayStack.pop());
        }


    }

}
