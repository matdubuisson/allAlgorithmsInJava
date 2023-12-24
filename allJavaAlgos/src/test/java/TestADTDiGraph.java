package test.java;

import main.java.DiGraph;
import main.java.Graph;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestADTDiGraph{
    @Order(0)
    @Test
    public void testDiGraph(){
        DiGraph digraph = new DiGraph(10);

        assertEquals(digraph.V(), 10);
        assertEquals(digraph.E(), 0);

        digraph.addEdge(0, 1);
        digraph.addEdge(0, 2);
        digraph.addEdge(1, 3);
        digraph.addEdge(3, 0);

        assertTrue(digraph.hasEdge(0, 1));
        assertTrue(digraph.hasEdge(1, 3));
        assertFalse(digraph.hasEdge(3, 1));
        assertFalse(digraph.hasEdge(0, 4));

        assertEquals(digraph.V(), 10);
        assertEquals(digraph.E(), 4);

        Iterable<Integer> iter = digraph.adj(0);

        int i = 0;

        for(int w : digraph.adj(0)){
            assertTrue(w == 1 || w == 2);
            assertTrue(i < 2);
        }
    }

    @Order(1)
    @Test
    public void testCycle(){
        DiGraph digraph = new DiGraph(5);

        digraph.addEdge(0, 1);
        digraph.addEdge(0, 2);
        digraph.addEdge(0, 3);
        digraph.addEdge(1, 3);
        digraph.addEdge(2, 4);
        digraph.addEdge(4, 0); // HERE

        assertTrue(digraph.hasCycle());

        Iterable<Integer> iter = digraph.getCycle();

        assertTrue(iter != null);

        int preW = -1;

        for(int w : iter){
            if(preW != -1) assertTrue(digraph.hasEdge(preW, w));

            preW = w;
        }

        for(int w : iter){
            assertTrue(digraph.hasEdge(preW, w));
            break;
        }

        assertTrue(preW != -1);
    }

    @Order(1)
    @Test
    public void testNoCycle(){
        DiGraph digraph = new DiGraph(5);

        digraph.addEdge(0, 1);
        digraph.addEdge(0, 2);
        digraph.addEdge(0, 3);
        digraph.addEdge(1, 3);
        digraph.addEdge(2, 4);
        digraph.addEdge(0, 4); // HERE

        assertFalse(digraph.hasCycle());

        Iterable<Integer> iter = digraph.getCycle();

        assertTrue(iter == null);
    }

    @Order(1)
    @Test
    public void testBiparti(){
        DiGraph digraph = new DiGraph(5);

        digraph.addEdge(1, 0);
        digraph.addEdge(1, 4);
        digraph.addEdge(3, 0);
        digraph.addEdge(3, 2);
        digraph.addEdge(3, 4);

        digraph.addEdge(2, 1);
        digraph.addEdge(4, 1);
        digraph.addEdge(4, 3);

        assertTrue(digraph.isBiparti());
    }

    @Order(1)
    @Test
    public void testNoBiparti(){
        DiGraph digraph = new DiGraph(5);

        digraph.addEdge(1, 0);
        digraph.addEdge(1, 4);
        digraph.addEdge(3, 0);
        digraph.addEdge(3, 2);
        digraph.addEdge(3, 4);

        digraph.addEdge(0, 4); // HERE

        digraph.addEdge(2, 1);
        digraph.addEdge(4, 1);
        digraph.addEdge(4, 3);

        assertFalse(digraph.isBiparti());
    }

    @Order(1)
    @Test
    public void testReverse(){
        DiGraph diGraph = new DiGraph(5);

        diGraph.addEdge(0, 1);
        diGraph.addEdge(0, 2);
        diGraph.addEdge(2, 0);
        diGraph.addEdge(2, 3);
        diGraph.addEdge(0, 4);

        DiGraph rDiGraph = diGraph.reverse();

        assertTrue(rDiGraph.hasEdge(1, 0));
        assertTrue(rDiGraph.hasEdge(0, 2));
        assertTrue(rDiGraph.hasEdge(2, 0));
        assertTrue(rDiGraph.hasEdge(3, 2));
        assertTrue(rDiGraph.hasEdge(4, 0));

        assertFalse(rDiGraph.hasEdge(0, 1));
        assertFalse(rDiGraph.hasEdge(2, 3));
        assertFalse(rDiGraph.hasEdge(0, 4));
    }

    @Order(2)
    @Test
    public void testConnected(){
        DiGraph digraph = new DiGraph(6);

        digraph.addEdge(0, 1);
        digraph.addEdge(1, 2);
        digraph.addEdge(3, 2);
        digraph.addEdge(1, 4);
        digraph.addEdge(4, 3);
        digraph.addEdge(3, 4);

        digraph.addEdge(5, 2);
        digraph.addEdge(2, 0);

        assertFalse(digraph.isConnected());

        assertTrue(digraph.isConnected(0, 1));
        assertTrue(digraph.isConnected(0, 2));
        assertTrue(digraph.isConnected(0, 3));
        assertTrue(digraph.isConnected(0, 4));
        assertFalse(digraph.isConnected(0, 5));

        assertFalse(digraph.isConnected(5, 0));
        assertFalse(digraph.isConnected(5, 1));
        assertFalse(digraph.isConnected(5, 2));
        assertFalse(digraph.isConnected(5, 3));
        assertFalse(digraph.isConnected(5, 4));

        assertEquals(digraph.nComponents(), 2);

        digraph = new DiGraph(5);

        digraph.addEdge(0, 1);
        digraph.addEdge(1, 0);
        digraph.addEdge(1, 2);
        digraph.addEdge(2, 3);
        digraph.addEdge(3, 1);
        digraph.addEdge(3, 4);
        digraph.addEdge(4, 3);

        assertTrue(digraph.isConnected());

        assertEquals(digraph.nComponents(), 1);
    }

    @Order(2)
    @Test
    public void testBFS(){
        DiGraph digraph = new DiGraph(13);

        digraph.addEdge(0, 2);
        digraph.addEdge(0, 6);

        digraph.addEdge(1, 0);

        digraph.addEdge(2, 3);
        digraph.addEdge(2, 4);

        digraph.addEdge(3, 2);
        digraph.addEdge(3, 4);

        digraph.addEdge(4, 5);
        digraph.addEdge(4, 6);
        digraph.addEdge(4, 11);

        digraph.addEdge(5, 0);
        digraph.addEdge(5, 3);

        digraph.addEdge(6, 7);

        digraph.addEdge(7, 8);

        digraph.addEdge(8, 7);

        digraph.addEdge(9, 6);
        digraph.addEdge(9, 8);
        digraph.addEdge(9, 12);

        digraph.addEdge(10, 12);

        digraph.addEdge(11, 4);
        digraph.addEdge(11, 12);

        digraph.addEdge(12, 9);

        Iterable<Integer> iter = digraph.bfs(0, 12);

        int c = 0;

        for(int v : iter){
            if(c == 0) assertEquals(v, 0);
            else if(c == 1) assertEquals(v, 2);
            else if(c == 2) assertEquals(v, 4);
            else if(c == 3) assertEquals(v, 11);
            else if(c == 4) assertEquals(v, 12);
            c++;
        }

        assertEquals(c, 5);
    }
}
