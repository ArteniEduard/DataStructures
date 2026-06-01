package Lab2;

import java.util.*;

public class Deques {

    static void main() {
        int[] a = {4, 2, 1, 3, 5};
        int k = 3;

        Problema1(a, k);
    }

    static void Problema1(int[] a, int k) {

        Deque<Integer> dq = new ArrayDeque<>();

        for (int i = 0; i < a.length; i++) {

            // 1. Scoatem elementele iesite din fereastra
            while (!dq.isEmpty() && dq.peekFirst() <= i - k) {
                dq.pollFirst();
            }

            // 2. Mentinem deque-ul crescator
            while (!dq.isEmpty() && a[dq.peekLast()] >= a[i]) {
                dq.pollLast();
            }

            // 3. Adaugam indicele curent
            dq.addLast(i);

            if (i >= k - 1) {
                System.out.print(a[dq.peekFirst()] + " ");
            }
        }
    }



}
