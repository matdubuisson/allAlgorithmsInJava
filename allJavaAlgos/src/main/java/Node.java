package main.java;

public class Node<E>{
    E item;
    Node<E> next;

    public Node(E item, Node next){
        this.item = item;
        this.next = next;
    }

    public Node(E item){
        this(item, null);
    }
}