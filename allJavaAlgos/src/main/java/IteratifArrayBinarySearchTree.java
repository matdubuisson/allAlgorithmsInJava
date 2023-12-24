package main.java;

public class IteratifArrayBinarySearchTree<Key extends Comparable<Key>, Value> implements ADT.SymbolTable<Key, Value>{
    public static int default_size = 10;

    int size = 0;
    Key[] keys;
    Value[] values;
    int[] lefts, rights;

    public IteratifArrayBinarySearchTree(int size){
        this.keys = (Key[]) new Comparable[size];
        this.values = (Value[]) new Object[size];
        this.lefts = new int[size];
        this.rights = new int[size];
    }

    public IteratifArrayBinarySearchTree(){
        this(IteratifArrayBinarySearchTree.default_size);
    }


    @Override
    public void put(Key key, Value value) {
        if(this.size == 0){
            this.keys[0] = key;
            this.values[0] = value;
            this.lefts[0] = this.rights[0] = -1;
            this.size = 1;
        } else {
            int parentCursor = -1, cursor = 0, cmp = 0;

            while (cursor != -1 && cursor < this.size) {
                cmp = key.compareTo(this.keys[cursor]);
                parentCursor = cursor;

                if (cmp == 0) {
                    this.values[cursor] = value;
                    return;
                } else if (cmp < 0) cursor = this.lefts[cursor];
                else cursor = this.rights[cursor];
            }

            if (this.size == this.keys.length) {
                this.keys = ArrayFactory.resize(this.keys, this.keys.length * 2);
                this.values = ArrayFactory.resize(this.values, this.keys.length * 2);
                this.lefts = ArrayFactory.resize(this.lefts, this.keys.length * 2);
                this.rights = ArrayFactory.resize(this.rights, this.keys.length * 2);
            }

            if(parentCursor != -1) {
                if (cmp < 0) this.lefts[parentCursor] = this.size;
                else this.rights[parentCursor] = this.size;
            }

            this.keys[this.size] = key;
            this.values[this.size] = value;
            this.lefts[this.size] = this.rights[this.size] = -1;
            this.size++;
        }
    }

    @Override
    public Value get(Key key) {
        if(this.size == 0) return null;

        int cursor = 0, cmp;

        while(cursor != -1 && cursor < this.size){
            cmp = key.compareTo(this.keys[cursor]);

            if(cmp == 0) return this.values[cursor];
            else if(cmp < 0) cursor = this.lefts[cursor];
            else cursor = this.rights[cursor];
        }

        return null;
    }

    public int deleteMax(int cursor){
        int parentCursor = -1;

        while(cursor != -1 && cursor < this.keys.length){
            if(this.rights[cursor] == -1){
                if(parentCursor != -1) this.rights[parentCursor] = this.lefts[cursor];
                return cursor;
            } else {
                parentCursor = cursor;
                cursor = this.rights[cursor];
            }
        }

        return -1;
    }

    private void refactor(int deleted, int size){
        int i;

        for(i = deleted + 1; i < this.size; i++){
            this.keys[i - 1] = this.keys[i];
            this.values[i - 1] = this.values[i];
            this.lefts[i - 1] = this.lefts[i];
            this.rights[i - 1] = this.rights[i];
        }

        for(i = 0; i < size; i++){
            if(this.lefts[i] > deleted) this.lefts[i]--;
            if(this.rights[i] > deleted) this.rights[i]--;
        }
    }

    @Override
    public void delete(Key key) {
        // TODO
        return;
    }

    @Override
    public boolean contains(Key key) {
        if(this.size == 0) return false;

        int cursor = 0, cmp;

        while(cursor != -1 && cursor < this.size){
            cmp = key.compareTo(this.keys[cursor]);

            if(cmp == 0) return true;
            else if(cmp < 0) cursor = this.lefts[cursor];
            else cursor = this.rights[cursor];
        }

        return false;
    }

    @Override
    public boolean empty() {
        return this.size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Key min() {
        if(this.size == 0) return null;

        int parentCursor = -1, cursor = 0;

        while(cursor != -1 && cursor < this.size){
            parentCursor = cursor;
            cursor = this.lefts[cursor];
        }

        if(parentCursor == -1) return null;
        else return this.keys[parentCursor];
    }

    @Override
    public Key max() {
        if(this.size == 0) return null;

        int parentCursor = -1, cursor = 0;

        while(cursor != -1 && cursor < this.size){
            parentCursor = cursor;
            cursor = this.rights[cursor];
        }

        if(parentCursor == -1) return null;
        else return this.keys[parentCursor];
    }

    @Override
    public Key floor(Key key) {
        return null;
    }

    @Override
    public Key ceiling(Key key) {
        return null;
    }

    @Override
    public int rank(Key key) {
        return 0;
    }

    @Override
    public Key select(int k) {
        return null;
    }

    @Override
    public void deleteMin() {

    }

    @Override
    public void deleteMax() {

    }

    @Override
    public int size(Key lo, Key hi) {
        return 0;
    }

    @Override
    public Iterable<Key> keys() {
        return null;
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        return null;
    }

    public static void main(String[] args) {
        IteratifArrayBinarySearchTree<String, Integer> tree = new IteratifArrayBinarySearchTree<>();

        tree.put("b", 14);
        tree.put("a", 16);
        tree.put("c", 17);
        tree.put("d", 10);

        System.out.println(tree.contains("d"));
        ArrayFactory.print(tree.keys);
        ArrayFactory.print(tree.values);
        ArrayFactory.print(tree.lefts);
        ArrayFactory.print(tree.rights);
    }
}
