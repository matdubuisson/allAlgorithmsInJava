package test.java;

public class StopWatch{
    long time;
    public StopWatch(){
        this.time = System.currentTimeMillis();
    }

    public float click(){
        return (float) (System.currentTimeMillis() - this.time) / 1000;
    }
}
