package main.java;

import jdk.nashorn.internal.runtime.regexp.JoniRegExp;

import java.net.ConnectException;
import java.util.Arrays;

public class Graph implements ADT.Graph{
    private final int V;
    private int E;
    private LinkedNodeBag<Integer>[] adjs;

    public Graph(int V){
        this.V = V;
        this.E = 0;

        this.adjs = new LinkedNodeBag[V];

        for(int i = 0; i < V; i++) this.adjs[i] = new LinkedNodeBag<Integer>();
    }

    @Override
    public int V() {
        return this.V;
    }

    @Override
    public int E() {
        return this.E;
    }

    @Override
    public void addEdge(int v, int w) {
        this.adjs[v].add(w);
        this.adjs[w].add(v);
        this.E++;
    }

    @Override
    public boolean hasEdge(int v, int w){
        for(int wp : this.adjs[v]){
            if(wp == w) return true;
        }

        return false;
    }

    @Override
    public Iterable<Integer> adj(int v) {
        return this.adjs[v];
    }

    private class Cycle{
        private boolean[] marked = new boolean[Graph.this.V];
        private int[] edgeTo = new int[Graph.this.V];
        private LinkedNodeStack<Integer> cycle = null;

        public Cycle(){
            for(int v = 0; v < Graph.this.V; v++){
                if(!this.marked[v]){
                    this.edgeTo[v] = v;
                    this.dfs(v, v); // In case where some parts are disconnected
                }
            }
        }

        private void dfs(int v, int u){
            if(this.cycle != null) return;

            this.marked[v] = true;

            for(int w : Graph.this.adj(v)){
                if(!this.marked[w]){
                    this.edgeTo[w] = v;
                    this.dfs(w, v);
                } else if(w != u){ // If w == u, so the previous call I was at u so I'm just from u that has been marked previously
                    this.cycle = new LinkedNodeStack<Integer>();

                    for(int i = v; i != w; i = this.edgeTo[i]){
                        this.cycle.push(i);
                    }

                    this.cycle.push(w);
                    break;
                }
            }
        }

        public Iterable<Integer> getCycle(){
            return this.cycle;
        }
    }

    @Override
    public boolean hasCycle() {
        return (new Cycle()).getCycle() != null;
    }

    public Iterable<Integer> getCycle(){
        return (new Cycle()).getCycle();
    }

    private class Color{
        private boolean[] marked = new boolean[Graph.this.V];
        private boolean[] color = new boolean[Graph.this.V];
        private boolean isBiparti = true;

        public Color(){
            for(int v = 0; v < Graph.this.V; v++){
                if(!this.marked[v]){
                    this.color[v] = true;
                    this.dfs(v);
                }
            }
        }

        private void dfs(int v){
            this.marked[v] = true;

            for(int w : Graph.this.adj(v)){
                if(!this.isBiparti) return;

                if(!this.marked[w]){
                    this.color[w] = !this.color[v]; // Look here, it takes the opposite color of the edgedTo node
                    this.dfs(w);
                } else if(this.color[w] == this.color[v]) this.isBiparti = false;
            }
        }

        public boolean isBiparti(){
            return this.isBiparti;
        }
    }

    @Override
    public boolean isBiparti() {
        return (new Color()).isBiparti;
    }

    private class Connection{
        private boolean[] marked = new boolean[Graph.this.V];
        private int[] ids = new int[Graph.this.V];
        private int id = 0;

        public Connection(){
            for(int v = 0; v < Graph.this.V; v++){
                if(!this.marked[v]){
                    this.dfs(v);
                    this.id++;
                }
            }
        }

        private void dfs(int v){
            this.marked[v] = true;
            this.ids[v] = this.id;

            for(int w : Graph.this.adj(v)){
                if(!this.marked[w]){
                    this.dfs(w);
                }
            }
        }

        public boolean isConnected(int v, int w){
            return this.ids[v] == this.ids[w];
        }

        public boolean areAllConnected(){
            return this.id == 1;
        }

        public int nComponents(){
            return this.id;
        }
    }

    @Override
    public boolean isConnected(int v, int w){
        return (new Connection()).isConnected(v, w);
    }

    @Override
    public boolean isConnected(){
        return (new Connection()).areAllConnected();
    }

    @Override
    public int nComponents(){
        return (new Connection()).nComponents();
    }

    private class BFS{
        private boolean[] marked = new boolean[Graph.this.V];
        private int[] edgeTo = new int[Graph.this.V];
        private int v;

        public BFS(int v){
            this.v = v;
            this.bfs(v);
        }

        private void bfs(int v){
            LinkedNodeQueue<Integer> queue = new LinkedNodeQueue<Integer>();
            queue.enqueue(v);
            this.edgeTo[v] = v;

            while(!queue.empty()){
                v = queue.dequeue();
                this.marked[v] = true;

                for(int w : Graph.this.adj(v)){
                    if(!this.marked[w]){
                        this.marked[w] = true;
                        this.edgeTo[w] = v;
                        queue.enqueue(w);
                    }
                }
            }
        }

        public Iterable<Integer> getPath(int u){
            LinkedNodeStack<Integer> stack = new LinkedNodeStack<Integer>();

            for(int v = u; v != this.v; v = this.edgeTo[v]) stack.push(v);

            stack.push(this.v);
            return stack;
        }
    }

    @Override
    public Iterable<Integer> bfs(int v, int u){
        return (new BFS(v)).getPath(u);
    }
}
