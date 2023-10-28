import org.javagrader.Grade;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.sql.Array;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@Grade
public class ADTSTTest {
    public void testSymbolTable(ADT.SymbolTable<String, Integer> st){
        String key = new String("key");

        assertTrue(st.empty());
        assertEquals(0, st.size());
        assertNull(st.min());
        assertNull(st.max());
        assertNull(st.floor(key));
        assertNull(st.ceiling(key));
        assertEquals(0, key);
        assertNull(st.select(10));

        int length = 10;
        String[] keys = new String[]{"aaa", "bbb", "ccc", "ddd", "eee", "fff", "ggg", key, "lll", "mmm", "nnn"};
    }
}