package com.zfr.aaron.spring.algorithm.concept;

public class CircularQueue {
    /**
     * 数组：items，数组大小：n
     */
    private String[] items;
    private int n = 0;
    /**
     * head表示队头下标，tail表示队尾下标
     */
    private int head = 0;
    private int tail = 0;

    /**
     * 申请一个大小为capacity的数组
     * @param capacity
     */
    public CircularQueue(int capacity) {
        items = new String[capacity];
        //如果不加1，会导致容量不足
        n = capacity + 1;
    }

    /**
     * 入队
     * @param item
     * @return
     */
    public boolean enqueue(String item) {
        // 队列满了
        if ((tail + 1) % n == head) {
            return false;
        }
        items[tail] = item;
        tail = (tail + 1) % n;
        return true;
    }

    /**
     * 出队
     * @return
     */
    public String dequeue() {
        // 如果head == tail 表示队列为空
        if (head == tail) {
            return null;
        }
        String ret = items[head];
        head = (head + 1) % n;
        return ret;
    }

    public void printAll() {
        if (0 == n) {
            return;
        }
        for (int i = head; i % n != tail; ++i) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        CircularQueue circularQueue = new CircularQueue(5);
        circularQueue.enqueue("1");
        circularQueue.enqueue("2");
        circularQueue.enqueue("3");
        circularQueue.enqueue("4");
        circularQueue.enqueue("5");

        circularQueue.printAll();

    }
}
