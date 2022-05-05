package com.example.dsdemo0.entity.pojo;

public class TreeNode {

    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node(int val) {
            this.val = val;
        }
    }
    public Node root = null;

    public Node search(int val) {
        if(root == null) return null;
        Node cur = root;
        while (cur != null) {
            if(cur.val == val) {
                return cur;
            }else if(cur.val < val) {
                cur = cur.right;
            }else {
                cur = cur.left;
            }
        }
        return null;
    }

    public boolean insert(int val) {
        Node bsNode = new Node(val);
        if(root == null) {
            root = bsNode;
            return true;
        }
        Node cur = root;
        Node parent = null;

        while (cur != null) {
            if(cur.val == val) {
                return false;
            }else if(cur.val < val) {
                parent = cur;
                cur = cur.right;
            }else {
                parent = cur;
                cur = cur.left;
            }
        }
        if(parent.val < val) {
            parent.right = bsNode;
        }else {
            parent.left = bsNode;
        }
        return true;
    }

    public boolean remove(int val) {
        Node cur = root;
        Node parent = null;
        while (cur != null) {
            if (val == cur.val) {
                break;
            } else if (val < cur.val) {
                parent = cur;
                cur = cur.left;
            }else {
                parent = cur;
                cur = cur.right;
            }
        }
        // 该元素不在二叉搜索树中
        if(null == cur){
            return false;
        }
        return true;
    }
}






