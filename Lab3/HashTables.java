package Lab3;

import java.util.LinkedList;

class HashTable {
    private int M = 16;
    private LinkedList<Integer>[] table;
    private int size = 0;

    public HashTable() {
        table = new LinkedList[M];

        for (int i = 0; i < M; i++) {
            table[i] = new LinkedList<>();
        }
    }

    private int hash(int x) {
        return Math.abs(x) % M;
    }

    public void insert(int x) {

        if (exists(x))
            return;

        table[hash(x)].add(x);
        size++;

        if ((double) size / M > 0.75) {
            resize();
        }
    }

    public boolean exists(int x) {
        return table[hash(x)].contains(x);
    }

    public void erase(int x) {
        if (table[hash(x)].remove(Integer.valueOf(x))) {
            size--;
        }
    }

    private void resize() {

        int oldM = M;
        LinkedList<Integer>[] oldTable = table;

        M *= 2;

        table = new LinkedList[M];

        for (int i = 0; i < M; i++) {
            table[i] = new LinkedList<>();
        }

        for (int i = 0; i < oldM; i++) {

            for (int x : oldTable[i]) {

                int newHash = hash(x);
                table[newHash].add(x);
            }
        }
    }
}

public class HashTables {

    static long hash(String s) {

        long h = 0;
        long B = 31;

        for(char c : s.toCharArray()) {
            h = h * B + (c - 'a' + 1);
        }

        return h;
    }

    static void main(String[] args) {

        System.out.println(hash("abirege"));

        /*
        HashTable h = new HashTable();

        h.insert(10);
        h.insert(20);
        h.insert(1030);

        System.out.println(h.exists(20));

        h.erase(20);

        System.out.println(h.exists(20));

         */
    }
}