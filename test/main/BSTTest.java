package main;

import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.*;

public class BSTTest {
    private final Integer[] unorderedInts = {5, 1, 9, 4, 7, 2, 8, 6, 3, 0};
    private final Integer[] inOrderInts = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    private final Integer[] preOrderInts = {5, 1, 0, 4, 2, 3, 9, 7, 6, 8};
    private final Integer[] postOrderInts = {0, 3, 2, 4, 1, 6, 8, 7, 9, 5};

    @Test
    public void getInOrder() {
        ArrayList<Integer> resultArrayList;
        Integer[] resultArray;

        BST<Integer> bst = new BST<Integer>();
        for (Integer element : unorderedInts) { bst.insert(element); }
        resultArrayList = bst.getInOrder();
        resultArray = resultArrayList.toArray(new Integer[0]);

        assertEquals(inOrderInts, resultArray);
    }

    @Test
    public void getPreOrder() {
        ArrayList<Integer> resultArrayList;
        Integer[] resultArray;

        BST<Integer> bst = new BST<Integer>();
        for (Integer element : unorderedInts) { bst.insert(element); }
        resultArrayList = bst.getPreOrder();
        resultArray = resultArrayList.toArray(new Integer[0]);

        assertEquals(preOrderInts, resultArray);
    }

    @Test
    public void getPostOrder() {
        ArrayList<Integer> resultArrayList;
        Integer[] resultArray;

        BST<Integer> bst = new BST<Integer>();
        for (Integer element : unorderedInts) { bst.insert(element); }
        resultArrayList = bst.getPostOrder();
        resultArray = resultArrayList.toArray(new Integer[0]);

        assertEquals(postOrderInts, resultArray);
    }

    @Test
    public void find() {
        BST<Integer> bst = new BST<Integer>();
        for (Integer element : unorderedInts) { bst.insert(element); }
        BST<Integer> emtpyBST = new BST<Integer>();

        assertEquals(0, bst.find(5));
        assertEquals(1, bst.find(9));
        assertEquals(2, bst.find(4));
        assertEquals(3, bst.find(6));
        assertEquals(4, bst.find(3));
        assertEquals(-1, bst.find(10));
        assertEquals(-1, emtpyBST.find(8));
    }

    @Test
    public void getMinimum() {
        BST<Integer> bst = new BST<Integer>();
        for (Integer element : unorderedInts) { bst.insert(element); }
        BST<Integer> emtpyBST = new BST<Integer>();

        int minValue = bst.getMinimum();
        int minDepth = bst.find(minValue);

        assertEquals(0, minValue);
        assertEquals(2, minDepth);
        assertNull(emtpyBST.getMinimum());
    }

    @Test
    public void getMaximum() {
        BST<Integer> bst = new BST<Integer>();
        for (Integer element : unorderedInts) { bst.insert(element); }
        BST<Integer> emtpyBST = new BST<Integer>();

        int maxValue = bst.getMaximum();
        int maxDepth = bst.find(maxValue);

        assertEquals(9, maxValue);
        assertEquals(1, maxDepth);
        assertNull(emtpyBST.getMinimum());
    }
}