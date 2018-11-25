package main;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Assignment {
    public static void main(String[] args) {
        int selection = -1;
        BST<Integer> bst = new BST<Integer>();

        while (selection != 0) {
            selection = getMenuSelection();
            switch (selection) {
                case 1: insert(bst); break;
                case 2: delete(bst); break;
                case 3: find(bst); break;
                case 4: minimum(bst); break;
                case 5: maximum(bst); break;
                case 6: successor(bst); break;
                case 7: predecessor(bst); break;
                case 8: print(bst); break;
                case 9: clear(bst); break;
            }
        }
    }

    private static int getMenuSelection() {
        String menu;
        menu = "\nPress a number key corresponding to one of the selections below:\n" +
                "  (1) Insert a key\n" +
                "  (2) Delete a key\n" +
                "  (3) Search for a key\n" +
                "  (4) Display the minimum key\n" +
                "  (5) Display the maximum key\n" +
                "  (6) Display the successor of a key\n" +
                "  (7) Display the predecessor of a key\n" +
                "  (8) Print the tree\n" +
                "  (9) Clear the tree\n" +
                "  (0) Exit the program\n";
        System.out.print(menu);
        return getInt();
    }

    private static void insert(BST<Integer> bst) {
        System.out.print("\nInsert a key\n");
        int key = getInt();
        int depth = bst.find(key);
        if (depth == -1) {
            bst.insert(key);
            depth = bst.find(key);
            System.out.println("Inserted key at depth = " + Integer.toString(depth));
        }
        else {
            System.out.println("Key already in tree at depth = " + Integer.toString(depth));
        }
    }

    private static void delete(BST<Integer> bst) {
        System.out.print("\nDelete a key\n");
        int key = getInt();
        if (bst.delete(key)) { System.out.println("Key deleted succesfully" ); }
        else { System.out.println("Key not in tree"); }
    }

    private static void find(BST<Integer> bst) {
        System.out.print("\nSearch for a key\n");
        int key = getInt();
        int depth = bst.find(key);
        if (depth == -1) { System.out.println("Key not in tree"); }
        else { System.out.println("Key found at depth = " + Integer.toString(depth)); }
    }

    private static void minimum(BST<Integer> bst) {
        System.out.print("\nDisplay the minimum key\n");
        Integer minimum = bst.getMinimum();
        if (minimum == null) { System.out.println("Tree is empty"); }
        else {
            int depth = bst.find(minimum);
            System.out.println("Found " + minimum + " at depth = " + depth);
        }
    }

    private static void maximum(BST<Integer> bst) {
        System.out.print("\nDisplay the maximum key\n");
        Integer maximum = bst.getMaximum();
        if (maximum == null) { System.out.println("Tree is empty"); }
        else {
            int depth = bst.find(maximum);
            System.out.println("Found " + maximum + " at depth = " + depth);
        }
    }

    private static void successor(BST<Integer> bst) {
        System.out.print("\nDisplay the successor of a key\n");
        int key = getInt();
        Integer successor = bst.getSuccessor(key);
        if (successor == null)
            System.out.println("No successor can be found");
        else {
            int depth = bst.find(successor);
            System.out.println("Successor is " + successor + " at depth = " + depth);
        }
    }

    private static void predecessor(BST<Integer> bst) {
        System.out.print("\nDisplay the predecessor of a key\n");
        int key = getInt();
        Integer predecessor = bst.getPredecessor(key);
        if (predecessor == null)
            System.out.println("No predecessor can be found");
        else {
            int depth = bst.find(predecessor);
            System.out.println("Successor is " + predecessor + " at depth = " + depth);
        }
    }

    private static void print(BST<Integer> bst) {
        System.out.print("\nPrint the tree");
        System.out.print("\nIn order:   ");
        printArrayList(bst.getInOrder());
        System.out.print("\nPre order:  ");
        printArrayList(bst.getPreOrder());
        System.out.print("\nPost order: ");
        printArrayList(bst.getPostOrder());
        System.out.println();
    }

    private static void clear(BST<Integer> bst) {
        System.out.print("\nClear the tree\n");
        bst.clearTree();
        System.out.println("Tree cleared successfully");
    }

    private static void printArrayList(ArrayList<Integer> list) {
        for (Integer element : list) {
            System.out.print(element + " ");
        }
    }

    private static int getInt() {
        Scanner in = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("Input -> ");
                return in.nextInt();
            }
            catch (InputMismatchException exception) {
                in = new Scanner(System.in);  // try again
            }
        }
    }
}
