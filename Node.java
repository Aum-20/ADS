package ADS;
import java.util.*;
public class Node {
    int data;
    Node left, right;
    boolean rightThread;

    public Node(int data) {
        this.data = data;
        this.left = right = null;
        this.rightThread = false;
    }
}

    class Assignment2 {
        Node root = null;

        // Function to perform inorder traversal
        public void inorder() {
            Node current = leftMost(root);
            while (current != null) {
                System.out.print(current.data + " ");
                if (current.rightThread)
                    current = current.right;
                else
                    current = leftMost(current.right);
            }
        }

        // Function to find the leftmost node in a subtree
        private Node leftMost(Node node) {
            if (node == null)
                return null;

            while (node.left != null)
                node = node.left;

            return node;
        }

        // Function to insert a node in the binary tree
        public void insert(int data) {
            Node newNode = new Node(data);

            if (root == null) {
                root = newNode;
                return;
            }

            Node current = root;

            while (true) {
                if (data < current.data) {
                    if (current.left == null) {
                        current.left = newNode;
                        newNode.rightThread = true;
                        newNode.right = current;
                        return;
                    }
                    current = current.left;
                } else {
                    if (current.rightThread) {
                        newNode.right = current.right;
                        current.right = newNode;
                        current.rightThread = false;
                        newNode.rightThread = true;
                        return;
                    }
                    if (current.right == null) {
                        current.right = newNode;
                        return;
                    }
                    current = current.right;
                }
            }
        }

        public static void main(String args[]) {
            Scanner sc = new Scanner(System.in);
            Assignment2 tree1 = new Assignment2(); // Assuming initial root data as 0
            System.out.println("\t*****Threaded BST operations*****\t");
            System.out.println("\n-----------------------------------------");
            boolean x = true;
            while (x) {
                System.out.println("Operations Available : ");
                System.out.println(
                        "1. Insert the data to TBT \n2. Inorder print \n3. Exit");
                System.out.println("First insert the data then perform another operations.");
                System.out.println("\n-----------------------------------------");
                System.out.print("Enter your choice : ");
                int choice = sc.nextInt();
                if (choice < 1 || choice > 3) {
                    System.out.println("Please, Enter the choice between 1 to 3.");
                }

                switch (choice) {
                    case 1:
                        System.out.print("Enter the data (-1 to stop) : ");
                        int data = sc.nextInt();
                        while (data != -1) {
                            tree1.insert(data);
                            data = sc.nextInt();
                        }
                        System.out.println("The data get successfully inserted.");
                        System.out.println("\n-----------------------------------------");
                        break;

                    case 2:
                        System.out.println("The inorder traversal is : ");
                        tree1.inorder();
                        System.out.println("\n-----------------------------------------");
                        break;

                    case 3:
                        x = false;
                        break;
                }
            }
        }
    }


