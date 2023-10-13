import java.util.Iterator;

public class ArrayListP<E> implements ADT.List<E>, Iterable<E>, IteratorFactory.InformalArrayIterable<E>{
    static int default_size = 10;
    int size = 0, nOp = 0;
    E[] array;

    public ArrayListP(int size){
        this.array = (E[]) new Object[size];
    }

    public ArrayListP(){
        this(ArrayListP.default_size);
    }

    @Override
    public void append(E item) {

    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public void set(int index, E item) {

    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public boolean contains(E item) {
        return false;
    }

    @Override
    public boolean empty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public int nOp() {
        return 0;
    }

    @Override
    public E[] array() {
        return null;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }
}
