package Lab4;

import java.util.*;

class Heap {
    int[] data;
    int size = 0;
    int height = 1;

    public Heap() {
        this.data = new int[(1 << height) - 1];
    }

    private void resize() {
        height++;
        int[] newData = new int[(1 << height) - 1];
        System.arraycopy(data, 0, newData, 0, data.length);
        data = newData;
    }

    public void print() {
        System.out.print("[");
        for (int i = 0; i < size; i++) {
            System.out.print(data[i]);
            if (i != size - 1) {
                System.out.print(", ");
            }
        }
        if (size < data.length && size > 0) {
            System.out.print(", ");
        }
        for (int i = size; i < data.length; i++) {
            System.out.print("~");
            if (i != data.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public void insert(int x) {
        if (size == data.length) {
            resize();
        }

        data[size] = x;

        int index = size;
        size++;

        int parentIndex = (index - 1) / 2;
        while (index != 0 && x < data[parentIndex]) {

            data[index] = data[index] ^ data[parentIndex];
            data[parentIndex] = data[parentIndex] ^ data[index];
            data[index] = data[index] ^ data[parentIndex];

            index = parentIndex;
            parentIndex = (index - 1) / 2;
        }
    }

    public void pop() {
        if (size == 0) {
            throw new RuntimeException("Heap is empty");
        }

        data[0] = data[size - 1];
        size--;

        int index = 0;
        int leftChildIndex = 1;
        int rightChildIndex = 2;
        while (rightChildIndex < size && leftChildIndex < size && data[index] > Math.min(data[leftChildIndex], data[rightChildIndex])) {
            if (data[leftChildIndex] < data[rightChildIndex]) {
                data[index] = data[index] ^ data[leftChildIndex];
                data[leftChildIndex] = data[leftChildIndex] ^ data[index];
                data[index] = data[index] ^ data[leftChildIndex];
                index = leftChildIndex;
            } else {
                data[index] = data[index] ^ data[rightChildIndex];
                data[rightChildIndex] = data[rightChildIndex] ^ data[index];
                data[index] = data[index] ^ data[rightChildIndex];
                index = rightChildIndex;
            }
            leftChildIndex = index * 2 + 1;
            rightChildIndex = index * 2 + 2;
        }
    }

    public int top() {
        if (size == 0) {
            throw new RuntimeException("Heap is empty");
        }
        return data[0];
    }
}

class Entry {
    int value;
    int listIndex;

    Entry(int value, int listIndex) {
        this.value = value;
        this.listIndex = listIndex;
    }

}

class SpecialHeap {
    Entry[] data;
    int size = 0;
    int height = 1;

    public SpecialHeap() {
        this.data = new Entry[(1 << height) - 1];
    }

    private void resize() {
        height++;
        Entry[] newData = new Entry[(1 << height) - 1];
        System.arraycopy(data, 0, newData, 0, data.length);
        data = newData;
    }

    public void print() {
        System.out.print("[");
        for (int i = 0; i < size; i++) {
            System.out.print(data[i]);
            if (i != size - 1) {
                System.out.print(", ");
            }
        }
        if (size < data.length && size > 0) {
            System.out.print(", ");
        }
        for (int i = size; i < data.length; i++) {
            System.out.print("~");
            if (i != data.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public void insert(Entry x) {
        if (size == data.length) {
            resize();
        }

        data[size] = x;

        int index = size;
        size++;

        int parentIndex = (int) Math.floor((index - 1) / 2.0);
        while (index != 0 && x.value < data[parentIndex].value) {
            Entry t = data[index];
            data[index] = data[parentIndex];
            data[parentIndex] = t;

            index = parentIndex;
            parentIndex = (int) Math.floor((index - 1) / 2.0);
        }
    }

    public void pop() {

        if (size == 0) {
            throw new RuntimeException("Heap is empty");
        }

        data[0] = data[size - 1];
        size--;

        int index = 0;

        while (true) {

            int left = 2 * index + 1;
            int right = 2 * index + 2;

            int smallest = index;

            if (left < size &&
                    data[left].value < data[smallest].value) {

                smallest = left;
            }

            if (right < size &&
                    data[right].value < data[smallest].value) {

                smallest = right;
            }

            if (smallest == index) {
                break;
            }

            Entry tmp = data[index];
            data[index] = data[smallest];
            data[smallest] = tmp;

            index = smallest;
        }
    }

    public Entry top() {
        if (size == 0) {
            throw new RuntimeException("Heap is empty");
        }
        return data[0];
    }
}

class MedianHeap {
    PriorityQueue<Integer> minHeap =
            new PriorityQueue<>();

    PriorityQueue<Integer> maxHeap =
            new PriorityQueue<>(Collections.reverseOrder());

    void insert(int x) {

        if (maxHeap.isEmpty() || x <= maxHeap.peek()) {
            maxHeap.add(x);
        } else {
            minHeap.add(x);
        }

        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.add(maxHeap.poll());
        }

        if (minHeap.size() > maxHeap.size() + 1) {
            maxHeap.add(minHeap.poll());
        }
    }

    double getMedian() {

        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        }

        if (minHeap.size() > maxHeap.size()) {
            return minHeap.peek();
        }

        return (maxHeap.peek() + minHeap.peek()) / 2.0;
    }
}

public class Heaps {
    static ArrayList mergeK(ArrayList<LinkedList<Integer>> lists) {
        SpecialHeap heap = new SpecialHeap();
        ArrayList<Integer> result = new ArrayList<>();

        int i = 0;
        for (LinkedList<Integer> list : lists) {
            heap.insert(new Entry(list.removeFirst(), i));
            i++;
        }

        while (heap.size > 0) {
            Entry top = heap.top();
            heap.pop();

            result.add(top.value);

            if (!lists.get(top.listIndex).isEmpty()) {
                heap.insert(new Entry(lists.get(top.listIndex).removeFirst(), top.listIndex));
            }
        }

        return result;
    }

    static int interclasari(List<Integer> list) {
        Heap heap = new Heap();
        for (Integer integer : list) {
            heap.insert(integer);
        }

        int total = 0;

        while (heap.size > 1) {
            int min1 = heap.top();
            heap.pop();
            int min2 = heap.top();
            heap.pop();

            heap.insert(min1 + min2);
            total += min1 + min2;
        }
        return total;
    }

    static void main() {
        MedianHeap medianHeap = new MedianHeap();

        int[] values = {5, 15, 1, 3, 8, 7, 9};

        for (int x : values) {

            medianHeap.insert(x);

            System.out.println(
                    "Inserted: " + x +
                            ", median = " + medianHeap.getMedian()
            );
        }

//        System.out.println(interclasari(List.of(1,2,3,4)));

//        ArrayList<LinkedList<Integer>> lists = new ArrayList<>();
//        LinkedList<Integer> list1 = new LinkedList<>();
//        list1.add(1);
//        list1.add(5);
//        list1.add(13);
//        list1.add(20);
//        list1.add(24);
//        LinkedList<Integer> list2 = new LinkedList<>();
//        list2.add(3);
//        list2.add(6);
//        list2.add(18);
//        list2.add(27);
//        list2.add(34);
//        LinkedList<Integer> list3 = new LinkedList<>();
//        list3.add(0);
//        list3.add(7);
//        list3.add(14);
//        list3.add(21);
//        list3.add(24);
//        lists.add(list1);
//        lists.add(list2);
//        lists.add(list3);
//        System.out.println(mergeK(lists));


//        Heap heap = new Heap();
//        try {
//            System.out.println(heap.top());
//        } catch (RuntimeException e) {
//            System.out.println(e.getMessage());
//        }
//        heap.print();
//        heap.insert(3);
//        heap.print();
//        heap.insert(5);
//        heap.print();
//        heap.insert(2);
//        heap.print();
//        heap.insert(1);
//        heap.print();
//        heap.insert(7);
//        heap.insert(8);
//        heap.insert(100);
//        heap.print();
//        System.out.println(heap.top());
//        heap.pop();
//        heap.print();
//        heap.pop();
//        heap.print();
//        heap.pop();
//        heap.print();
//        heap.pop();
//        heap.print();
//        heap.pop();
//        heap.print();
//        heap.pop();
//        heap.print();
//        heap.pop();
//        heap.print();
    }
}
