import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayListP<E extends Comparable<E>> implements ADT.List<E>, ADT.SortableADT<E>, Iterable<E>, IteratorFactory.InformalArrayIterable<E>{
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
        this.nOp++;

        if(this.size == this.array.length) this.array = ArrayFactory.resize(this.array, this.array.length * 2);

        this.array[this.size++] = item;
    }

    @Override
    public E get(int index) {
        if(index >= this.size && this.size + index < 0) throw new NoSuchElementException();

        if(index >= 0) return this.array[index];
        else return this.array[this.size + index];
    }

    @Override
    public void set(int index, E item) {
        this.nOp++;

        if(index >= this.size || this.size + index < 0) throw new NoSuchElementException();

        if(index >= 0) this.array[index] = item;
        else this.array[this.size + index] = item;
    }

    @Override
    public E remove(int index) {
        this.nOp++;

        if(index >= this.size && this.size + index < 0) throw new NoSuchElementException();

        if(index < 0) index = this.size + index;

        E oldElem = this.array[index];

        for(int i = index + 1; i < this.size; i++) this.array[i - 1] = this.array[i];

        this.size--;
        return oldElem;
    }

    @Override
    public boolean contains(E item) {
        for(int i = 0; i < this.size; i++){
            if(this.array[i].equals(item)) return true;
        }

        return false;
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
    public void sort() {
        Sorting.mergeSort(this.array);
    }

    @Override
    public Iterator<E> iterator() {
        return new IteratorFactory.ArrayIterator<E>(this);
    }
}
