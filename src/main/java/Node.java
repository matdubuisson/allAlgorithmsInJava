public class Node<E>{
    E item;
    Node next;

    public Node(E item, Node next){
        this.item = item;
        this.next = next;
    }

    public Node(E item){
        this(item, null);
    }
}