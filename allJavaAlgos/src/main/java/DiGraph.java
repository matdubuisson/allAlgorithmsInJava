package main.java;

import java.util.Arrays;

public class DiGraph implements ADT.DiGraph{
    private final int V;
    private int E = 0;
    private LinkedNodeBag<Integer>[] adjs;

    public DiGraph(int V){
        this.V = V;
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
        this.E++;
    }

    @Override
    public boolean hasEdge(int v, int w) {
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
        private boolean[] marked = new boolean[DiGraph.this.V];
        private boolean[] onStack = new boolean[DiGraph.this.V]; // NEED ONSTACK FOR ELSE CLAUSE
        private int[] edgeTo = new int[DiGraph.this.V];

        LinkedNodeStack<Integer> cycle = null;

        public Cycle(){
            for(int v = 0; v < DiGraph.this.V; v++){
                if(!this.marked[v]){
                    this.edgeTo[v] = v;
                    this.dfs(v, v);
                }
            }
        }

        private void dfs(int v, int u){
            if(this.cycle != null) return;

            this.marked[v] = true;
            this.onStack[v] = true;

            for(int w : DiGraph.this.adj(v)){
                if(!this.marked[w]){
                    this.edgeTo[w] = v;
                    this.dfs(w, v);
                } else if(onStack[w] && w != u){ // Very important to check the presence of the neighbors on the stack
                    this.cycle = new LinkedNodeStack<Integer>();

                    for(int i = v; i != w; i = this.edgeTo[i]) this.cycle.push(i);

                    this.cycle.push(w);
                    break;
                }
            }

            this.onStack[v] = false;
        }

        public boolean hasCycle(){
            return this.cycle != null;
        }

        public Iterable<Integer> getCycle(){
            return this.cycle;
        }
    }

    @Override
    public boolean hasCycle() {
        return (new Cycle()).hasCycle();
    }

    @Override
    public Iterable<Integer> getCycle() {
        return (new Cycle()).getCycle();
    }

    private class Color{
        private boolean[] marked = new boolean[DiGraph.this.V];
        private boolean[] onStack = new boolean[DiGraph.this.V]; // NEED ONSTACK FOR ELSE CLAUSE
        private boolean[] color = new boolean[DiGraph.this.V];
        private boolean isBiparti = true;

        public Color(){
            for(int v = 0; v < DiGraph.this.V; v++){
                if(!this.marked[v]){
                    this.color[v] = true;
                    this.dfs(v);
                }
            }
        }

        private void dfs(int v){
            if(!this.isBiparti) return;

            this.marked[v] = true;
            this.onStack[v] = true;

            for(int w : DiGraph.this.adj(v)){
                if(!this.marked[w]){
                    this.color[w] = !this.color[v];
                    this.dfs(w);
                } else if(this.onStack[w] && this.color[w] == this.color[v]) this.isBiparti = false;
            }

            this.onStack[v] = false;
        }

        public boolean isBiparti(){
            return this.isBiparti;
        }
    }

    @Override
    public boolean isBiparti() {
        return (new Color()).isBiparti();
    }

    private class Connection{
        private boolean[] marked = new boolean[DiGraph.this.V];
        // private boolean[] onStack = new boolean[DiGraph.this.V]; // NEED ONSTACK FOR ELSE CLAUSE
        private int[] ids = new int[DiGraph.this.V];
        private int id = 0;
        private LinkedNodeStack<Integer> stack = new LinkedNodeStack<Integer>();

        public Connection(){ // Use Kosaraju algorithm
            DiGraph reverse = DiGraph.this.reverse();

            int v;

            for(v = 0; v < DiGraph.this.V; v++){
                if(!this.marked[v]){
                    this.reversePostOrder(reverse, v);
                }
            }

            for(v = 0; v < DiGraph.this.V; v++) this.marked[v] = false;

            if(!this.stack.empty()) {
                do{
                    v = this.stack.pop();

                    if (!this.marked[v]) {
                        this.dfs(v);
                        this.id++;
                    }
                } while (!this.stack.empty());
            }
        }

        private void reversePostOrder(DiGraph reverse, int v){
            this.marked[v] = true;

            for(int w : reverse.adj(v)){
                if(!this.marked[w]){
                    this.reversePostOrder(reverse, w);
                }
            }

            this.stack.push(v);
        }

        private void dfs(int v){
            this.marked[v] = true;
            this.ids[v] = this.id;

            for(int w : DiGraph.this.adj(v)){
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
    public boolean isConnected(int v, int w) {
        return (new Connection()).isConnected(v, w);
    }

    @Override
    public boolean isConnected() {
        return (new Connection()).areAllConnected();
    }

    @Override
    public int nComponents() {
        return (new Connection()).nComponents();
    }

    private class BFS{
        private boolean[] marked = new boolean[DiGraph.this.V];
        private int[] edgeTo = new int[DiGraph.this.V];
        private LinkedNodeQueue<Integer> queue = new LinkedNodeQueue<Integer>();

        private final int v;

        public BFS(int v){
            this.v = v;
            this.bfs(v);
        }

        private void bfs(int v){
            this.marked[v] = true;
            this.edgeTo[v] = v;
            this.queue.enqueue(v);

            int vp;

            while(!this.queue.empty()){
                vp = this.queue.dequeue();

                for(int w : DiGraph.this.adj(vp)){
                    if(!this.marked[w]){
                        this.marked[w] = true;
                        this.edgeTo[w] = vp;
                        this.queue.enqueue(w);
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
    public Iterable<Integer> bfs(int v, int w) {
        return (new BFS(v)).getPath(w);
    }

    @Override
    public DiGraph reverse() {
        DiGraph digraph = new DiGraph(this.V);

        for(int v = 0; v < this.V; v++){
            for(int w : this.adj(v)){
                digraph.addEdge(w, v);
            }
        }

        return digraph;
    }
}
