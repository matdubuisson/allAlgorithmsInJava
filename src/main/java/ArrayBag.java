import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayBag<E> implements ADT.Bag<E>, Iterable<E>, IteratorFactory.InformalArrayIterable<E>{
    static int default_size = 10;
    int size = 0, nOp = 0;
    E[] array;

    public ArrayBag(int size){
        this.array = (E[]) new Object[size];
    }

    public ArrayBag(){
        this(ArrayStack.default_size);
    }

    @Override
    public void add(E item) {
        this.nOp++;

        if(this.size == this.array.length){
            this.array = ArrayFactory.resize(this.array, this.array.length * 2);
        }

        this.array[this.size++] = item;
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
    public E[] array() {
        return this.array;
    }

    @Override
    public Iterator<E> iterator() {
        return new IteratorFactory.ArrayIterator<E>(this);
    }
}
