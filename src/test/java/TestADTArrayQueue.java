import org.javagrader.Grade;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

@Grade
public class TestADTArrayQueue {
    @Test
    @Order(0)
    public void testMethods(){
        TestADT.testQueue(new ArrayQueue<Integer>(), 0);
        TestADT.testQueue(new ArrayQueue<Integer>(), 10);
        TestADT.testQueue(new ArrayQueue<Integer>(), 100);
    }

    @Test
    @Order(0)
    public void testIterator(){
        TestADT.testIterator(new ArrayQueue<Integer>(), 0, true);
        TestADT.testIterator(new ArrayQueue<Integer>(), 10, true);
    }
}