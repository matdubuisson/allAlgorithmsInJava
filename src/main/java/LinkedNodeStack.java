import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedNodeStack<E> implements ADT.Stack<E>, Iterable<E>, IteratorFactory.InformalLinkedNodeIterable<E>{
    int size = 0, nOp = 0;
    Node<E> top = null;

    @Override
    public void push(E item) {
        this.nOp++;
        Node<E> newNode = new Node<E>(item, this.top);
        this.top = newNode;
        this.size++;
    }

    @Override
    public E pop() {
        this.nOp++;

        if(this.empty()){
            throw new NoSuchElementException();
        }

        Node<E> oldNode = this.top;
        this.top = this.top.next;
        this.size--;
        return oldNode.item;
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
    public int nOp() {
        return this.nOp;
    }

    @Override
    public Node<E> first() {
        return this.top;
    }

    @Override
    public Iterator<E> iterator() {
        return new IteratorFactory.LinkedNodeIterator<E>(this);
    }
}
