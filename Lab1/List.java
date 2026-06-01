package Lab1;

class Node {
    int data;
    Node next;
    Node previous;
}

class LinkedList {
    Node head;
    Node tail;

    void removeTail() {
        if (tail == null) {
            return;
        }
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            tail = tail.next;
            tail.previous = null;
        }
    }

    void removeHead() {
        if (head == null) {
            return;
        }
        head = head.next;
        head.previous = null;

    }

    void insertEnd(int data) {
        Node newNode = new Node();
        newNode.data = data;
        if (tail == null) {
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
        }
    }

    void insertStart(int data) {
        Node newNode = new Node();
        newNode.data = data;
        if (head == null) {
            head = newNode;
        } else {
            head.previous = newNode;
            newNode.next = head;
            head = newNode;
        }
    }

    Node getNode(int data) {
        Node current = head;
        while (current.next != head) {
            if (current.next.data == data) {
                return current.next;
            }
            current = current.next;
        }
        return null;
    }

    Node getNodeByIndex(int index) {
        Node current = head;
        int i = 0;
        for (; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    void insertAtIndex(int data, int index) {
        Node node = getNodeByIndex(index);
        Node next = node.next;
        Node newNode = new Node();
        newNode.data = data;

        node.next = newNode;
        newNode.previous = node;

        next.previous = newNode;
        newNode.next = next;
    }

    void eraseAtIndex(int index) {
        Node node = getNodeByIndex(index);
        node.previous.next = node.next;
        node.next.previous = node.previous;
    }
}

public class List {
    LinkedList list = new LinkedList();
}
