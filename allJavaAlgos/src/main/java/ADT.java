package main.java;

public class ADT {
    public static interface Node<E>{
        E getItem();
        Node<E> getNext();
    }

    public static interface DoubleNode<E> extends Node<E>{
        Node<E> getPrev();
    }

    public static interface BinaryTreeNode<Key extends Comparable<Key>, Value>{
        Key getKey();
        void setKey(Key key);
        Value getValue();
        void setValue(Value value);
        ADT.BinaryTreeNode<Key, Value> getLeft();
        void setLeft(ADT.BinaryTreeNode<Key, Value> left);
        ADT.BinaryTreeNode<Key, Value> getRight();
        void setRight(ADT.BinaryTreeNode<Key, Value> right);
    }

    public static interface RedBlack23TreeNode<Key extends Comparable<Key>, Value> extends BinaryTreeNode<Key, Value> {
        ADT.RedBlack23TreeNode<Key, Value> getLeft();
        void setLeft(ADT.RedBlack23TreeNode<Key, Value> left);
        ADT.RedBlack23TreeNode<Key, Value> getRight();
        void setRight(ADT.RedBlack23TreeNode<Key, Value> right);
        boolean getColor();
        void setColor(boolean color);
    }

    public static interface Bag<E>{
        void add(E item);
        boolean empty();
        int size();
    }

    public static interface Stack<E>{
        void push(E item);
        E pop();
        boolean empty();
        int size();
    }

    public static interface Queue<E>{
        void enqueue(E item);
        E dequeue();
        boolean empty();
        int size();
    }

    public static interface List<E extends Comparable<? super E>>{
        void append(E item);
        E get(int index);
        void set(int index, E item);
        E remove(int index);
        boolean contains(E item);
        void sort();
        boolean empty();
        int size();
    }

    public static interface SymbolTable<Key extends Comparable<Key>, Value>{
        void put(Key key, Value value);
        Value get(Key key);
        void delete(Key key);
        boolean contains(Key key);
        boolean empty();
        int size();
        Key min();
        Key max();
        Key floor(Key key);
        Key ceiling(Key key);
        int rank(Key key);
        Key select(int k);
        void deleteMin();
        void deleteMax();
        int size(Key lo, Key hi);
        Iterable<Key> keys();
        Iterable<Key> keys(Key lo, Key hi);
    }

    public static interface RedBlack23SearchTree<Key extends Comparable<Key>, Value>{
        void flipColors(RedBlack23TreeNode<Key, Value> root);
        ADT.RedBlack23TreeNode<Key, Value> rotateLeft(RedBlack23TreeNode<Key, Value> root);
        ADT.RedBlack23TreeNode<Key, Value> rotateRight(RedBlack23TreeNode<Key, Value> root);

        boolean isRed(RedBlack23TreeNode<Key, Value> root);
    }
}