package Lab4;

import java.util.ArrayList;
import java.util.List;

class Node {
    private static int deserializationIndex = 0;
    int data;
    Node parent;
    Node left;
    Node right;

    public Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    public void deserialize(List<Integer> serialization) {
        if (deserializationIndex == serialization.size()) {
            return;
        } else if (serialization.get(deserializationIndex) == null) {
            deserializationIndex++;
            return;
        }
        this.data = serialization.get(deserializationIndex++);

        if (serialization.get(deserializationIndex) != null) {
            this.left = new Node(serialization.get(deserializationIndex));
            this.left.deserialize(serialization);
        } else {
            this.left = null;
            deserializationIndex++;
        }

        if (serialization.get(deserializationIndex) != null) {
            this.right = new Node(serialization.get(deserializationIndex));
            this.right.deserialize(serialization);
        } else {
            this.right = null;
            deserializationIndex++;
        }

    }

    public void serialize(ArrayList<Integer> result) {
        result.add(data);
        if (left != null) left.serialize(result);
        else result.add(null);
        if (right != null) right.serialize(result);
        else result.add(null);
    }

    public void resetDeserialization() {
        deserializationIndex = 0;
    }

    public Node find(int data) {
        if (this.data == data) {
            return this;
        } else if (this.data > data) {
            if (this.left != null) {
                return this.left.find(data);
            } else {
                return null;
            }
        } else {
            if (this.right != null) {
                return this.right.find(data);
            } else {
                return null;
            }
        }
    }

    public boolean validate() {
        if (isLeaf()) {
            return true;
        } else {
            boolean result = true;
            if (left != null) {
                if (this.data < left.data) {
                    return false;
                } else {
                    result = result && left.validate();
                }
            }
            if (right != null) {
                if (this.data > right.data) {
                    return false;
                } else {
                    result = result && right.validate();
                }
            }
            return result;
        }
    }

    public Node successor() {
        if (this.right == null) {
            Node current = this;
            Node p = this.parent;

            while (p != null && p.right == current) {
                current = p;
                p = p.parent;
            }

            return p;
        } else {
            return this.inOrderSuccessor();
        }
    }

    public void insert(int data) {
        if (this.data > data) {
            if (this.left != null) {
                this.left.insert(data);
            } else {
                this.left = new Node(data);
                this.left.parent = this;
            }
        } else {
            if (this.right != null) {
                this.right.insert(data);
            } else {
                this.right = new Node(data);
                this.right.parent = this;
            }
        }
    }

    public boolean isFull() {
        return left != null && right != null;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    public boolean isRoot() {
        return parent == null;
    }

    public Node onlyChild() {
        if (left == null) {
            return right;
        }
        return left;
    }

    public Node leftMost() {
        if (this.left == null) {
            return this;
        } else {
            return this.left.leftMost();
        }
    }

    public Node inOrderSuccessor() {
        if (this.right == null) {
            return null;
        } else {
            return right.leftMost();
        }
    }
}

class BST {
    public Node root;

    public BST() {
        this.root = null;
    }

    public BST(int data) {
        this.root = new Node(data);
    }

    public BST(List<Integer> serialization) {
        this.root = new Node(serialization.get(0));
        this.root.resetDeserialization();
        this.root.deserialize(serialization);
    }

    public ArrayList<Integer> serialize() {
        ArrayList<Integer> result = new ArrayList<>();
        root.serialize(result);
        return result;
    }

    public void insert(int data) {
        if (this.root == null) {
            this.root = new Node(data);
            this.root.parent = null;
        } else {
            this.root.insert(data);
        }
    }

    public void erase(int data) {
        Node nodeToErase = find(data);
        if (nodeToErase != null) {
            if (nodeToErase.isFull()) {
                Node succesor = nodeToErase.inOrderSuccessor();
                nodeToErase.data = succesor.data;
//                succesor.parent.
            } else {
                if (nodeToErase.isRoot()) {
                    this.root = null;
                } else {
                    if (nodeToErase.parent.data > nodeToErase.data) {
                        nodeToErase.parent.left = nodeToErase.onlyChild();
                    } else {
                        nodeToErase.parent.right = nodeToErase.onlyChild();
                    }
                }
            }
        } else {
            System.err.println("Cannot erase a node that doesn't exist!");
        }
    }

    public Node find(int data) {
        if (this.root == null) {
            return null;
        } else if (this.root.data == data) {
            return this.root;
        } else if (this.root.data > data) {
            if (this.root.left != null) {
                return this.root.left.find(data);
            } else {
                return null;
            }
        } else {
            if (this.root.right != null) {
                return this.root.right.find(data);
            } else {
                return null;
            }
        }
    }
}

public class BinarySearchTree {
    static void main() {
        BST b = new BST();
        b.insert(10);
        b.insert(5);
        b.insert(15);
        List<Integer> serialization = b.serialize();
        System.out.println(serialization);
        BST c = new BST(serialization);
        System.out.println(c.serialize());
    }

}
