import org.javagrader.Grade;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class TestADTLinkedNodeBag {
    @Test
    @Order(0)
    public void testMethods() {
        TestADT.testBag(new LinkedNodeBag<Integer>(), 0);
        TestADT.testBag(new LinkedNodeBag<Integer>(), 10);
        TestADT.testBag(new LinkedNodeBag<Integer>(), 100);
    }

    @Test
    @Order(0)
    public void testIterator(){
        TestADT.testIterator(new LinkedNodeBag<Integer>(), 0, false);
        TestADT.testIterator(new LinkedNodeBag<Integer>(), 10, false);
    }
}
