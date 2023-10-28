package main.java;

public class ArrayFactory {
    public static <E> E[] resize(E[] array, int newSize){
        E[] newArray = (E[]) new Object[newSize];

        for(int i = 0; i < (array.length <= newSize ? array.length : newSize); i++){
            newArray[i] = array[i];
        }

        return newArray;
    }

    public static <E> E[] resize(Comparable<E>[] array, int newSize){
        Comparable<E>[] newArray = new Comparable[newSize];

        for(int i = 0; i < (array.length <= newSize ? array.length : newSize); i++){
            newArray[i] = array[i];
        }

        return (E[]) newArray;
    }

    public static <E> void shift(E[] array, int from, int to, int scale){
        if(scale == 0) return;

        int start, stop;

        if(scale > 0) {
            start = from;
            stop = to + scale < array.length ? to + scale : array.length;

            for(int i = stop - 1; i >= start + scale; i--) array[i] = array[i - scale]; // scale > 0
        } else {
            start = from + scale >= 0 ? from + scale : 0;
            stop = to;

            for(int i = start; i < stop + scale; i++) array[i] = array[i - scale]; // scale < 0
        }
    }

    public static <E> void print(E[] array){
        System.out.print("{");

        for(int i = 0; i < array.length; i++){
            System.out.print("[" + array[i] + "]");
        }

        System.out.println("}");
    }

    public static void main(String[] args) {
        int length = 10;
        Integer[] array = new Integer[length];

        for(int i = 0; i < length; i++){
            array[i] = i * i;
        }

        print(array);

        shift(array, 7, 9, -7);

        print(array);
    }
}
