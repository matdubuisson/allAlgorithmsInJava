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

    public static class TestSymbolStable{
        private static void initDatas(ADT.SymbolTable<Integer, Integer> st, ArrayList<Integer> keys, ArrayList<Integer> values, int n){
            Integer key, value;
            Random random = new Random();
            boolean found;

            for(int i = 0, j; i < n; i++){
                do {
                    key = random.nextInt();
                    found = false;

                    for(j = 0; j < keys.size() && !found; j++){
                        if(keys.get(j).compareTo(key) == 0) found = true;
                    }
                } while(found);

                value = random.nextInt();
                keys.add(key);
                values.add(value);
                st.put(key, value);
                TestSymbolStable.testEmptyAndSize(st, keys);
            }
        }

        public static void testEmptyAndSize(ADT.SymbolTable<Integer, Integer> st, ArrayList<Integer> array){
            assertEquals(array.isEmpty(), st.empty());
            assertEquals(array.size(), st.size());
        }

        public static void testPutAndGetAndContain(ADT.SymbolTable<Integer, Integer> st, int n){
            int i;
            Integer key, value;
            Random random = new Random();
            ArrayList<Integer> keys = new ArrayList<Integer>();
            ArrayList<Integer> values = new ArrayList<Integer>();
            TestSymbolStable.testEmptyAndSize(st, keys);
            TestSymbolStable.initDatas(st, keys, values, n);

            for(i = 0; i < n; i++){
                key = keys.get(i);
                value = values.get(i);
                assertTrue(st.contains(key));
                assertEquals(value, st.get(key));
                key = random.nextInt();
                assertEquals(keys.contains(key), st.contains(key));
                TestSymbolStable.testEmptyAndSize(st, keys);
            }
        }

        public static void testMin(ADT.SymbolTable<Integer, Integer> st, int n){
            ArrayList<Integer> keys = new ArrayList<Integer>();
            ArrayList<Integer> values = new ArrayList<Integer>();
            TestSymbolStable.initDatas(st, keys, values, n);
            Integer mi = null;

            for(Integer k : keys){
                if(mi == null || k.compareTo(mi) < 0) mi = k;
            }

            assertEquals(mi, st.min());
        }

        public static void testMax(ADT.SymbolTable<Integer, Integer> st, int n){
            ArrayList<Integer> keys = new ArrayList<Integer>();
            ArrayList<Integer> values = new ArrayList<Integer>();
            TestSymbolStable.initDatas(st, keys, values, n);
            Integer ma = null;

            for(Integer k : keys){
                if(ma == null || k.compareTo(ma) > 0) ma = k;
            }

            assertEquals(ma, st.max());
        }

        public static void testDeleteMin(ADT.SymbolTable<Integer, Integer> st, int n){
            ArrayList<Integer> keys = new ArrayList<Integer>();
            ArrayList<Integer> values = new ArrayList<Integer>();
            TestSymbolStable.initDatas(st, keys, values, n);
            Integer mi;

            if(n > 0) {
                for(int i = 0; i < n; i++) {
                    mi = st.min();
                    assertTrue(st.contains(mi));
                    st.deleteMin();
                    assertFalse(st.contains(mi));
                }

                assertTrue(st.empty());
            } else {
                assertThrows(NoSuchElementException.class, () -> {
                    st.deleteMin();
                });
            }
        }

        public static void testDeleteMax(ADT.SymbolTable<Integer, Integer> st, int n){
            ArrayList<Integer> keys = new ArrayList<Integer>();
            ArrayList<Integer> values = new ArrayList<Integer>();
            TestSymbolStable.initDatas(st, keys, values, n);
            Integer ma;

            if(n > 0) {
                for(int i = 0; i < n; i++) {
                    ma = st.max();
                    assertTrue(st.contains(ma));
                    st.deleteMax();
                    assertFalse(st.contains(ma));
                }

                assertTrue(st.empty());
            } else {
                assertThrows(NoSuchElementException.class, () -> {
                    st.deleteMax();
                });
            }
        }

        public static void testDelete(ADT.SymbolTable<Integer, Integer> st, int n){
            if(n == 0){
                assertThrows(NoSuchElementException.class, () -> {
                    st.delete(0);
                });
            } else {
                ArrayList<Integer> keys = new ArrayList<Integer>();
                ArrayList<Integer> values = new ArrayList<Integer>();
                TestSymbolStable.initDatas(st, keys, values, n);
                int i;

                assertFalse(st.empty());

                for(i = 0; i < n; i++){
                    assertFalse(st.empty());
                    assertEquals(n - i, st.size());
                    assertTrue(st.contains(keys.get(i)));
                    st.delete(keys.get(i));
                    assertFalse(st.contains(keys.get(i)));
                }

                assertTrue(st.empty());
            }
        }
    }
}
