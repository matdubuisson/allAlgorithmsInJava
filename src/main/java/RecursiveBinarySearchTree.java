import java.util.Iterator;
import java.util.NoSuchElementException;

public class RecursiveBinarySearchTree<Key extends Comparable<Key>, Value> implements ADT.SymbolTable<Key, Value>{
    // Size is calculated recursively
    // Doesn't need a nOp has the iterator here is produced with a Queue that contains its own iterator and thus its own nOp counter
    BinaryTreeNode<Key, Value> root;

    public RecursiveBinarySearchTree(){
        this.root = null;
    }

    @Override
    public void put(Key key, Value value) {
        this.root = this.put(this.root, key, value);
    }

    public BinaryTreeNode<Key, Value> put(BinaryTreeNode<Key, Value> root, Key key, Value value){
        if(root == null) return new BinaryTreeNode<Key, Value>(key, value);

        int cmp = key.compareTo(root.key);

        if(cmp == 0) {
            root.value = value;
        } else if(cmp < 0){
            root.left = this.put(root.left, key, value);
        } else {
            root.right = this.put(root.right, key, value);
        }

        return root;
    }

    @Override
    public Value get(Key key) {
        return this.get(this.root, key);
    }

    public Value get(BinaryTreeNode<Key, Value> root, Key key){
        if(root == null) throw new NoSuchElementException();

        int cmp = key.compareTo(root.key);

        if(cmp == 0) return root.value;
        else if(cmp < 0) return this.get(root.left, key);
        else return this.get(root.right, key);
    }

    @Override
    public void delete(Key key) {
        this.delete(this.root, key);
    }

    public BinaryTreeNode<Key, Value> delete(BinaryTreeNode<Key, Value> root, Key key){
        if(root == null) throw new NoSuchElementException();

        int cmp = key.compareTo(root.key);

        if(cmp == 0){
            if(root.left == null) return root.right;
            else {
                Key maxKey = this.max(root.left);
                Value maxValue = this.get(maxKey);
                root.left = this.deleteMax(root.left);
                root.key = maxKey;
                root.value = maxValue;
            }
        } else if(cmp < 0) root.left = this.delete(root.left, key);
        else root.right = this.delete(root.right, key);

        return root;
    }

    @Override
    public boolean contains(Key key) {
        return this.contains(this.root, key);
    }

    public boolean contains(BinaryTreeNode<Key, Value> root, Key key){
        if(root == null) return false;

        int cmp = key.compareTo(root.key);

        if(cmp == 0) return true;
        else if(cmp < 0) return this.contains(root.left, key);
        else return this.contains(root.right, key);
    }

    @Override
    public boolean empty() {
        return this.empty(this.root);
    }

    public boolean empty(BinaryTreeNode<Key, Value> root){
        return root == null;
    }

    @Override
    public int size() {
        return this.size(this.root);
    }

    public int size(BinaryTreeNode<Key, Value> root){
        if(root == null) return 0;
        else return 1 + this.size(root.left) + this.size(root.right);
    }

    @Override
    public Key min() {
        if(this.root == null) return null;
        else return this.min(this.root);
    }

    public Key min(BinaryTreeNode<Key, Value> root){
        if(root.left == null) return root.key;
        else return this.min(root.left);
    }

    @Override
    public Key max() {
        if(this.root == null) return null;
        else return this.max(this.root);
    }

    public Key max(BinaryTreeNode<Key, Value> root){
        if(root.right == null) return root.key;
        else return this.max(root.right);
    }

    @Override
    public Key floor(Key key) {
        return this.floor(this.root, key, null);
    }

    public Key floor(BinaryTreeNode<Key, Value> root, Key key, Key bestSimilarKey){
        if(root == null) return bestSimilarKey;

        int cmp = key.compareTo(root.key);

        if(cmp == 0) return key;
        else if(cmp > 0){
            if(bestSimilarKey == null || root.key.compareTo(bestSimilarKey) > 0) bestSimilarKey = root.key;

            return this.floor(root.right, key, bestSimilarKey);
        } else return this.floor(root.left, key, bestSimilarKey);
    }

    @Override
    public Key ceiling(Key key) {
        return this.ceiling(this.root, key, null);
    }

    public Key ceiling(BinaryTreeNode<Key, Value> root, Key key, Key bestSimilarKey){
        if(root == null) return bestSimilarKey;

        int cmp = key.compareTo(root.key);

        if(cmp == 0) return key;
        else if(cmp < 0){
            if(bestSimilarKey == null || root.key.compareTo(bestSimilarKey) < 0) bestSimilarKey = root.key;

            return this.ceiling(root.left, key, bestSimilarKey);
        } else return this.ceiling(root.right, key, bestSimilarKey);
    }

    @Override
    public int rank(Key key) {
        return this.rank(this.root, key);
    }

    public int rank(BinaryTreeNode<Key, Value> root, Key key){
        if(root == null) return 0;

        int cmp = key.compareTo(root.key);
        int leftSize = this.size(root.left);

        if(cmp == 0) return leftSize;
        else if(cmp < 0) return this.rank(root.left, key);
        else return 1 + leftSize + this.rank(root.right, key);
    }

    @Override
    public Key select(int k) {
        return this.select(this.root, k);
    }

    public Key select(BinaryTreeNode<Key, Value> root, int k){
        if(root == null) return null;

        int leftSize = this.size(root.left);

        if(leftSize == k) return root.key;
        else if(leftSize > k) return this.select(root.left, k);
        else return this.select(root.right, k - leftSize - 1); // Parent and all left childen are strictly smaller than all right children so all rights have leftSize + 1 in more in their "k count"
    }

    @Override
    public void deleteMin() {
        if(this.root == null) throw new NoSuchElementException();
        else this.root = this.deleteMin(this.root);
    }

    public BinaryTreeNode<Key, Value> deleteMin(BinaryTreeNode<Key, Value> root){
        if(root.left == null) return root.right;
        else{
            root.left = this.deleteMin(root.left);
            return root;
        }
    }

    @Override
    public void deleteMax() {
        if(this.root == null) throw new NoSuchElementException();
        else this.root = this.deleteMax(this.root);
    }

    public BinaryTreeNode<Key, Value> deleteMax(BinaryTreeNode<Key, Value> root){
        if(root.right == null) return root.left;
        else {
            root.right = this.deleteMax(root.right);
            return root;
        }
    }

    @Override
    public int size(Key lo, Key hi) {
        return 0;
    }

    public int size(BinaryTreeNode<Key, Value> root, Key lo, Key hi){
        if(root == null) return 0;

        int cmplo = root.key.compareTo(lo), cmphi = root.key.compareTo(hi);

        if(cmplo < 0) return this.size(root.right, lo, hi);
        else if(cmphi > 0) return this.size(root.left, lo, hi);
        else return 1 + this.size(root.left, lo, hi) + this.size(root.right, lo, hi);
    }

    @Override
    public Iterable<Key> keys() {
        return this.keys(this.min(), this.max());
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        return this.keys(this.root, new ArrayQueue<Key>(), lo, hi);
    }

    public Iterable<Key> keys(BinaryTreeNode<Key, Value> root, ArrayQueue<Key> queue, Key lo, Key hi){
        if(root == null) return queue;

        int cmplo = root.key.compareTo(lo), cmphi = root.key.compareTo(hi);

        if(cmplo < 0) this.keys(root.right, queue, lo, hi);
        if(cmplo >= 0 && cmphi <= 0) queue.enqueue(root.key);
        if(cmphi > 0) this.keys(root.left, queue, lo, hi);

        return queue;
    }
}