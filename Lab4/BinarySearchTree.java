package Lab4;

class Node {
    int data;
    Node parent;
    Node left;
    Node right;

    public Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
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
}

class BST {
    private Node root;

    public BST() {
        this.root = null;
    }

    public BST(int data) {
        this.root = new Node(data);
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
        System.out.println(b.find(101));
    }

}
