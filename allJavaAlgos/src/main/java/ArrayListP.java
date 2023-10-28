package main.java;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayListP<E extends Comparable<E>> implements ADT.List<E>, Iterable<E>, IteratorFactory.InformalArrayIterable<E>{
    static int defaultSize = 10;
    int size = 0, nOp = 0;
    E[] array;

    public ArrayListP(int size){
        this.array = (E[]) new Comparable[size];
    }

    public ArrayListP(){
        this(ArrayListP.defaultSize);
    }

    @Override
    public void append(E item) {
        this.nOp++;
        if(this.size == this.array.length) this.array = ArrayFactory.resize(this.array, this.array.length * 2);

        this.array[this.size++] = item;
    }

    @Override
    public E get(int index) {
        if(index >= this.size || -index < -this.size) throw new NoSuchElementException();

        if(index >= 0) return this.array[index];
        else return this.array[this.size + index];
    }

    @Override
    public void set(int index, E item) {
        this.nOp++;
        if(index >= this.size || -index < -this.size) throw new NoSuchElementException();

        if(index >= 0) this.array[index] = item;
        else this.array[this.size + index] = item;
    }

    @Override
    public E remove(int index) {
        this.nOp++;
        if(index >= this.size || -index < -this.size) throw new NoSuchElementException();

        if(this.size == this.array.length / 4) this.array = ArrayFactory.resize(this.array, this.array.length / 2);

        if(index < 0) index = this.size + index;

        E oldItem = this.array[index];
        this.size--;

        for(int i = index; i < this.size; i++) this.array[index] = this.array[index + 1];

        return oldItem;
    }

    @Override
    public boolean contains(E item) {
        for(int i = 0; i < this.size; i++){
            if(this.array[i].compareTo(item) == 0) return true;
        }

        return false;
    }

    @Override
    public void sort() {
        this.nOp++;
        Sorting.quickSort(this.array, 0, this.size);
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
