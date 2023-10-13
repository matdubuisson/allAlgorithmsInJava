import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorFactory{
    public interface InformalArrayIterable<E>{
        int size();
        int nOp();
        E[] array();
    }

    public static class ArrayIterator<E> implements Iterator<E> {
        int index = 0, size, nOp;
        InformalArrayIterable author;

        public ArrayIterator(InformalArrayIterable author){
            this.size = author.size();
            this.nOp = author.nOp();
            this.author = author;
        }

        @Override
        public boolean hasNext() {
            return this.index < this.size;
        }

        @Override
        public E next() {
            if(this.index >= this.size){
                throw new NoSuchElementException();
            } else if(this.nOp != this.author.nOp()){
                throw new ConcurrentModificationException();
            } else {
                return (E) (this.author.array())[this.index++];
            }
        }
    }

    public interface InformalLinkedNodeIterable<E>{
        int size();
        int nOp();
        Node<E> first();
    }

    public static class LinkedNodeIterator<E> implements Iterator<E> {
        int size, nOp;
        Node<E> current;
        InformalLinkedNodeIterable author;

        public LinkedNodeIterator(InformalLinkedNodeIterable author){
            this.size = author.size();
            this.nOp = author.nOp();
            this.current = author.first();
            this.author = author;
        }

        @Override
        public boolean hasNext() {
            return this.size > 0;
        }

        @Override
        public E next() {
            if (this.size == 0) {
                throw new NoSuchElementException();
            } else if (this.nOp != this.author.nOp()) {
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
