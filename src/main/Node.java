package main;

/**
 * A generic Node class to hold data of generic type T. Intended for use within a binary search tree.
 *
 * @author Eric Weber
 * @version 0.1
 * @since 11/31/18
 */

class Node<T extends Comparable> { // T must be a comparable type
    private T data; // actually holds the data of some type T
    private Node<T> parent;
    private Node<T> lChild;
    private Node<T> rChild;

    // constructors
    Node(T data) {
        this.parent = this.lChild = this.rChild = null;
        this.data = data;
     }

    Node(T data, Node<T> parent, Node<T> lChild, Node<T> rChild) {
        this.parent = parent;
        this.lChild = lChild;
        this.rChild = rChild;
        this.data = data;
    }

    // setters
    void setParent(Node<T> parent) { this.parent = parent; }
    void setLChild(Node<T> lChild) { this.lChild = lChild; }
    void setRChild(Node<T> rChild) { this.rChild = rChild; }

    // getters
    Node<T> getParent() { return this.parent; }
    Node<T> getLChild() { return this.lChild; }
    Node<T> getRChild() { return this.rChild; }
    T getData() { return this.data; }
}
