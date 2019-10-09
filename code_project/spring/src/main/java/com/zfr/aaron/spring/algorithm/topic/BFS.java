package com.zfr.aaron.spring.algorithm.topic;

import java.util.LinkedList;

/**
 * 广度优先遍历
 */
public class BFS {


    /**
     * 实现
     * @param s
     * @param t
     */
    public void bfs(int s ,int t){
        if( s ==t){
            return ;
        }
        boolean[] visited =  new boolean[10];



    }
    public class Graph { // 无向图
        private int v; // 顶点的个数
        private LinkedList<Integer> adj[]; // 邻接表

        public Graph(int v) {
            this.v = v;
            adj = new LinkedList[v];
            for (int i=0; i<v; ++i) {
                adj[i] = new LinkedList<>();
            }
        }

        public void addEdge(int s, int t) { // 无向图一条边存两次
            adj[s].add(t);
            adj[t].add(s);
        }
    }


}
