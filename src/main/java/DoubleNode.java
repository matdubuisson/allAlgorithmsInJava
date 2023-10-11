public class DoubleNode<E>{
    E item;
    DoubleNode<E> prev, next;

    public DoubleNode(E item, DoubleNode<E> prev, DoubleNode<E> next){
        this.item = item;
        this.prev = prev;
        this.next = next;
    }

    public DoubleNode(E item){
        this(item, null, null);
    }
}
