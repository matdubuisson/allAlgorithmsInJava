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

    public static interface List<E>{
        void append(E item);
        E get(int index);
        E remove(int index);
        boolean contains(E item);
        boolean empty();
        int size();
    }
}