package test.java;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import main.java.*;

public class TestADTArrayBag {
    @Test
    @Order(0)
    public void testMethods() {
        TestADT.testBag(new ArrayBag<Integer>(), 0);
        TestADT.testBag(new ArrayBag<Integer>(), 10);
        TestADT.testBag(new ArrayBag<Integer>(), 100);
    }

    @Test
    @Order(0)
    public void testIterator(){
        TestADT.testIterator(new ArrayBag<Integer>(), 0, true);
        TestADT.testIterator(new ArrayBag<Integer>(), 10, true);
    }
}
