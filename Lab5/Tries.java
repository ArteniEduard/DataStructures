package Lab5;

import org.junit.jupiter.api.Test;

import static Lab5.Tries.maximumXor;
import static org.junit.jupiter.api.Assertions.*;

class Node {
    Node[] child = new Node[2];
}

class BinaryTrie {

    Node root = new Node();

    void insert(int x) {

        Node curr = root;

        for (int i = 31; i >= 0; i--) {

            int bit = (x >> i) & 1;

            if (curr.child[bit] == null) {
                curr.child[bit] = new Node();
            }

            curr = curr.child[bit];
        }
    }

    int maxXor(int x) {

        Node curr = root;

        int result = 0;

        for (int i = 31; i >= 0; i--) {

            int bit = (x >> i) & 1;
            int wanted = 1 - bit;

            if (curr.child[wanted] != null) {

                result |= (1 << i);
                curr = curr.child[wanted];

            } else {

                curr = curr.child[bit];
            }
        }

        return result;
    }
}

class TrieNode {

    TrieNode[] next;
    boolean isWord;

    TrieNode() {
        next = new TrieNode[26];
        isWord = false;
    }
}

class Trie {
    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String s) {

        TrieNode current = root;

        for (char c : s.toCharArray()) {

            int idx = c - 'a';

            if (current.next[idx] == null) {
                current.next[idx] = new TrieNode();
            }

            current = current.next[idx];
        }

        current.isWord = true;
    }

    public int longestPrefix(String s) {

        TrieNode current = root;

        int length = 0;

        for (char c : s.toCharArray()) {

            int idx = c - 'a';

            if (current.next[idx] == null) {
                break;
            }

            current = current.next[idx];
            length++;
        }

        return length;
    }

    public boolean exists(String s) {

        TrieNode current = root;

        for (char c : s.toCharArray()) {

            int idx = c - 'a';

            if (current.next[idx] == null) {
                return false;
            }

            current = current.next[idx];
        }

        return current.isWord;
    }

    public void erase(String s) {
        erase(root, s, 0);
    }

    private boolean erase(TrieNode node, String s, int pos) {

        if (pos == s.length()) {

            if (!node.isWord) {
                return false;
            }

            node.isWord = false;

            return isEmpty(node);
        }

        int idx = s.charAt(pos) - 'a';

        TrieNode child = node.next[idx];

        if (child == null) {
            return false;
        }

        boolean deleteChild = erase(child, s, pos + 1);

        if (deleteChild) {
            node.next[idx] = null;
        }

        return !node.isWord && isEmpty(node);
    }

    private boolean isEmpty(TrieNode node) {

        for (TrieNode child : node.next) {
            if (child != null) {
                return false;
            }
        }

        return true;
    }
}

class XorMaxTest {

    @Test
    void testSimpleCase() {

        int[] a = {2, 4};

        assertEquals(6, maximumXor(a));
    }

    @Test
    void testClassicExample() {

        int[] a = {3, 10, 5, 25, 2, 8};

        assertEquals(28, maximumXor(a));
    }

    @Test
    void testAllEqual() {

        int[] a = {7, 7, 7, 7};

        assertEquals(0, maximumXor(a));
    }
}

public class Tries {
    public static int maximumXor(int[] a) {

        BinaryTrie trie = new BinaryTrie();

        trie.insert(a[0]);

        int answer = 0;

        for (int i = 1; i < a.length; i++) {

            answer = Math.max(answer, trie.maxXor(a[i]));

            trie.insert(a[i]);
        }

        return answer;
    }

    static void main(String[] args) {

        Trie trie = new Trie();

        trie.insert("car");
        trie.insert("cat");
        trie.insert("dog");

        System.out.println(trie.exists("car"));
        System.out.println(trie.exists("cat"));
        System.out.println(trie.exists("dog"));
        System.out.println(trie.exists("cow"));

        System.out.println(trie.longestPrefix("catinca"));

        trie.erase("cat");

        System.out.println();

        System.out.println(trie.exists("car"));
        System.out.println(trie.exists("cat"));
        System.out.println(trie.exists("dog"));
    }
}
