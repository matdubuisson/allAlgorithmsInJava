public class Sorting{
    public static void selectionSort(Comparable[] array){
        int minimal;
        Comparable tmp;

        for(int i = 0, j; i < array.length; i++){
            minimal = i;

            for(j = i + 1; j < array.length; j++){
                if(array[j].compareTo(array[minimal]) < 0){
                    minimal = j;
                }
            }

            tmp = array[i];
            array[i] = array[minimal];
            array[minimal] = tmp;
        }

        return;
    }

    public static void insertionSort(Comparable[] array){
        Comparable key;

        for(int i = 1, j; i < array.length; i++){
            key = array[i];

            for(j = i - 1; j >= 0 && key.compareTo(array[j]) < 0; j--){
                array[j + 1] = array[j];
            }

            array[j + 1] = key;
        }

        return;
    }

    public static void shellSort(Comparable[] array, int gap){
        Comparable key;
        int h = 1, i, j;

        while(h < array.length / gap) h = gap * h + 1;

        while(h >= 1){

            for(i = h; i < array.length; i++){
                key = array[i];

                for(j = i - h; j >= 0 && key.compareTo(array[j]) < 0; j -= h){
                    array[j + h] = array[j];
                }

                array[j + h] = key;
            }

            h /= gap;
        }

        return;
    }

    public static void shellSort(Comparable[] array){
        Sorting.shellSort(array, 3);
        return;
    }

    private static void merge(Comparable[] array, Comparable[] auxArray, int start, int middle, int stop){
        int i, j = middle, k = start;

        for(i = start; i < stop; i++){
            auxArray[i] = array[i];
        }

        i = start;

        while(i < middle && j < stop){
            if(array[i].compareTo(array[j]) < 0){
                auxArray[k] = array[i];
                i++;
            } else {
                auxArray[k] = array[j];
                j++;
            }

            k++;
        }

        while(i < middle){
            auxArray[k] = array[i];
            i++;
            k++;
        }

        while(j < stop){
            auxArray[k] = array[j];
            j++;
            k++;
        }

        for(i = start; i < stop; i++){
            array[i] = auxArray[i];
        }

        return;
    }

    public static void mergeSort(Comparable[] array){
        Comparable[] auxArray = new Comparable[array.length];

        for(int size = 1, i; size < array.length; size *= 2){
            for(i = 0; i < array.length; i += 2 * size){
                merge(array, auxArray, i,
                        i + size >= array.length ? array.length : i + size,
                        i + 2 * size >= array.length ? array.length : i + 2 * size
                );
            }
        }

        return;
    }

    public static int partition(Comparable[] array, int start, int stop){
        int i = start, j = stop;
        Comparable key = array[start], tmp;

        while(true){
            while(key.compareTo(array[++i]) > 0){
                if(i == stop - 1) break;
            }

            while(key.compareTo(array[--j]) < 0){
                if(j == start) break;
            }

            if(i >= j) break;

            tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }

        array[start] = array[j];
        array[j] = key;

        return j;
    }

    public static void quickSort(Comparable[] array, int start, int stop){
        if(start >= stop) return;

        int top = -1;
        int[] stack = new int[stop - start];

        while(true) {
            while(start < stop - 1){
                stack[++top] = stop;
                stop = partition(array, start, stop + 1 - 1);
            }

            if(top == -1) break;

            start = stop + 1;
            stop = stack[top--] + 1 - 1;
        }

        return;
    }

    public static void quickSort(Comparable[] array){
        Sorting.quickSort(array, 0, array.length);
        return;
    }

    public static void recursiveQuickSort(Comparable[] array, int start, int stop){
        if(start >= stop - 1) return;

        int limit = partition(array, start, stop);

        Sorting.recursiveQuickSort(array, start, (limit + 1) - 1); // Explanation purpose
        Sorting.recursiveQuickSort(array, limit + 1, stop);
        return;
    }

    public static void recursiveQuickSort(Comparable[] array){
        Sorting.recursiveQuickSort(array, 0, array.length);
        return;
    }

    public static void main(String[] args) {

    }
}
