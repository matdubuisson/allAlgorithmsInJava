import org.javagrader.Grade;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.Array;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@Grade
public class ADTTest {
    public void testStack(ADT.Stack<Integer> stack, int size){
        Stack<Integer> javaStack = new Stack<Integer>();

        Random random = new Random();

        assertEquals(javaStack.empty(), stack.empty());

        for(int i = 0, j, k; i < 10; i++){
            for(j = 0; j < size; j++){
                k = random.nextInt();
                stack.push(k);
                javaStack.push(k);
                assertEquals(javaStack.empty(), stack.empty());
                assertEquals(javaStack.size(), stack.size());
            }

            for(j = 0; j < size; j++){
                assertEquals(javaStack.pop(), stack.pop());
                assertEquals(javaStack.empty(), stack.empty());
                assertEquals(javaStack.size(), stack.size());
            }
        }

        assertThrows(NoSuchElementException.class, () -> {
            stack.pop();
        });
    }

    @Test
    @Order(0)
    public void testArrayStack(){
        ADT.Stack<Integer> stack = new ArrayStack<Integer>();
        testStack(stack, 0);
        testStack(stack, 10);
        testStack(stack, 100);
    }

    @Test
    @Order(0)
    public void testLinkedNodeStack(){
        ADT.Stack<Integer> stack = new LinkedNodeStack<Integer>();
        testStack(stack, 0);
        testStack(stack, 10);
        testStack(stack, 100);
    }

    public void testQueue(ADT.Queue<Integer> queue, int size){
        ArrayList<Integer> array = new ArrayList<Integer>();

        Random random = new Random();
        int i, k;

        assertEquals(array.isEmpty(), queue.empty());
        assertEquals(array.size(), queue.size());

        for(i = 0; i < size; i++){
            k = random.nextInt();
            array.add(k);
            queue.enqueue(k);
            assertEquals(array.isEmpty(), queue.empty());
            assertEquals(array.size(), queue.size());
        }

        int sz = queue.size();

        for(i = 0; i < size; i++){
            assertEquals(array.get(i), queue.dequeue());
            assertEquals(i + 1 == size, queue.empty());
            assertEquals(sz - i - 1, queue.size());
        }

        assertThrows(NoSuchElementException.class, () -> {
            queue.dequeue();
        });
    }

    @Test
    @Order(1)
    public void testArrayQueue(){
        testQueue(new ArrayQueue<Integer>(), 0);
        testQueue(new ArrayQueue<Integer>(), 10);
        testQueue(new ArrayQueue<Integer>(), 100);
    }

    @Test
    @Order(1)
    public void testLinkedNodeQueue(){
        testQueue(new ArrayQueue<Integer>(), 0);
        testQueue(new ArrayQueue<Integer>(), 10);
        testQueue(new ArrayQueue<Integer>(), 100);
    }
}
