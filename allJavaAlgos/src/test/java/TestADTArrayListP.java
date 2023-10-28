package test.java;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import main.java.*;

public class TestADTArrayListP {
    @Test
    @Order(0)
    public void testMethods(){
        TestADT.testList(new ArrayListP<Integer>(), 0);
        TestADT.testList(new ArrayListP<Integer>(), 10);
        TestADT.testList(new ArrayListP<Integer>(), 100);
    }

    @Test
    @Order(0)
    public void testIterator(){
        TestADT.testIterator(new ArrayListP<Integer>(), 0, true);
        TestADT.testIterator(new ArrayListP<Integer>(), 10, true);
    }
}
