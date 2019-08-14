package com.zfr.aaron.spring.algorithm.concept;

import lombok.Data;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 链表实现栈
 */
@Data
public class StackBasedOnLinkedList {

    private Node top = null;

    public void push(int value){

        Node newNode = new Node(value, null);

        //判断是否为空
        if(top == null){
            top = newNode;
        }else{
            newNode.next = top;
            top = newNode;
        }


    }

    /**
     * 我用-1表示栈中没有数据。
     */
    public int pop() {
        if (top == null) {
            return -1;
        }
        int value = top.data;
        top = top.next;
        return value;
    }

    public void printAll() {
        Node p = top;
        while (p != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }



    private static class Node{

        private int data;
        private Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }

    }
    public static void main(String[] args) {

        //ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue();

        StackBasedOnLinkedList arrayStack = new StackBasedOnLinkedList();

        arrayStack.push(1);
        arrayStack.push(2);
        arrayStack.push(3);
        arrayStack.push(4);
        arrayStack.push(5);

        for(int i=0 ,l = 5;i < l;i++){
            System.out.println(arrayStack.pop());
        }


    }
}
