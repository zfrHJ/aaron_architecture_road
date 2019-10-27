package com.zfr.aaron.spring.algorithm.topic;

import lombok.Data;

/**
 * 208:实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 */
public class LeetCode208 {


    private TrieNode root;

    /** Initialize your data structure here. */
    public LeetCode208() {

        root = new TrieNode();
        root.val =' ';
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode ws = root;
        for(int i =0; i < word.length(); i++){
            char c = word.charAt(i);
            if(ws.children[c -'a'] == null){
                ws.children[c -'a'] = new TrieNode(c);
            }
            ws = ws.children[c -'a'];
        }
        ws.isWord = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode ws = root;
        for(int i= 0 ; i < word.length(); i++){
            char c = word.charAt(i);
            if(ws.children[c -'a'] == null ){
                return false;
            }
            ws = ws.children[c -'a'];
        }
        return ws.isWord;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode ws = root;
        for(int i= 0 ; i < prefix.length(); i++){
            char c = prefix.charAt(i);
            if(ws.children[c -'a'] == null ){
                return false;

            }
            ws = ws.children[c -'a'];

        }
        return true;
    }





    @Data
    class TrieNode{
        public char val;
        public boolean isWord;
        public TrieNode[] children = new TrieNode[26];
        public TrieNode(){}
        TrieNode(char c){
            TrieNode node = new TrieNode();
            node.val = c;
        }
    }

    class TrieNode1 {

        // R links to node children
        private TrieNode[] links;

        private final int R = 26;

        private boolean isEnd;

        public TrieNode1() {
            links = new TrieNode[R];
        }

        public boolean containsKey(char ch) {
            return links[ch -'a'] != null;
        }
        public TrieNode get(char ch) {
            return links[ch -'a'];
        }
        public void put(char ch, TrieNode node) {
            links[ch -'a'] = node;
        }
        public void setEnd() {
            isEnd = true;
        }
        public boolean isEnd() {
            return isEnd;
        }
    }


}
