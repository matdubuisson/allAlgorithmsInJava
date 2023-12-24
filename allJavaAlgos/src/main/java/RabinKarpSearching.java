package main.java;

public class RabinKarpSearching {
    public static int default_R = 10, default_Q = 256;
    public int R, Q;

    public RabinKarpSearching(int R, int Q) {
        this.R = R;
        this.Q = Q;
    }

    public RabinKarpSearching() {
        this(RabinKarpSearching.default_R, RabinKarpSearching.default_Q);
    }

    public long hash(String key, int M) {
        long h = 0;

        for (int i = 0; i < M; i++) {
            h = (this.R * h + key.charAt(i)) % this.Q;
        }

        return h;
    }

    public boolean equivalent(String string, String key, int i) {
        for (int j = 0; j < key.length(); j++) {
            if (string.charAt(i + j) != key.charAt(j)) return false;
        }

        return true;
    }

    public int search(String string, String key) {
        int N = string.length(), M = key.length(), i;
        long hashKey = this.hash(key, M);
        long curHash = this.hash(string, M); // c0 * R**1 + c1

        long RM = 1;

        for (i = 0; i < M - 1; i++) RM *= this.R;

        for (i = M; i < N; i++) {
            if (curHash == hashKey && this.equivalent(string, key, i - M)) return i - M;

            curHash = ( (curHash - (string.charAt(i - M) * RM) % Q) * this.R ) % this.Q;
        }

        return -1;
    }

    public static void main(String[] args) {
        RabinKarpSearching rks = new RabinKarpSearching();

        System.out.println(rks.search("bonjour je suis qqun", "je"));
    }
}
