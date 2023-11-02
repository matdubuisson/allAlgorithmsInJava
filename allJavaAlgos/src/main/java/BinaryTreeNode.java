package main.java;

public class BinaryTreeNode<Key extends Comparable<Key>, Value> implements ADT.BinaryTreeNode<Key, Value>{
    Key key;
    Value value;
    ADT.BinaryTreeNode<Key, Value> left, right;

    public BinaryTreeNode(Key key, Value value, ADT.BinaryTreeNode<Key, Value> left, ADT.BinaryTreeNode<Key, Value> right){
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public BinaryTreeNode(Key key, Value value){
        this(key, value, null, null);
    }

    public BinaryTreeNode(Key key){
        this(key, null, null, null);
    }


    @Override
    public Key getKey() {
        return this.key;
    }

    @Override
    public void setKey(Key key) {
        this.key = key;
    }

    @Override
    public Value getValue() {
        return this.value;
    }

    @Override
    public void setValue(Value value) {
        this.value = value;
    }

    @Override
    public ADT.BinaryTreeNode<Key, Value> getLeft() {
        return this.left;
    }

    @Override
    public void setLeft(ADT.BinaryTreeNode<Key, Value> left) {
        this.left = left;
    }

    @Override
    public ADT.BinaryTreeNode<Key, Value> getRight() {
        return this.right;
    }

    @Override
    public void setRight(ADT.BinaryTreeNode<Key, Value> right) {
        this.right = right;
    }
}
