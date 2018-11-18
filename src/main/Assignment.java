package main;

public class Assignment {
    private BST<Integer> bst;

    public static void main(String[] args) {
        int selection = -1;
        BST<Integer> bst = new BST<Integer>();

        while (selection != 0) {
            selection = getMenuSelection(bst);
        }
    }

    private static int getMenuSelection(BST<Integer> bst) {
        return 0;
    }
}
