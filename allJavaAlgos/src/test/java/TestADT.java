package test.java;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

import main.java.*;

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

    public static void testListAux(ArrayList<Integer> jList, ADT.List<Integer> list){
        assertEquals(jList.isEmpty(), list.empty());
        assertEquals(jList.isEmpty(), list.empty());
    }

    public static void testList(ADT.List<Integer> list, int n){
        ArrayList<Integer> jList = new ArrayList<Integer>();

        testListAux(jList, list);

        int i;

        for(i = 0; i < n; i++){
            jList.add(i * i);
            list.append(i * i);
            testListAux(jList, list);
        }

        assertThrows(NoSuchElementException.class, () -> {
            list.get(n);
        });

        for(i = 0; i < n; i++){
            assertEquals(jList.get(i), list.get(i));
            assertEquals(jList.contains(jList.get(i)), list.contains(jList.get(i)));
            testListAux(jList, list);
        }

        for(i = 0; i < n; i++){
            jList.set(i, i * i * i);
            list.set(i, i * i * i);
            assertEquals(jList.get(i), list.get(i));
            assertEquals(jList.contains(jList.get(i)), list.contains(jList.get(i)));
            testListAux(jList, list);
        }

        assertThrows(NoSuchElementException.class, () -> {
            list.remove(n);
        });

        if(n >= 3) {
            assertEquals(jList.remove(n - 1), list.remove(n - 1));
            assertEquals(jList.remove((n - 1) / 2), list.remove((n - 1) / 2));
            assertEquals(jList.remove(0), list.remove(0));

            for(i = 0; i < n - 3; i++){
                list.remove(0);
                jList.remove(0);
            }
        } else if(n == 2){
            assertEquals(jList.remove(1), list.remove(1));
            assertEquals(jList.remove(1), list.remove(0));
        }

        testListAux(jList, list);

        assertThrows(NoSuchElementException.class, () -> {
            list.remove(0);
        });

        list.sort();

        for(i = 1; i < list.size(); i++){
            assertTrue(list.get(i - 1).compareTo(list.get(i)) <= 0);
        }
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
            } else if(object instanceof ADT.List){
                ((ADT.List<Integer>) object).append(i * i);
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
                    } else if (object instanceof ADT.List) {
                        ((ADT.List<Integer>) object).append(0);
                    }
                }
            });
        }
    }
}
