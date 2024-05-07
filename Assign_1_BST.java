package ADS;
import java.util.*;
class TreeNode_Assign_1 {
    int val;
    TreeNode_Assign_1 left, right;

    public TreeNode_Assign_1(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }

    static class BinarySearchTree {
        private TreeNode_Assign_1 root;

        public BinarySearchTree() {
            this.root = null;
        }

        public void insert(int key) {
            root = insertRec(root, key);
        }

        private TreeNode_Assign_1 insertRec(TreeNode_Assign_1 root, int key) {
            if (root == null) {
                root = new TreeNode_Assign_1(key);
                return root;
            }

            if (key < root.val)
                root.left = insertRec(root.left, key);
            else if (key > root.val)
                root.right = insertRec(root.right, key);

            return root;
        }

        public void delete(int key) {
            root = deleteRec(root, key);
        }

        private TreeNode_Assign_1 deleteRec(TreeNode_Assign_1 root, int key) {
            if (root == null)
                return root;

            if (key < root.val)
                root.left = deleteRec(root.left, key);
            else if (key > root.val)
                root.right = deleteRec(root.right, key);
            else {
                if (root.left == null)
                    return root.right;
                else if (root.right == null)
                    return root.left;

                root.val = minValue(root.right);
                root.right = deleteRec(root.right, root.val);
            }

            return root;
        }

        private int minValue(TreeNode_Assign_1 root) {
            int minv = root.val;
            while (root.left != null) {
                minv = root.left.val;
                root = root.left;
            }
            return minv;
        }

        public void inorderTraversal() {
            System.out.print("Inorder Traversal: ");
            inorderTraversalRec(root);
            System.out.println();
        }

        private void inorderTraversalRec(TreeNode_Assign_1 root) {
            if (root != null) {
                inorderTraversalRec(root.left);
                System.out.print(root.val + " ");
                inorderTraversalRec(root.right);
            }
        }

        public void levelOrderTraversal() {
            System.out.print("Level Order Traversal: ");
            if (root == null)
                return;

            Queue<TreeNode_Assign_1> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                TreeNode_Assign_1 node = queue.poll();
                System.out.print(node.val + " ");
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            System.out.println();
        }
    }
}

 class Main {
    public static void main(String[] args) {
        TreeNode_Assign_1.BinarySearchTree bst = new TreeNode_Assign_1.BinarySearchTree();
        bst.insert(50);
        bst.insert(30);
        bst.insert(20);
        bst.insert(40);
        bst.insert(70);
        bst.insert(60);
        bst.insert(80);

        bst.inorderTraversal(); // Display inorder
        bst.levelOrderTraversal(); // Display level order

        bst.delete(20);
        bst.delete(30);
        bst.inorderTraversal(); // Updated inorder after deletion
    }
}

