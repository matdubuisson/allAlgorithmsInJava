import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestADT {
    // All tests for ADTs

    public static void testBag(ADT.Bag<Integer> bag, int n){
        assertEquals(true, bag.empty());
        assertEquals(0, bag.size());

        for(int i = 0; i < n; i++){
            bag.add(i * i);
            assertEquals(false, bag.empty());
            assertEquals(i + 1, bag.size());
        }
    }

    public static void testStack(ADT.Stack<Integer> stack, int size){
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

    public static void testQueue(ADT.Queue<Integer> queue, int size){
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

    // Tests for iterators

    public static void testIterator(Iterable<Integer> object, int n, boolean normalDirection){
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
}
