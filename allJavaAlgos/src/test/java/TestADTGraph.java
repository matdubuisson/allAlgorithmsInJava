package test.java;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import main.java.*;

public class TestADTGraph{
    @Order(0)
    @Test
    public void testGraph(){
        Graph graph = new Graph(10);

        assertEquals(graph.V(), 10);
        assertEquals(graph.E(), 0);

        graph.addEdge(0, 3);
        graph.addEdge(0, 4);
        graph.addEdge(5, 4);

        assertTrue(graph.hasEdge(4, 5));
        assertFalse(graph.hasEdge(1, 3));

        assertEquals(graph.V(), 10);
        assertEquals(graph.E(), 3);

        Iterable<Integer> iter = graph.adj(0);

        int i = 0;

        for(int w : iter){
            assertTrue(w == 3 || w == 4);
            assertTrue(i < 2);
            i++;
        }
    }

    @Order(1)
    @Test
    public void testCycle(){
        Graph graph = new Graph(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 4);
        graph.addEdge(2, 4);
        graph.addEdge(1, 3);
        graph.addEdge(3, 4);

        assertTrue(graph.hasCycle());

        Iterable<Integer> iter = graph.getCycle();

        assertTrue(iter != null);

        int preW = -1;

        for(int w : iter){
            if(preW != -1) assertTrue(graph.hasEdge(preW, w));

            preW = w;
        }

        for(int w : iter){
            assertTrue(graph.hasEdge(preW, w));
            break;
        }

        assertTrue(preW != -1);
    }

    @Order(1)
    @Test
    public void testNoCycle() {
        Graph graph = new Graph(7);
        graph.addEdge(1, 3);
        graph.addEdge(1, 0);
        graph.addEdge(2, 4);
        graph.addEdge(5, 2);
        graph.addEdge(6, 5);

        assertFalse(graph.hasCycle());

        Iterable<Integer> iter = graph.getCycle();

        assertTrue(iter == null);
    }

    @Order(1)
    @Test
    public void testBiparti(){
        Graph graph = new Graph(5);
        graph.addEdge(1, 0);
        graph.addEdge(1, 4);
        graph.addEdge(3, 0);
        graph.addEdge(3, 2);
        graph.addEdge(3, 4);

        assertTrue(graph.isBiparti());
    }

    @Order(1)
    @Test
    public void testNoBiparti(){
        Graph graph = new Graph(5);
        graph.addEdge(1, 0);
        graph.addEdge(1, 3); // HERE
        graph.addEdge(3, 0);
        graph.addEdge(3, 2);
        graph.addEdge(3, 4);

        assertFalse(graph.isBiparti());
    }

    @Order(1)
    @Test
    public void testConnected(){
        Graph graph = new Graph(5);

        assertFalse(graph.isConnected());

        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(3, 4);

        assertFalse(graph.isConnected());
        assertEquals(graph.nComponents(), 2);

        assertTrue(graph.isConnected(0, 2));
        assertFalse(graph.isConnected(2, 4));

        graph.addEdge(0, 3);

        assertTrue(graph.isConnected());
        assertEquals(graph.nComponents(), 1);

        assertTrue(graph.isConnected(0, 2));
        assertTrue(graph.isConnected(2, 4));
    }

    @Order(2)
    @Test
    public void testBFS(){
        Graph graph = new Graph(6);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);

        graph.addEdge(2, 4);

        graph.addEdge(1, 5);
        graph.addEdge(5, 4);

        graph.addEdge(3, 1);

        Iterable<Integer> iter = graph.bfs(0, 4);

        int c = 0;

        for(int v : iter){
            if(c == 0) assertEquals(v, 0);
            else if(c == 1) assertEquals(v, 2);
            else if(c == 2) assertEquals(v, 4);
            c++;
        }

        assertEquals(c, 3);
    }
}
