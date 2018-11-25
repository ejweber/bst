package main;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.testng.Assert.*;

public class BSTTest {
    private final Integer[] unorderedInts = {5, 1, 9, 4, 7, 2, 8, 6, 3, 0};
    private final Integer[] inOrderInts = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    private final Integer[] preOrderInts = {5, 1, 0, 4, 2, 3, 9, 7, 6, 8};
    private final Integer[] postOrderInts = {0, 3, 2, 4, 1, 6, 8, 7, 9, 5};
    private final Integer[] extendedUnorderedInts = {5, 1, 9, 4, 7, 2, 8, 6, 3, 0, 11, 10, 12};
    private final Integer[] extendedInOrderInts = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

    @Test
    public void getInOrder() {
        ArrayList<Integer> resultArrayList;
        Integer[] resultArray;
        BST<Integer> emtpyBST = new BST<Integer>();

        BST<Integer> bst = new BST<Integer>();
        for (Integer element : unorderedInts) {
            bst.insert(element);
        }
        resultArrayList = bst.getInOrder();
        resultArray = resultArrayList.toArray(new Integer[0]);

        emtpyBST.getInOrder();  // ensure an empty tree doesn't break things
        assertEquals(inOrderInts, resultArray);
    }

    @Test
    public void getPreOrder() {
        ArrayList<Integer> resultArrayList;
        Integer[] resultArray;
        BST<Integer> emptyBST = new BST<Integer>();

        BST<Integer> bst = new BST<Integer>();
        for (Integer element : unorderedInts) {
            bst.insert(element);
        }
        resultArrayList = bst.getPreOrder();
        resultArray = resultArrayList.toArray(new Integer[0]);

        emptyBST.getInOrder();  // ensure an empty tree doesn't break things
        assertEquals(preOrderInts, resultArray);
    }

    @Test
    public void getPostOrder() {
        ArrayList<Integer> resultArrayList;
        Integer[] resultArray;
        BST<Integer> emptyBST = new BST<Integer>();

        BST<Integer> bst = new BST<Integer>();
        for (Integer element : unorderedInts) {
            bst.insert(element);
        }
        resultArrayList = bst.getPostOrder();
        resultArray = resultArrayList.toArray(new Integer[0]);

        emptyBST.getInOrder();  // ensure an empty tree doesn't break things
        assertEquals(postOrderInts, resultArray);
    }

    @Test
    public void find() {
        BST<Integer> bst = new BST<Integer>();
        for (Integer element : unorderedInts) {
            bst.insert(element);
        }
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
        for (Integer element : unorderedInts) {
            bst.insert(element);
        }
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
        for (Integer element : unorderedInts) {
            bst.insert(element);
        }
        BST<Integer> emtpyBST = new BST<Integer>();

        int maxValue = bst.getMaximum();
        int maxDepth = bst.find(maxValue);

        assertEquals(9, maxValue);
        assertEquals(1, maxDepth);
        assertNull(emtpyBST.getMinimum());
    }

    @Test
    public void getSuccessor() {
        BST<Integer> bst = new BST<Integer>();
        for (Integer element : unorderedInts) {
            bst.insert(element);
        }
        BST<Integer> emtpyBST = new BST<Integer>();

        assertEquals(1, (int) bst.getSuccessor(0));
        assertEquals(2, (int) bst.getSuccessor(1));
        assertEquals(3, (int) bst.getSuccessor(2));
        assertEquals(4, (int) bst.getSuccessor(3));
        assertEquals(5, (int) bst.getSuccessor(4));
        assertEquals(6, (int) bst.getSuccessor(5));
        assertEquals(7, (int) bst.getSuccessor(6));
        assertEquals(8, (int) bst.getSuccessor(7));
        assertEquals(9, (int) bst.getSuccessor(8));
        assertNull(bst.getSuccessor(9));
        assertNull(bst.getSuccessor(10));
        assertNull(emtpyBST.getSuccessor(5));
    }

    @Test
    public void getPredecessor() {
        BST<Integer> bst = new BST<Integer>();
        for (Integer element : unorderedInts) {
            bst.insert(element);
        }
        BST<Integer> emtpyBST = new BST<Integer>();

        assertEquals(0, (int) bst.getPredecessor(1));
        assertEquals(1, (int) bst.getPredecessor(2));
        assertEquals(2, (int) bst.getPredecessor(3));
        assertEquals(3, (int) bst.getPredecessor(4));
        assertEquals(4, (int) bst.getPredecessor(5));
        assertEquals(5, (int) bst.getPredecessor(6));
        assertEquals(6, (int) bst.getPredecessor(7));
        assertEquals(7, (int) bst.getPredecessor(8));
        assertEquals(8, (int) bst.getPredecessor(9));
        assertNull(bst.getPredecessor(0));
        assertNull(bst.getPredecessor(10));
        assertNull(emtpyBST.getPredecessor(5));
    }

    @Test
    public void delete() {
        BST<Integer> bst = new BST<Integer>();
        for (Integer element : extendedUnorderedInts) {
            bst.insert(element);
        }
        ArrayList<Integer> answer = new ArrayList<Integer>(Arrays.asList(extendedInOrderInts));
        ArrayList<Integer> result;
        boolean deleted;

        // verify that deleting a non-existing node doesn't break anything
        deleted = bst.delete(27);
        assertFalse(deleted);

        deleted = bst.delete(9);
        answer.remove(new Integer(9));
        result = bst.getInOrder();
        assertEquals(result, answer);
        assertTrue(deleted);

        deleted = bst.delete(10);
        answer.remove(new Integer(10));
        result = bst.getInOrder();
        assertEquals(result, answer);
        assertTrue(deleted);

        deleted = bst.delete(2);
        answer.remove(new Integer(2));
        result = bst.getInOrder();
        assertEquals(result, answer);
        assertTrue(deleted);

        deleted = bst.delete(4);
        answer.remove(new Integer(4));
        result = bst.getInOrder();
        assertEquals(result, answer);
        assertTrue(deleted);

        // delete the rest of the nodes and attempt to find a maximum
        for (Integer element : answer)
            bst.delete(element);
        assertNull(bst.getMaximum());

    }

    @Test
    public void nonDefaultConstructor() {
        BST<Integer> bst = new BST<Integer>(5);
        bst.insert(7);

        assertEquals(bst.find(5), 0);
        assertEquals(bst.find(7), 1);
        assertEquals(bst.find(12), -1);
    }

    @Test
    public void clearTree() {
        BST<Integer> bst = new BST<Integer>();
        for (Integer element : unorderedInts) { bst.insert(element); }
        bst.clearTree();

        for (Integer element : unorderedInts) {
            assertEquals(bst.find(element), -1);
        }
    }
}