package Lab1;

import java.util.Random;

class Vector {
    private int size = 0;
    private int[] data = new int[5];

    public void pop_back() {
        size--;
    }

    private void increase_size() {
        int[] newData = new int[data.length * 2];
        for (int i = 0; i < data.length; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    public void push_back(int value) {
        if (size == data.length) {
            increase_size();
        }
        data[size] = value;
        size++;
    }

    public int get_size() {
        return size;
    }

    public void print() {
        System.out.print("{");
        for (int i = 0; i < size; i++) {
            System.out.print(data[i]);
            if (i < size - 1) {
                System.out.print(',');
            }
        }
        System.out.println("}");
    }
}

class RandVector{
    private int key;
    private int nrElems = 0;
    private int size = 0;
    private int[] data = new int[5];
    private void increase_size() {
        int[] newData = new int[data.length * 2];
        for (int i = 0; i < data.length; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }
    public void delete(){
        this.pop();
    }

    public int pop(){
        if(nrElems == 0)
            throw new RuntimeException("Array is empty");
        nrElems--;
        Random rand = new Random();
        int r = rand.nextInt(0,size);
        while (data[r] == key) r = rand.nextInt(0,size);
        int d = data[r];
        data[r] = key;
        return d;
    }

    public void print(){
        if(nrElems == 0)
            throw new RuntimeException("Array is empty");
        Random rand = new Random();
        int r = rand.nextInt(0,size);
        while (data[r] == key) r = rand.nextInt(0,size);
        int d = data[r];
        System.out.println(d);
    }

    public RandVector() {
        Random rand = new Random();
        key = rand.nextInt(Integer.MIN_VALUE, Integer.MIN_VALUE/2);
    }

    public void push_back(int value) {
        if (size == data.length) {
            increase_size();
        }
        data[size] = value;
        size++;
        nrElems++;
    }
}

public class Vectors {

    static void main() {
        Vector vector = new Vector();
        System.out.println(vector.get_size());
        vector.print();
        vector.push_back(10);
        vector.print();
        System.out.println(vector.get_size());
        vector.push_back(20);
        vector.push_back(30);
        vector.push_back(40);
        vector.push_back(50);
        vector.push_back(60);
        System.out.println(vector.get_size());
        vector.print();
        vector.pop_back();
        vector.print();
        System.out.println(vector.get_size());
        vector.push_back(70);
        vector.print();
        System.out.println(vector.get_size());

        RandVector rv = new RandVector();
        rv.push_back(1);
        rv.push_back(2);
        rv.push_back(3);
        try {
            rv.print();
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }

    }


}
