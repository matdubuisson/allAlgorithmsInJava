package main.java;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayQueue<E> implements ADT.Queue<E>, Iterable<E>, IteratorFactory.InformalArrayIterable<E>{
    static int default_size = 10;
    int size = 0, nOp = 0;
    E[] array;
    public ArrayQueue(int size){
        this.array = (E[]) new Object[size];
    }

    public ArrayQueue(){
        this(ArrayQueue.default_size);
    }

    @Override
    public void enqueue(E item) {
        this.nOp++;

        if(this.size == this.array.length){
            this.array = ArrayFactory.resize(this.array, this.array.length * 2);
        }

        this.array[this.size++] = item;
    }

    @Override
    public E dequeue() {
        this.nOp++;

        if(this.empty()) throw new NoSuchElementException();

        E oldItem = this.array[0];

        for(int i = 1; i < this.size; i++){
            this.array[i - 1] = this.array[i];
        }

        this.size--;
        return oldItem;
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

    public static void main(String[] args) {
        ArrayQueue<Integer> q = new ArrayQueue<Integer>();

        q.dequeue();
    }
}
