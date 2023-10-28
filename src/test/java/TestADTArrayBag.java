import org.javagrader.Grade;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

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
