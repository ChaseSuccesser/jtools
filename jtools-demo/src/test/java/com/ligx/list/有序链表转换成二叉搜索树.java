package com.ligx.list;

/**
 * @author: ligongxing.
 * @date: 2021年04月13日.
 */
public class 有序链表转换成二叉搜索树 {

    public TreeNode sortedListToAst(Link head) {
        return rec(head, null);
    }

    public TreeNode rec(Link start, Link end) {
        if (start == end) {
            return null;
        }
        Link mid = start;
        Link trail = start;
        while (trail != end && trail.next != end) {
            mid = mid.next;
            trail = trail.next.next;
        }

        TreeNode root = new TreeNode(mid.data);
        root.left = rec(start, mid);
        root.right = rec(mid.next, end);
        return root;
    }
}
