package test.java;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import main.java.*;

public class TestADTLinkedNodeQueue {
    @Test
    @Order(0)
    public void testMethods(){
        TestADT.testQueue(new LinkedNodeQueue<Integer>(), 0);
        TestADT.testQueue(new LinkedNodeQueue<Integer>(), 10);
        TestADT.testQueue(new LinkedNodeQueue<Integer>(), 100);
    }

    @Test
    @Order(0)
    public void testIterator(){
        TestADT.testIterator(new LinkedNodeQueue<Integer>(), 0, false);
        TestADT.testIterator(new LinkedNodeQueue<Integer>(), 10, false);
    }
}