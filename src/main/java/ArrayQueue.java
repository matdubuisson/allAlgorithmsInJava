import java.lang.reflect.Array;
import java.util.NoSuchElementException;

public class ArrayQueue<E> implements ADT.Queue<E>{
    static int default_size = 10;
    int size = 0;
    E[] array;
    public ArrayQueue(int size){
        this.array = (E[]) new Object[size];
    }

    public ArrayQueue(){
        this(ArrayQueue.default_size);
    }

    @Override
    public void enqueue(E item) {
        if(this.size == this.array.length){
            this.array = ArrayFactory.resize(this.array, this.array.length * 2);
        }

        this.array[this.size++] = item;
    }

    @Override
    public E dequeue() {
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
}
