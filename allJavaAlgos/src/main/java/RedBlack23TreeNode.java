package main.java;

public class RedBlack23TreeNode<Key extends Comparable<Key>, Value> extends BinaryTreeNode<Key, Value> implements ADT.RedBlack23TreeNode<Key, Value>{
    public static boolean RED = true, BLACK = false;
    boolean color;
    RedBlack23TreeNode<Key, Value> left, right;

    public RedBlack23TreeNode(Key key, Value value, RedBlack23TreeNode<Key, Value> left, RedBlack23TreeNode<Key, Value> right, boolean color){
        super(key, value, null, null);
        this.left = left;
        this.right = right;
        this.color = color;
    }

    public RedBlack23TreeNode(Key key, Value value, boolean color){
        super(key, value, null, null);
        this.color = color;
    }

    public RedBlack23TreeNode(Key key, Value value){
        this(key, value, null, null, RedBlack23TreeNode.BLACK);
    }

    public boolean isRed(){
        return this.color == RedBlack23TreeNode.RED;
    }

    public boolean isBlack(){
        return this.color == RedBlack23TreeNode.BLACK;
    }

    @Override
    public ADT.RedBlack23TreeNode<Key, Value> getLeft() {
        return (ADT.RedBlack23TreeNode<Key, Value>) super.left;
    }

    @Override
    public void setLeft(ADT.RedBlack23TreeNode<Key, Value> left) {
        super.left = left;
    }

    @Override
    public ADT.RedBlack23TreeNode<Key, Value> getRight() {
        return (ADT.RedBlack23TreeNode<Key, Value>) super.right;
    }

    @Override
    public void setRight(ADT.RedBlack23TreeNode<Key, Value> right) {
        super.right = right;
    }

    @Override
    public boolean getColor() {
        return this.color;
    }

    @Override
    public void setColor(boolean color) {
        this.color = color;
    }
}
