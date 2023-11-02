package test.java;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import main.java.*;

public class TestADTLinkedRedBlack23SearchTree {
    @Test
    @Order(0)
    public void testPutAndGetAndContain(){
        TestADT.TestSymbolStable.testPutAndGetAndContain(new RecursiveLinkedRedBlack23SearchTree<Integer, Integer>(), 0);
        TestADT.TestSymbolStable.testPutAndGetAndContain(new RecursiveLinkedRedBlack23SearchTree<Integer, Integer>(), 10);
        TestADT.TestSymbolStable.testPutAndGetAndContain(new RecursiveLinkedRedBlack23SearchTree<Integer, Integer>(), 100);
    }

    @Test
    @Order(1)
    public void testMin(){
        TestADT.TestSymbolStable.testMin(new RecursiveLinkedRedBlack23SearchTree<Integer, Integer>(), 0);
        TestADT.TestSymbolStable.testMin(new RecursiveLinkedRedBlack23SearchTree<Integer, Integer>(), 50);
    }

    @Test
    @Order(1)
    public void testMax(){
        TestADT.TestSymbolStable.testMax(new RecursiveLinkedRedBlack23SearchTree<Integer, Integer>(), 0);
        TestADT.TestSymbolStable.testMax(new RecursiveLinkedRedBlack23SearchTree<Integer, Integer>(), 50);
    }

    @Test
    @Order(2)
    public void testDeleteMin(){
        TestADT.TestSymbolStable.testDeleteMin(new RecursiveLinkedRedBlack23SearchTree<Integer, Integer>(), 0);
        TestADT.TestSymbolStable.testDeleteMin(new RecursiveLinkedRedBlack23SearchTree<Integer, Integer>(), 50);
    }

    @Test
    @Order(2)
    public void testDeleteMax(){
        TestADT.TestSymbolStable.testDeleteMax(new RecursiveLinkedRedBlack23SearchTree<Integer, Integer>(), 0);
        TestADT.TestSymbolStable.testDeleteMax(new RecursiveLinkedRedBlack23SearchTree<Integer, Integer>(), 50);
    }

    @Test
    @Order(3)
    public void testDelete(){
        TestADT.TestSymbolStable.testDelete(new RecursiveLinkedRedBlack23SearchTree<Integer, Integer>(), 0);
        TestADT.TestSymbolStable.testDelete(new RecursiveLinkedRedBlack23SearchTree<Integer, Integer>(), 50);
    }
}
