package test.java;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import main.java.*;

public class TestADTIteratifArrayBinarySearchTree {
    @Test
    @Order(0)
    public void testPutAndGetAndContain(){
        TestADT.TestSymbolStable.testPutAndGetAndContain(new IteratifArrayBinarySearchTree<Integer, Integer>(), 0);
        TestADT.TestSymbolStable.testPutAndGetAndContain(new IteratifArrayBinarySearchTree<Integer, Integer>(), 10);
        TestADT.TestSymbolStable.testPutAndGetAndContain(new IteratifArrayBinarySearchTree<Integer, Integer>(), 100);
    }

    @Test
    @Order(1)
    public void testMin(){
        TestADT.TestSymbolStable.testMin(new IteratifArrayBinarySearchTree<Integer, Integer>(), 0);
        TestADT.TestSymbolStable.testMin(new IteratifArrayBinarySearchTree<Integer, Integer>(), 50);
    }

    @Test
    @Order(1)
    public void testMax(){
        TestADT.TestSymbolStable.testMax(new IteratifArrayBinarySearchTree<Integer, Integer>(), 0);
        TestADT.TestSymbolStable.testMax(new IteratifArrayBinarySearchTree<Integer, Integer>(), 50);
    }

    @Test
    @Order(3)
    public void testDelete(){
        //TestADT.TestSymbolStable.testDelete(new IteratifArrayBinarySearchTree<Integer, Integer>(), 0);
        TestADT.TestSymbolStable.testDelete(new IteratifArrayBinarySearchTree<Integer, Integer>(), 50);
    }
}
