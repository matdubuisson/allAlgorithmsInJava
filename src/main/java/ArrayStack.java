import java.util.NoSuchElementException;

public class ArrayStack<E> implements ADT.Stack<E>{
    static int default_size = 10;
    int size = 0;
    E array[];

    public ArrayStack(int size){
        this.array = (E[]) new Object[size];
    }

    public ArrayStack(){
        this(ArrayStack.default_size);
    }

    @Override
    public void push(E item) {
        if(this.size == this.array.length){
            this.array = ArrayFactory.resize(this.array, this.array.length * 2);
        }

        this.array[this.size++] = item;
    }

    @Override
    public E pop() {
        if(this.empty()){
            throw new NoSuchElementException();
        }

        if(this.size == this.array.length / 4){
            this.array = ArrayFactory.resize(this.array, this.array.length / 2);
        }

        return this.array[--this.size];
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
