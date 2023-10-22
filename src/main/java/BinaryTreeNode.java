public class BinaryTreeNode<Key extends Comparable, Value>{
    Key key;
    Value value;
    BinaryTreeNode<Key, Value> left, right;

    public BinaryTreeNode(Key key, Value value, BinaryTreeNode<Key, Value> left, BinaryTreeNode<Key, Value> right){
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
}
