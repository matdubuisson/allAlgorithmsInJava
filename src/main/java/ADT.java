public class ADT {
    public static interface Bag<E>{
        void add(E item);
        boolean empty();
        int size();
    }

    public static interface Stack<E>{
        void push(E item);
        E pop();
        boolean empty();
        int size();
    }

    public static interface Queue<E>{
        void enqueue(E item);
        E dequeue();
        boolean empty();
        int size();
    }

    public static interface List<E extends Comparable<? super E>>{
        void append(E item);
        E get(int index);
        void set(int index, E item);
        E remove(int index);
        boolean contains(E item);
        void sort();
        boolean empty();
        int size();
    }

    public static interface SymbolTable<Key extends Comparable<Key>, Value>{
        void put(Key key, Value value);
        Value get(Key key);
        void delete(Key key);
        boolean contains(Key key);
        boolean empty();
        int size();
        Key min();
        Key max();
        Key floor(Key key);
        Key ceiling(Key key);
        int rank(Key key);
        Key select(int k);
        void deleteMin();
        void deleteMax();
        int size(Key lo, Key hi);
        Iterable<Key> keys();
        Iterable<Key> keys(Key lo, Key hi);
    }
}