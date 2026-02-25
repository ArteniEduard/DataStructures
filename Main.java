class Vector{
    private int size =0;
    private int[] data = new int[5];
    public void pop_back(){
        size--;
    }
    private void increase_size(){
        int[] newData = new int[data.length * 2];
        for (int i = 0; i < data.length; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }
    public void push_back(int value){
        if(size == data.length){
            increase_size();
        }
        data[size] = value;
        size++;
    }
    public int get_size(){
        return size;
    }
    public void print(){
        System.out.print("{");
        for (int i = 0; i < size; i++) {
            System.out.print(data[i]);
            if(i < size-1){
                System.out.print(',');
            }
        }
        System.out.println("}");
    }
}

public class Main {
    public static void main(String[] args) {
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


    }
}