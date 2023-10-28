package test.java;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import main.java.*;

public class TestADTArrayStack {
    @Test
    @Order(0)
    public void testMethods(){
        ADT.Stack<Integer> stack = new ArrayStack<Integer>();
        TestADT.testStack(stack, 0);
        TestADT.testStack(stack, 10);
        TestADT.testStack(stack, 100);
    }

    @Test
    @Order(0)
    public void testIterator(){
        TestADT.testIterator(new ArrayStack<Integer>(), 0, true);
        TestADT.testIterator(new ArrayStack<Integer>(), 10, true);
    }
}