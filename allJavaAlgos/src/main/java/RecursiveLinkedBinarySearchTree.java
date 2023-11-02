package main.java;

import javax.swing.tree.TreeNode;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RecursiveLinkedBinarySearchTree<Key extends Comparable<Key>, Value> implements ADT.SymbolTable<Key, Value>{
    // Size is calculated recursively
    // Doesn't need a nOp has the iterator here is produced with a Queue that contains its own iterator and thus its own nOp counter
    ADT.BinaryTreeNode<Key, Value> root = null;

    public RecursiveLinkedBinarySearchTree(){}

    @Override
    public void put(Key key, Value value) {
        this.root = this.put(this.root, key, value);
    }

    public ADT.BinaryTreeNode<Key, Value> put(ADT.BinaryTreeNode<Key, Value> root, Key key, Value value){
        if(root == null) return new BinaryTreeNode<Key, Value>(key, value);

        int cmp = key.compareTo(root.getKey());

        if(cmp == 0) {
            root.setValue(value);
        } else if(cmp < 0){
            root.setLeft(this.put(root.getLeft(), key, value));
        } else {
            root.setRight(this.put(root.getRight(), key, value));
        }

        return root;
    }

    @Override
    public Value get(Key key) {
        return this.get(this.root, key);
    }

    public Value get(ADT.BinaryTreeNode<Key, Value> root, Key key){
        if(root == null) throw new NoSuchElementException();

        int cmp = key.compareTo(root.getKey());

        if(cmp == 0) return root.getValue();
        else if(cmp < 0) return this.get(root.getLeft(), key);
        else return this.get(root.getRight(), key);
    }


    @Override
    public void delete(Key key) {
        this.root = this.delete(this.root, key);
    }

    public ADT.BinaryTreeNode<Key, Value> delete(ADT.BinaryTreeNode<Key, Value> root, Key key){
        if(root == null) throw new NoSuchElementException();

        int cmp = key.compareTo(root.getKey());

        if(cmp == 0){
            if(root.getLeft() == null) return root.getRight();
            else if(root.getRight() == null) return root.getLeft();
            else {
                ADT.BinaryTreeNode<Key, Value> max = this.max(root.getLeft());
                max.setLeft(this.deleteMax(root.getLeft()));
                max.setRight(root.getRight());
                return max;
            }
        } else if(cmp < 0) root.setLeft(this.delete(root.getLeft(), key));
        else root.setRight(this.delete(root.getRight(), key));

        return root;
    }

    @Override
    public boolean contains(Key key) {
        return this.contains(this.root, key);
    }

    public boolean contains(ADT.BinaryTreeNode<Key, Value> root, Key key){
        if(root == null) return false;

        int cmp = key.compareTo(root.getKey());

        if(cmp == 0) return true;
        else if(cmp < 0) return this.contains(root.getLeft(), key);
        else return this.contains(root.getRight(), key);
    }

    @Override
    public boolean empty() {
        return this.empty(this.root);
    }

    public boolean empty(ADT.BinaryTreeNode<Key, Value> root){
        return root == null;
    }

    @Override
    public int size() {
        return this.size(this.root);
    }

    public int size(ADT.BinaryTreeNode<Key, Value> root){
        if(root == null) return 0;
        else return 1 + this.size(root.getLeft()) + this.size(root.getRight());
    }

    @Override
    public Key min() {
        if(this.root == null) return null;
        else return this.min(this.root).getKey();
    }

    public ADT.BinaryTreeNode<Key, Value> min(ADT.BinaryTreeNode<Key, Value> root){
        if(root.getLeft() == null) return root;
        else return this.min(root.getLeft());
    }

    @Override
    public Key max() {
        if(this.root == null) return null;
        else return this.max(this.root).getKey();
    }

    public ADT.BinaryTreeNode<Key, Value> max(ADT.BinaryTreeNode<Key, Value> root){
        if(root.getRight() == null) return root;
        else return this.max(root.getRight());
    }

    @Override
    public Key floor(Key key) {
        return this.floor(this.root, key, null);
    }

    public Key floor(ADT.BinaryTreeNode<Key, Value> root, Key key, Key bestSimilarKey){
        if(root == null) return bestSimilarKey;

        int cmp = key.compareTo(root.getKey());

        if(cmp == 0) return key;
        else if(cmp > 0){
            if(bestSimilarKey == null || root.getKey().compareTo(bestSimilarKey) > 0) bestSimilarKey = root.getKey();

            return this.floor(root.getRight(), key, bestSimilarKey);
        } else return this.floor(root.getLeft(), key, bestSimilarKey);
    }

    @Override
    public Key ceiling(Key key) {
        return this.ceiling(this.root, key, null);
    }

    public Key ceiling(ADT.BinaryTreeNode<Key, Value> root, Key key, Key bestSimilarKey){
        if(root == null) return bestSimilarKey;

        int cmp = key.compareTo(root.getKey());

        if(cmp == 0) return key;
        else if(cmp < 0){
            if(bestSimilarKey == null || root.getKey().compareTo(bestSimilarKey) < 0) bestSimilarKey = root.getKey();

            return this.ceiling(root.getLeft(), key, bestSimilarKey);
        } else return this.ceiling(root.getRight(), key, bestSimilarKey);
    }

    @Override
    public int rank(Key key) {
        return this.rank(this.root, key);
    }

    public int rank(ADT.BinaryTreeNode<Key, Value> root, Key key){
        if(root == null) return 0;

        int cmp = key.compareTo(root.getKey());
        int leftSize = this.size(root.getLeft());

        if(cmp == 0) return leftSize;
        else if(cmp < 0) return this.rank(root.getLeft(), key);
        else return 1 + leftSize + this.rank(root.getRight(), key);
    }

    @Override
    public Key select(int k) {
        return this.select(this.root, k);
    }

    public Key select(ADT.BinaryTreeNode<Key, Value> root, int k){
        if(root == null) return null;

        int leftSize = this.size(root.getLeft());

        if(leftSize == k) return root.getKey();
        else if(leftSize > k) return this.select(root.getLeft(), k);
        else return this.select(root.getRight(), k - leftSize - 1); // Parent and all left childen are strictly smaller than all right children so all rights have leftSize + 1 in more in their "k count"
    }

    @Override
    public void deleteMin() {
        if(this.root == null) throw new NoSuchElementException();
        else this.root = this.deleteMin(this.root);
    }

    public ADT.BinaryTreeNode<Key, Value> deleteMin(ADT.BinaryTreeNode<Key, Value> root){
        if(root.getLeft() == null) return root.getRight();
        else{
            root.setLeft(this.deleteMin(root.getLeft()));
            return root;
        }
    }

    @Override
    public void deleteMax() {
        if(this.root == null) throw new NoSuchElementException();
        else this.root = this.deleteMax(this.root);
    }

    public ADT.BinaryTreeNode<Key, Value> deleteMax(ADT.BinaryTreeNode<Key, Value> root){
        if(root.getRight() == null) return root.getLeft();
        else {
            root.setRight(this.deleteMax(root.getRight()));
            return root;
        }
    }

    @Override
    public int size(Key lo, Key hi) {
        return this.size(this.root, lo, hi);
    }

    public int size(ADT.BinaryTreeNode<Key, Value> root, Key lo, Key hi){
        if(root == null) return 0;

        int cmplo = root.getKey().compareTo(lo), cmphi = root.getKey().compareTo(hi);

        if(cmplo < 0) return this.size(root.getRight(), lo, hi);
        else if(cmphi > 0) return this.size(root.getLeft(), lo, hi);
        else return 1 + this.size(root.getLeft(), lo, hi) + this.size(root.getRight(), lo, hi);
    }

    @Override
    public Iterable<Key> keys() {
        return this.keys(this.min(), this.max());
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        return this.keys(this.root, new ArrayQueue<Key>(), lo, hi);
    }

    public Iterable<Key> keys(ADT.BinaryTreeNode<Key, Value> root, ArrayQueue<Key> queue, Key lo, Key hi){
        if(root == null) return queue;

        int cmplo = root.getKey().compareTo(lo), cmphi = root.getKey().compareTo(hi);

        if(cmplo < 0) this.keys(root.getRight(), queue, lo, hi);
        if(cmplo >= 0 && cmphi <= 0) queue.enqueue(root.getKey());
        if(cmphi > 0) this.keys(root.getLeft(), queue, lo, hi);

        return queue;
    }
}