package test.java;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

import main.java.*;

public class SortingTest{
    private interface SortFunction{
        void run(Comparable[] array);
    }

    public void testSort(SortFunction fun, int n){
        Random random = new Random();
        Integer[] array = new Integer[n];

        for(int i = 0; i < n; i++){
            array[i] = random.nextInt(n);
        }

        Integer[] copiedArray = Arrays.copyOf(array, array.length);
        fun.run(array);
        Arrays.sort(copiedArray);
        assertEquals(true, Arrays.equals(array, copiedArray));
        return;
    }

    @Test
    @Order(0)
    public void testSelectionSort(){
        testSort((Comparable[] array) -> Sorting.selectionSort(array), 0);
        testSort((Comparable[] array) -> Sorting.selectionSort(array), 10);
        testSort((Comparable[] array) -> Sorting.selectionSort(array), 100);
        testSort((Comparable[] array) -> Sorting.selectionSort(array), 1000);
        testSort((Comparable[] array) -> Sorting.selectionSort(array), 10000);
        return;
    }

    @Test
    @Order(0)
    public void testInsertionSort(){
        testSort((Comparable[] array) -> Sorting.insertionSort(array), 0);
        testSort((Comparable[] array) -> Sorting.insertionSort(array), 10);
        testSort((Comparable[] array) -> Sorting.insertionSort(array), 100);
        testSort((Comparable[] array) -> Sorting.insertionSort(array), 1000);
        return;
    }

    @Test
    @Order(0)
    public void testShellSort2(){
        testSort((Comparable[] array) -> Sorting.shellSort(array, 2), 0);
        testSort((Comparable[] array) -> Sorting.shellSort(array, 2), 10);
        testSort((Comparable[] array) -> Sorting.shellSort(array, 2), 100);
        testSort((Comparable[] array) -> Sorting.shellSort(array, 2), 1000);
        testSort((Comparable[] array) -> Sorting.shellSort(array, 2), 10000);
        return;
    }

    @Test
    @Order(0)
    public void testShellSort3(){
        testSort((Comparable[] array) -> Sorting.shellSort(array, 3), 0);
        testSort((Comparable[] array) -> Sorting.shellSort(array, 3), 10);
        testSort((Comparable[] array) -> Sorting.shellSort(array, 3), 100);
        testSort((Comparable[] array) -> Sorting.shellSort(array, 3), 1000);
        testSort((Comparable[] array) -> Sorting.shellSort(array, 3), 10000);
        return;
    }

    @Test
    @Order(0)
    public void testMergeSort(){
        testSort((Comparable[] array) -> Sorting.mergeSort(array), 0);
        testSort((Comparable[] array) -> Sorting.mergeSort(array), 10);
        testSort((Comparable[] array) -> Sorting.mergeSort(array), 100);
        testSort((Comparable[] array) -> Sorting.mergeSort(array), 1000);
        testSort((Comparable[] array) -> Sorting.mergeSort(array), 10000);
        return;
    }

    @Test
    @Order(0)
    public void testQuickSort(){
        /*
        Integer[] array = new Integer[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        Integer[] copiedArray = Arrays.copyOf(array, array.length);

        Sorting.quickSort(array);

        System.out.print("{");
        for(int i = 0; i < array.length; i++){
            System.out.print("[" + array[i] + "]");
        }
        System.out.println("}");

        Arrays.sort(copiedArray);

        assertArrayEquals(copiedArray, array);
        */

        testSort((Comparable[] array) -> Sorting.quickSort(array), 0);
        testSort((Comparable[] array) -> Sorting.quickSort(array), 10);
        testSort((Comparable[] array) -> Sorting.quickSort(array), 100);
        testSort((Comparable[] array) -> Sorting.quickSort(array), 1000);
        testSort((Comparable[] array) -> Sorting.quickSort(array), 10000);
        return;
    }

    @Test
    @Order(0)
    public void testRecursiveQuickSort(){
        /*
        Integer[] array = new Integer[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        Integer[] copiedArray = Arrays.copyOf(array, array.length);

        Sorting.quickSort(array);

        System.out.print("{");
        for(int i = 0; i < array.length; i++){
            System.out.print("[" + array[i] + "]");
        }
        System.out.println("}");

        Arrays.sort(copiedArray);

        assertArrayEquals(copiedArray, array);
        */

        testSort((Comparable[] array) -> Sorting.recursiveQuickSort(array), 0);
        testSort((Comparable[] array) -> Sorting.recursiveQuickSort(array), 10);
        testSort((Comparable[] array) -> Sorting.recursiveQuickSort(array), 100);
        testSort((Comparable[] array) -> Sorting.recursiveQuickSort(array), 1000);
        testSort((Comparable[] array) -> Sorting.recursiveQuickSort(array), 10000);
        return;
    }

    @Test
    @Order(1)
    public void test2MergeSort(){
        Random random = new Random();
        testSort((Comparable[] array) -> Sorting.mergeSort(array), random.nextInt(1000) / 2);
        testSort((Comparable[] array) -> Sorting.mergeSort(array), random.nextInt(1000) / 3);
        testSort((Comparable[] array) -> Sorting.mergeSort(array), random.nextInt(1000) / 5);
        testSort((Comparable[] array) -> Sorting.mergeSort(array), random.nextInt(1000) / 7);
        return;
    }

    @Test
    @Order(2)
    public void testSpeeds(){
        /*
        int length = 5000000;
        Integer[] array = new Integer[length], copiedArray, javaArray;
        Random random = new Random();

        for(int i = 0; i < length; i++){
            array[i] = random.nextInt(1000);
        }

        javaArray = Arrays.copyOf(array, length);
        StopWatch swJavaSort = new StopWatch();
        Arrays.sort(javaArray);
        System.out.println("Java sort : " + swJavaSort.click() + " s");

        copiedArray = Arrays.copyOf(array, length);
        StopWatch swMergeSort = new StopWatch();
        Sorting.mergeSort(copiedArray);
        System.out.println("Merge sort : " + swMergeSort.click() + " s");
        assertEquals(true, Arrays.equals(javaArray, copiedArray));

        copiedArray = Arrays.copyOf(array, length);
        StopWatch swShellSort2 = new StopWatch();
        Sorting.shellSort(copiedArray, 2);
        System.out.println("Shell sort 2 : " + swShellSort2.click() + " s");
        assertEquals(true, Arrays.equals(javaArray, copiedArray));

        copiedArray = Arrays.copyOf(array, length);
        StopWatch swShellSort3 = new StopWatch();
        Sorting.shellSort(copiedArray, 3);
        System.out.println("Shell sort 3 : " + swShellSort3.click() + " s");
        assertEquals(true, Arrays.equals(javaArray, copiedArray));

        copiedArray = Arrays.copyOf(array, length);
        StopWatch swQuickSort = new StopWatch();
        Sorting.quickSort(copiedArray);
        System.out.println("QuickSort : " + swQuickSort.click() + " s");
        assertEquals(true, Arrays.equals(javaArray, copiedArray));
         */
    }
}
