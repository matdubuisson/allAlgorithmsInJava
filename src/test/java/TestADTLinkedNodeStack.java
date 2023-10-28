import org.javagrader.Grade;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

@Grade
public class TestADTLinkedNodeStack {
    @Test
    @Order(0)
    public void testMethods(){
        ADT.Stack<Integer> stack = new LinkedNodeStack<Integer>();
        TestADT.testStack(stack, 0);
        TestADT.testStack(stack, 10);
        TestADT.testStack(stack, 100);
    }

    @Test
    @Order(0)
    public void testIterator(){
        TestADT.testIterator(new LinkedNodeStack<Integer>(), 0, false);
        TestADT.testIterator(new LinkedNodeStack<Integer>(), 10, false);
    }
}
