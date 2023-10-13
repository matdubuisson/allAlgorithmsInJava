import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedNodeBag<E> implements ADT.Bag<E>, Iterable<E>, IteratorFactory.InformalLinkedNodeIterable<E> {
    int size = 0, nOp = 0;
    Node<E> first = null;

    @Override
    public void add(E item) {
        this.nOp++;
        this.first = new Node<E>(item, this.first);
        this.size++;
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
        return this.first;
    }

    @Override
    public Iterator<E> iterator() {
        return new IteratorFactory.LinkedNodeIterator<E>(this);
    }
}