package main.java;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedNodeListP<E extends Comparable<E>> implements ADT.List<E>, Iterable<E>, IteratorFactory.InformalLinkedNodeIterable<E>{
    int size = 0, nOp = 0;
    Node<E> first = null;

    @Override
    public void append(E item) {
        this.nOp++;

        if(this.first == null) this.first = new Node<E>(item, null);
        else this.find(this.size - 1).next = new Node<E>(item, null);

        this.size++;
    }

    private Node<E> find(int index, int deviance){
        if(index >= this.size || -index < -this.size) throw new NoSuchElementException();

        Node<E> curNode = this.first;

        if(index < 0) index = this.size + index;

        for(int i = 0; i < index + deviance; i++){
            curNode = curNode.next;
        }

        return curNode;
    }

    private Node<E> find(int index){
        return this.find(index, 0);
    }

    @Override
    public E get(int index) {
        return this.find(index).item;
    }

    @Override
    public void set(int index, E item) {
        this.nOp++;
        this.find(index).item = item;
    }

    @Override
    public E remove(int index) {
        this.nOp++;

        if(index >= this.size || -index < -this.size) throw new NoSuchElementException();

        E oldItem;

        if(index == 0 || this.size + index == 0){
            oldItem = this.first.item;
            this.first = this.first.next;
        } else {
            Node<E> prvNode = this.find(index, -1);
            oldItem = prvNode.next.item;
            prvNode.next = prvNode.next.next;
        }

        this.size--;
        return oldItem;
    }

    @Override
    public boolean contains(E item) {
        Node<E> curNode = this.first;

        while(curNode != null){
            if(curNode.item.compareTo(item) == 0) return true;

            curNode = curNode.next;
        }

        return false;
    }

    @Override
    public void sort() {
        this.nOp++;

        if(this.size <= 1) return;

        E[] array = (E[]) new Comparable[this.size];
        Node<E> curNode = this.first;
        int i = 0;

        while(curNode != null){
            array[i++] = curNode.item;
            curNode = curNode.next;
        }

        Sorting.quickSort(array);

        curNode = this.first;

        while(curNode != null){
            curNode.item = array[i++];
            curNode = curNode.next;
        }
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
