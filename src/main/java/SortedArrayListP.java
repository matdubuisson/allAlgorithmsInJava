import java.util.*;
import java.lang.Comparable;

public class SortedArrayListP<E extends Comparable<E>> implements ADT.List<E>, ADT.SortedADT<E>, Iterable<E>, IteratorFactory.InformalArrayIterable<E> {
    static int default_size = 10;
    int size = 0, nOp = 0;
    E[] array;

    public SortedArrayListP(int size){
        this.array = (E[]) new Object[size];
    }

    public SortedArrayListP(){
        this(ArrayListP.default_size);
    }

    @Override
    public void append(E item) {
        this.nOp++;

        if(this.size == this.array.length) this.array = ArrayFactory.resize(this.array, this.array.length * 2);

        if(this.size == 0 || item.compareTo(this.array[this.size - 1]) > 0){
            this.array[this.size++] = item;
        } else if(item.compareTo(this.array[0]) < 0){
            ArrayFactory.shift(this.array, 0, this.size, 1);
            this.array[0] = item;
            this.size++;
        } else {
            int i = 0, j = this.size, k, c0, c1;

            while(i < j) {
                k = (i + j - 1) / 2;
                c0 = item.compareTo(this.array[k]);
                c1 = item.compareTo(this.array[k + 1]);

                if(c0 >= 0 && c1 <= 0){
                    ArrayFactory.shift(this.array, k + 1, this.size, 1); // k + 1 has two copied on k + 1 and an other on k + 2
                    this.array[k + 1] = item;
                    break;
                } else if(c0 < 0){
                    j = k - 1;
                } else { // c1 > 0
                    i = (k + 1) + 1;
                }
            }
        }
    }

    @Override
    public E get(int index) {
        if(index >= this.size || this.size + index < 0) throw new NoSuchElementException();

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
        return null;
    }

    @Override
    public boolean contains(E item) {
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
    public int rank(E key) {
        int i = 0, j = this.size, k, c;

        while(i < j){
            k = (i + j - 1) / 2;
            c = key.compareTo(this.array[k]);

            if(c < 0) j = k - 1;
            else if(c > 0) i = k + 1;
            else return k;
        }

        return -1;
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
        SortedArrayListP<Integer> array = new SortedArrayListP<Integer>();
        Random random = new Random();

        for(int i = 0; i < 10; i++){
            array.append(random.nextInt());
        }

        for(Integer i : array){
            System.out.println(i);
        }
    }
}
