import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedNodeBag<E> implements ADT.Bag<E>, Iterable<E> {
    int size = 0, nOp = 0;
    Node<E> first = null;

    @Override
    public void add(E item) {
        this.first = new Node<E>(item, this.first);
        this.size++;
        this.nOp++;
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
    public Iterator<E> iterator() {
        return new LinkedNodeBagIterator();
    }

    private class LinkedNodeBagIterator implements Iterator<E>{
        int size = LinkedNodeBag.this.size, nOp = LinkedNodeBag.this.nOp;
        Node<E> current = LinkedNodeBag.this.first;

        @Override
        public boolean hasNext() {
            return this.size == 0;
        }

        @Override
        public E next() {
            if(this.size == 0){
                throw new NoSuchElementException();
            } else if(this.nOp != LinkedNodeBag.this.nOp){
                throw new ConcurrentModificationException();
            } else {
                E item = this.current.item;
                this.current = this.current.next;
                this.size--;
                return item;
            }
        }
    }
}
