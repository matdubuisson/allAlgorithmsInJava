public class ArrayFactory {
    public static <E> E[] resize(E[] array, int newSize){
        E[] newArray = (E[]) new Object[newSize];

        for(int i = 0; i < (array.length <= newSize ? array.length : newSize); i++){
            newArray[i] = array[i];
        }

        return newArray;
    }
}
