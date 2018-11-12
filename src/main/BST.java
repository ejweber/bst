package main;

import java.util.ArrayList;

/**
 * A generic binary search tree class to hold data of generic type T.
 *
 * @author Eric Weber
 * @version 0.1
 * @since 11/31/18
 */

public class BST<T extends Comparable<? super T>> {  // stole ? super T from StackOverflow
    private Node<T> root;
    private ArrayList<T> list;

    // basic constructor
    BST() { root = null; }

    // constructor with data to use in root node
    public BST(T rootData) { root = new Node<T>(rootData); }

    void insert(T data) {
        Node<T> node = new Node<T>(data);
        Node<T> lead = root;
        Node<T> trail = null;

        // find lowest level of bst
        while (lead != null) {
            trail = lead;
            if (node.getData().compareTo(lead.getData()) < 0)
                lead = lead.getLChild();
            else
                lead = lead.getRChild();
        }
        if (trail == null) {
            root = node;  // bst was empty
        }
        else {
            if (node.getData().compareTo(trail.getData()) < 0)
                trail.setLChild(node);
            else
                trail.setRChild(node);
            node.setParent(trail);
        }
    }

    ArrayList<T> getInOrder() {
        list = new ArrayList<T>();  // reset ArrayList that will store values in order
        inOrder(root);
        return list;
    }

    private void inOrder(Node<T> subroot) {
        if (subroot.getLChild() != null)
            inOrder(subroot.getLChild());
        list.add(subroot.getData());
        if (subroot.getRChild() != null)
            inOrder(subroot.getRChild());
    }

    ArrayList<T> getPreOrder() {
        list = new ArrayList<T>();  // reset ArrayList that will store values in order
        preOrder(root);
        return list;
    }

    private void preOrder(Node<T> subroot) {
        list.add(subroot.getData());
        if (subroot.getLChild() != null)
            preOrder(subroot.getLChild());
        if (subroot.getRChild() != null)
            preOrder(subroot.getRChild());
    }

    ArrayList<T> getPostOrder() {
        list = new ArrayList<T>();  // reset ArrayList that will store values in order
        postOrder(root);
        return list;
    }

    private void postOrder(Node<T> subroot) {
        if (subroot.getLChild() != null)
            postOrder(subroot.getLChild());
        if (subroot.getRChild() != null)
            postOrder(subroot.getRChild());
        list.add(subroot.getData());
    }

    int find(T data) {
        return recursiveFind(root, data, 0);
    }

    private int recursiveFind(Node<T> subroot, T data, int depth) {
        if (subroot == null) { return -1; }  // made it to bottom without finding element
        int comparison = data.compareTo(subroot.getData());
        if (comparison < 0) { return recursiveFind(subroot.getLChild(), data, depth + 1); }
        else if (comparison > 0) { return recursiveFind(subroot.getRChild(), data, depth + 1); }
        else { return depth; }
    }

    T getMinimum() {
        return recursiveMinimum(root); }  // may return null (on empty bst)

    private T recursiveMinimum(Node<T> subroot) {
        if (subroot == null) { return null; }
        else if (subroot.getLChild() != null) { return recursiveMinimum(subroot.getLChild()); }
        else { return subroot.getData(); }
    }

    T getMaximum() {
        return recursiveMaximum(root);  // may return null (on empty bst)
    }

    private T recursiveMaximum(Node<T> subroot) {
        if (subroot == null) { return null; }
        else if (subroot.getRChild() != null) { return recursiveMaximum(subroot.getRChild()); }
        else { return subroot.getData(); }
    }
}