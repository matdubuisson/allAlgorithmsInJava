import java.util.NoSuchElementException;

public class LinkedNodeQueue<E> implements ADT.Queue<E>{
    int size = 0;
    Node<E> first = null;

    @Override
    public void enqueue(E item) {
        this.first = new Node<E>(item, this.first);
        this.size++;
    }

    @Override
    public E dequeue() {
        if(this.empty()) throw new NoSuchElementException();

        Node<E> prvNode = null, curNode = this.first;

        while(curNode.next != null){
            prvNode = curNode;
            curNode = curNode.next;
        }

        if(prvNode == null){
            this.first = null;
        } else {
            prvNode.next = null;
        }

        this.size--;
        return curNode.item;
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
