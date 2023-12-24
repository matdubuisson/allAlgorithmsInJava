package test.java;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import main.java.*;

public class TestADTLinkedBinarySearchTree {
    @Test
    @Order(0)
    public void testPutAndGetAndContain(){
        TestADT.TestSymbolStable.testPutAndGetAndContain(new RecursiveLinkedBinarySearchTree<Integer, Integer>(), 0);
        TestADT.TestSymbolStable.testPutAndGetAndContain(new RecursiveLinkedBinarySearchTree<Integer, Integer>(), 10);
        TestADT.TestSymbolStable.testPutAndGetAndContain(new RecursiveLinkedBinarySearchTree<Integer, Integer>(), 100);
    }

    @Test
    @Order(1)
    public void testMin(){
        TestADT.TestSymbolStable.testMin(new RecursiveLinkedBinarySearchTree<Integer, Integer>(), 0);
        TestADT.TestSymbolStable.testMin(new RecursiveLinkedBinarySearchTree<Integer, Integer>(), 50);
    }

    @Test
    @Order(1)
    public void testMax(){
        TestADT.TestSymbolStable.testMax(new RecursiveLinkedBinarySearchTree<Integer, Integer>(), 0);
        TestADT.TestSymbolStable.testMax(new RecursiveLinkedBinarySearchTree<Integer, Integer>(), 50);
    }

    @Test
    @Order(2)
    public void testDeleteMin(){
        TestADT.TestSymbolStable.testDeleteMin(new RecursiveLinkedBinarySearchTree<Integer, Integer>(), 0);
        TestADT.TestSymbolStable.testDeleteMin(new RecursiveLinkedBinarySearchTree<Integer, Integer>(), 50);
    }

    @Test
    @Order(2)
    public void testDeleteMax(){
        TestADT.TestSymbolStable.testDeleteMax(new RecursiveLinkedBinarySearchTree<Integer, Integer>(), 0);
        TestADT.TestSymbolStable.testDeleteMax(new RecursiveLinkedBinarySearchTree<Integer, Integer>(), 50);
    }

    @Test
    @Order(3)
    public void testDelete(){
        TestADT.TestSymbolStable.testDelete(new RecursiveLinkedBinarySearchTree<Integer, Integer>(), 0);
        TestADT.TestSymbolStable.testDelete(new RecursiveLinkedBinarySearchTree<Integer, Integer>(), 50);
        TestADT.TestSymbolStable.testDelete(new RecursiveLinkedBinarySearchTree<Integer, Integer>(), 100);
    }

    @Test
    @Order(4)
    public void testFloor(){
        TestADT.TestSymbolStable.testFloor(new RecursiveLinkedBinarySearchTree<Integer, Integer>(), 0);
        TestADT.TestSymbolStable.testFloor(new RecursiveLinkedBinarySearchTree<Integer, Integer>(), 50);
    }

    @Test
    @Order(4)
    public void testCeiling(){
        TestADT.TestSymbolStable.testCeiling(new RecursiveLinkedBinarySearchTree<Integer, Integer>(), 0);
        TestADT.TestSymbolStable.testCeiling(new RecursiveLinkedBinarySearchTree<Integer, Integer>(), 50);
    }
}
