package com.example.dsdemo0.entity.pojo;

public class insert {
    TreeNode.Node root = null;
    public boolean insert(int val) {
        TreeNode.Node bsNode = new TreeNode.Node(val);
        if(root == null) {
            root = bsNode;
            return true;
        }
        TreeNode.Node cur = root;
        TreeNode.Node parent = null;

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
}
