import java.util.NoSuchElementException;

public class LinkedNodeStack<E> implements ADT.Stack<E>{
    int size = 0;
    Node<E> top = null;

    @Override
    public void push(E item) {
        Node<E> newNode = new Node<E>(item, this.top);
        this.top = newNode;
        this.size++;
    }

    @Override
    public E pop() {
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
}
