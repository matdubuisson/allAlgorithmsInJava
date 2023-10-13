import org.javagrader.Grade;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.sql.Array;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@Grade
public class ADTTest {
    public void testIterator(Iterable<Integer> object, int n, boolean normalDirection){
        int i;

        assertThrows(NoSuchElementException.class, () -> {
            (object.iterator()).next();
        });

        for(i = 0; i < n; i++) {
            if(object instanceof ADT.Bag){
                ((ADT.Bag<Integer>) object).add(i * i);
            } else if(object instanceof ADT.Stack){
                ((ADT.Stack<Integer>) object).push(i * i);
            } else if(object instanceof ADT.Queue){
                ((ADT.Queue<Integer>) object).enqueue(i * i);
            }
        }

        i = normalDirection ? 0 : n - 1;
        Iterator<Integer> iterator = object.iterator();

        while(iterator.hasNext()){
            assertEquals(i * i, iterator.next());
            i += normalDirection ? 1 : -1;
        }


        if(n > 0) {
            assertThrows(ConcurrentModificationException.class, () -> {
                for (Integer j : object) {
                    if (object instanceof ADT.Bag) {
                        ((ADT.Bag<Integer>) object).add(0);
                    } else if (object instanceof ADT.Stack) {
                        ((ADT.Stack<Integer>) object).push(0);
                    } else if (object instanceof ADT.Queue) {
                        ((ADT.Queue<Integer>) object).enqueue(0);
                    }
                }
            });
        }
    }

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

        testIterator(new ArrayStack<Integer>(), 0, true);
        testIterator(new ArrayStack<Integer>(), 10, true);
    }

    @Test
    @Order(0)
    public void testLinkedNodeStack(){
        ADT.Stack<Integer> stack = new LinkedNodeStack<Integer>();
        testStack(stack, 0);
        testStack(stack, 10);
        testStack(stack, 100);

        testIterator(new LinkedNodeStack<Integer>(), 0, false);
        testIterator(new LinkedNodeStack<Integer>(), 10, false);
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
    @Order(0)
    public void testArrayQueue(){
        testQueue(new ArrayQueue<Integer>(), 0);
        testQueue(new ArrayQueue<Integer>(), 10);
        testQueue(new ArrayQueue<Integer>(), 100);

        testIterator(new ArrayQueue<Integer>(), 0, true);
        testIterator(new ArrayQueue<Integer>(), 10, true);
    }

    @Test
    @Order(0)
    public void testLinkedNodeQueue(){
        testQueue(new LinkedNodeQueue<Integer>(), 0);
        testQueue(new LinkedNodeQueue<Integer>(), 10);
        testQueue(new LinkedNodeQueue<Integer>(), 100);

        testIterator(new LinkedNodeQueue<Integer>(), 0, false);
        testIterator(new LinkedNodeQueue<Integer>(), 10, false);
    }

    public void testBag(ADT.Bag<Integer> bag, int n){
        assertEquals(true, bag.empty());
        assertEquals(0, bag.size());

        for(int i = 0; i < n; i++){
            bag.add(i * i);
            assertEquals(false, bag.empty());
            assertEquals(i + 1, bag.size());
        }
    }

    @Test
    @Order(0)
    public void testLinkedNodeBag(){
        testBag(new LinkedNodeBag<Integer>(), 0);
        testBag(new LinkedNodeBag<Integer>(), 10);
        testBag(new LinkedNodeBag<Integer>(), 100);

        testIterator(new LinkedNodeBag<Integer>(), 0, false);
        testIterator(new LinkedNodeBag<Integer>(), 10, false);
    }

    @Test
    @Order(0)
    public void testArrayBag(){
        testBag(new ArrayBag<Integer>(), 0);
        testBag(new ArrayBag<Integer>(), 10);
        testBag(new ArrayBag<Integer>(), 100);

        testIterator(new ArrayBag<Integer>(), 0, true);
        testIterator(new ArrayBag<Integer>(), 10, true);
    }
}
