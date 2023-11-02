package main.java;

public class RecursiveLinkedRedBlack23SearchTree <Key extends Comparable<Key>, Value> extends RecursiveLinkedBinarySearchTree<Key, Value> implements ADT.SymbolTable<Key, Value>, ADT.RedBlack23SearchTree<Key, Value>{
    public ADT.RedBlack23TreeNode<Key, Value> getRoot(){
        return (ADT.RedBlack23TreeNode<Key, Value>) super.root;
    }

    @Override
    public void put(Key key, Value value){
        super.root = this.put(this.getRoot(), key, value);
        this.getRoot().setColor(RedBlack23TreeNode.BLACK);
    }

    public ADT.RedBlack23TreeNode<Key, Value> put(ADT.RedBlack23TreeNode<Key, Value> root, Key key, Value value){
        if(root == null) return new RedBlack23TreeNode<Key, Value>(key, value, RedBlack23TreeNode.RED);

        int cmp = key.compareTo(root.getKey());

        if(cmp == 0){
            root.setValue(value);
        } else if(cmp < 0){
            root.setLeft(this.put(root.getLeft(), key, value));
        } else {
            root.setRight(this.put(root.getRight(), key, value));
        }

        if(!this.isRed(root.getLeft()) && this.isRed(root.getRight())) root = this.rotateLeft(root);
        if(this.isRed(root.getLeft()) && this.isRed(root.getLeft().getLeft())) root = this.rotateRight(root);
        if(this.isRed(root.getLeft()) && this.isRed(root.getRight())) this.flipColors(root);

        return root;
    }

    

    @Override
    public void flipColors(ADT.RedBlack23TreeNode<Key, Value> root) {
        root.setColor(RedBlack23TreeNode.RED);
        root.getLeft().setColor(RedBlack23TreeNode.BLACK);
        root.getRight().setColor(RedBlack23TreeNode.BLACK);
    }

    @Override
    public ADT.RedBlack23TreeNode<Key, Value> rotateLeft(ADT.RedBlack23TreeNode<Key, Value> root) {
        ADT.RedBlack23TreeNode<Key, Value> between = root.getRight().getLeft(), newRoot = root.getRight();
        newRoot.setColor(root.getColor()); // #
        root.setColor(RedBlack23TreeNode.RED); // Need to be after #
        newRoot.setLeft(root);
        root.setRight(between);
        return newRoot;
    }

    @Override
    public ADT.RedBlack23TreeNode<Key, Value> rotateRight(ADT.RedBlack23TreeNode<Key, Value> root) {
        ADT.RedBlack23TreeNode<Key, Value> between = root.getLeft().getRight(), newRoot = root.getLeft();
        newRoot.setColor(root.getColor()); // #
        root.setColor(RedBlack23TreeNode.RED); // Need to be after #
        newRoot.setRight(root);
        root.setLeft(between);
        return newRoot;
    }

    @Override
    public boolean isRed(ADT.RedBlack23TreeNode<Key, Value> root) {
        return root != null && root.getColor() == RedBlack23TreeNode.RED;
    }

    public static void main(String[] args) {
        RecursiveLinkedRedBlack23SearchTree<String, Integer> tree = new RecursiveLinkedRedBlack23SearchTree<>();
        System.out.println(tree.size());
        tree.put("j", 0);
        System.out.println(tree.size());
        tree.put("i", 1);
        System.out.println(tree.size());
        tree.put("k", 2);
        System.out.println(tree.size());

        System.out.println(tree.get("i") + " " + tree.get("k"));

        System.out.println("====================");
        tree.put("a", 3);
        tree.put("b", 4);
        //tree.put("k", 5);
        //tree.put("z", 6);
        //tree.put("e", 7);

        System.out.println(tree.get("i") + " " + tree.get("a"));
        //System.out.println(tree.get("b"));
        //System.out.println(tree.get("k"));
        //System.out.println(tree.get("e"));
        System.out.println(tree.size());

        System.out.println("===============");

        ADT.RedBlack23TreeNode<String, Integer> root = tree.getRoot();
        System.out.println(root.getKey() + " " + root.getLeft().getKey() + " " + root.getRight().getKey());
        System.out.println(root.getColor() + " " + root.getLeft().getColor() + " " + root.getRight().getColor());
        System.out.println(root.getLeft().getLeft().getKey() + " " + root.getLeft().getRight().getKey());
        System.out.println(root.getLeft().getLeft().getColor() + " " + root.getLeft().getRight().getColor());
    }
}
