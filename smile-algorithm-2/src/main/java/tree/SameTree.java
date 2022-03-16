package tree;

/**
 * @className: SameTree
 * @description: 判断两棵树是否一样
 * @author: luweiming
 * @date: 2022/2/14
 **/
public class SameTree {

    public static Boolean same(TreeNode tree1, TreeNode tree2){
        if (tree1 == null && tree2 == null){
            return Boolean.TRUE;
        }
        if (tree1 == null || tree2 == null){
            return Boolean.FALSE;
        }

        if (tree1.val != tree2.val){
            return Boolean.FALSE;
        }

        return same(tree1.left, tree2.left) &&  same(tree1.right, tree2.right);
    }

    public static TreeNode mirrorTree(TreeNode root){
        if (root == null || root.left == null || root.right == null){
            return root;
        }
        TreeNode tempNode = root.left;
        root.left = root.right;
        root.right = tempNode;
        if (root.left != null){
            mirrorTree(root.left);
        }
        if (root.right != null){
            mirrorTree(root.right);
        }
        return root;
    }

}




class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
}
