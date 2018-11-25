package main;

import java.util.ArrayList;

/**
 * A generic binary search tree class to hold data of generic type T.
 *
 * @author Eric Weber
 * @version 0.1
 * @since 11/31/18
 */


class BST<T extends Comparable<? super T>> {  // stole ? super T from StackOverflow
    private Node<T> root;
    private ArrayList<T> list;

    // basic constructor
    BST() { root = null; }

    // constructor with data to use in root node
    BST(T rootData) { root = new Node<T>(rootData); }

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
        if (subroot == null) { return; }
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
        if (subroot == null) { return; }
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
        if (subroot == null) { return; }
        if (subroot.getLChild() != null)
            postOrder(subroot.getLChild());
        if (subroot.getRChild() != null)
            postOrder(subroot.getRChild());
        list.add(subroot.getData());
    }

    // returns -1 if not found
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

    private Node<T> findNode(T data) {
        return recursiveFindNode(root, data);
    }

    private Node<T> recursiveFindNode(Node<T> subroot, T data) {
        if (subroot == null) { return null; }  // made it to bottom without finding element
        int comparison = data.compareTo(subroot.getData());
        if (comparison < 0) { return recursiveFindNode(subroot.getLChild(), data); }
        else if (comparison > 0) { return recursiveFindNode(subroot.getRChild(), data); }
        else { return subroot; }
    }

    T getMinimum() {
        if (recursiveMinimum(root) != null)
            return recursiveMinimum(root).getData();
        else
            return null;  // may return null on empty bst
    }

    private Node<T> recursiveMinimum(Node<T> subroot) {
        if (subroot == null) { return null; }
        else if (subroot.getLChild() != null) { return recursiveMinimum(subroot.getLChild()); }
        else { return subroot; }
    }

    T getMaximum() {
        if (recursiveMaximum(root) != null)
            return recursiveMaximum(root).getData();
        else
            return null;  // may return null (on empty bst)
    }

    private Node<T> recursiveMaximum(Node<T> subroot) {
        if (subroot == null) { return null; }
        else if (subroot.getRChild() != null) { return recursiveMaximum(subroot.getRChild()); }
        else { return subroot; }
    }

    T getSuccessor(T data) {
        Node<T> trail = findNode(data);
        if (trail == null) { return null; }
        if (trail.getRChild() != null)
            // successor is minimum node in subtree rooted at trail's right child
            return recursiveMinimum(trail.getRChild()).getData();
        else {
            Node<T> lead = trail.getParent();
            while (lead != null && trail == lead.getRChild()) {  // look for an ancestor whose left child is an ancestor
                trail = lead;
                lead = lead.getParent();
            }
            if (lead != null) { return lead.getData(); }
            else { return null; }
        }
    }

    T getPredecessor(T data) {
        Node<T> trail = findNode(data);
        if (trail == null) { return null; }
        if (trail.getLChild() != null)
            // successor is maximum node in subtree rooted at trail's left child
            return recursiveMaximum(trail.getLChild()).getData();
        else {
            Node<T> lead = trail.getParent();
            // look for an ancestor whose right child is an ancestor
            while (lead != null && trail == lead.getLChild()) {
                trail = lead;
                lead = lead.getParent();
            }
            if (lead != null) { return lead.getData(); }
            else { return null; }
        }
    }

    // replace the subtree rooted at oldNode with the one rooted at newNode
    // newNode is left completely unhooked from the tree
    private void transplant(Node<T> oldNode, Node<T> newNode) {
        if (oldNode.getParent() == null)
            root = newNode;  // oldNode was root of tree
        else if (oldNode == oldNode.getParent().getLChild())
            oldNode.getParent().setLChild(newNode);  // hook in newNode as left child
        else
            oldNode.getParent().setRChild(newNode);  // hook in newNode as right child
        if (newNode != null)
            newNode.setParent(oldNode.getParent());
    }

    boolean delete(T data) {
        Node<T> target = findNode(data);
        if (target == null)
            return false;
        // if target has no left child, its right child can simply replace it
        if (target.getLChild() == null)
            transplant(target, target.getRChild());
        // if target has no right child, its left child can simply replace it
        else if (target.getRChild() == null)
            transplant(target, target.getLChild());
        else {
            Node<T> rightMin = recursiveMinimum(target.getRChild());
            // safely pull out the minimum node in target's subtree
            if (rightMin.getParent() != target) {
                transplant(rightMin, rightMin.getRChild());
                rightMin.setRChild(target.getRChild());
                rightMin.getRChild().setParent(rightMin);
            }
            // safely replace the target with the minimum node in its subtree
            transplant(target, rightMin);
            rightMin.setLChild(target.getLChild());
            rightMin.getLChild().setParent(rightMin);
        }
        return true;
    }

    void clearTree() {
        root = null;  // the garbage collector will take care of the rest
    }
}