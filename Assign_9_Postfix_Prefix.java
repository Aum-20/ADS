package ADS;
import java.util.*;
public class Assign_2 {
    char value;
    Assign_2 left, right;

    public Assign_2(char item) {
        value = item;
        left = right = null;
    }
}

class ExpressionTree {
    public static Assign_2 constructExpressionTree(String postfix) {
        Stack<Assign_2> stack = new Stack<>();
        char[] chars = postfix.toCharArray();

        for (char c : chars) {
            if (Character.isLetter(c)) {
                stack.push(new Assign_2(c));
            } else {
                Assign_2 node = new Assign_2(c);
                node.right = stack.pop();
                node.left = stack.pop();
                stack.push(node);
            }
        }

        return stack.pop();
    }

    public static void recursiveInorder(Assign_2 root) {
        if (root != null) {
            recursiveInorder(root.left);
            System.out.print(root.value + " ");
            recursiveInorder(root.right);
        }
    }

    public static void recursivePreorder(Assign_2 root) {
        if (root != null) {
            System.out.print(root.value + " ");
            recursivePreorder(root.left);
            recursivePreorder(root.right);
        }
    }

    public static void recursivePostorder(Assign_2 root) {
        if (root != null) {
            recursivePostorder(root.left);
            recursivePostorder(root.right);
            System.out.print(root.value + " ");
        }
    }

    public static void nonRecursiveInorder(Assign_2 root) {
        Stack<Assign_2> stack = new Stack<>();
        Assign_2 current = root;

        while (true) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            if (stack.isEmpty()) break;

            current = stack.pop();
            System.out.print(current.value + " ");
            current = current.right;
        }
    }

    public static void nonRecursivePreorder(Assign_2 root) {
        if (root == null) return;

        Stack<Assign_2> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Assign_2 current = stack.pop();
            System.out.print(current.value + " ");

            if (current.right != null) stack.push(current.right);
            if (current.left != null) stack.push(current.left);
        }
    }

    public static void nonRecursivePostorder(Assign_2 root) {
        if (root == null) return;

        Stack<Assign_2> stack = new Stack<>();
        Stack<Character> result = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Assign_2 current = stack.pop();
            result.push(current.value);

            if (current.left != null) stack.push(current.left);
            if (current.right != null) stack.push(current.right);
        }

        while (!result.isEmpty()) {
            System.out.print(result.pop() + " ");
        }
    }

    public static void main(String[] args) {
        String postfixExpression = "ab+ef*g*-";
        Assign_2 root = constructExpressionTree(postfixExpression);

        System.out.println("Recursive In-order Traversal:");
        recursiveInorder(root);
        System.out.println("\nRecursive Pre-order Traversal:");
        recursivePreorder(root);
        System.out.println("\nRecursive Post-order Traversal:");
        recursivePostorder(root);

        System.out.println("\nNon-Recursive In-order Traversal:");
        nonRecursiveInorder(root);
        System.out.println("\nNon-Recursive Pre-order Traversal:");
        nonRecursivePreorder(root);
        System.out.println("\nNon-Recursive Post-order Traversal:");
        nonRecursivePostorder(root);
    }
}
