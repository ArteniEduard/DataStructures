package Lab2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Stacks {

    static void main() {
//        NearestSmallerValues(List.of(6,4,15,8,90,20,10, 100));
//        System.out.println(Strabunica(List.of(2,5,6,6,2,3)));
//        removeDuplicates("bbssssigBBBossss",3);
//        P7("aaabbbbccddd");
    }


    static void P7(String s) {
        char[] chars = s.toCharArray();
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (char c : chars) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for (Character c : map.keySet()) {
            if (map.get(c) % 2 == 1) {
                System.out.print(c);
            }
        }

    }

    static boolean knows(int i, int j) {
        return false;
    }

    static void removeDuplicates(String s, int k) {
        Stack<Character> stack = new Stack<>();
        int streak = 1;
        for (int i = 0; i < s.length(); i++) {
            if (!stack.isEmpty()) {
                if (stack.peek() == s.charAt(i)) {
                    streak++;
                } else {
                    streak = 1;
                }
            }
            stack.push(s.charAt(i));
            if (streak == k) {
                for (int j = 0; j < k; j++) {
                    stack.pop();
                }
                streak = 1;
            }
        }
        System.out.println(stack);
    }

    static int problema4(List<Integer> list) {
        int candidate = 0;
        for (int i = 0; i < list.size(); i++) {
            if (knows(candidate, list.get(i))) {
                candidate = i;
            }
        }

        for (int i = 0; i < list.size(); i++) {
            if (knows(candidate, list.get(i))) {
                return -1; //niciunu
            }
            if (!knows(list.get(i), candidate)) {
                return -1; //nu e celeb
            }
        }
        return candidate;
    }

    static int Strabunica(List<Integer> heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;

        for (int i = 0; i <= heights.size(); i++) {

            int currentHeight;
            if (i == heights.size()) currentHeight = 0;
            else currentHeight = heights.get(i);

            while (!stack.isEmpty() && currentHeight < heights.get(stack.peek())) {

                int h = heights.get(stack.pop());

                int width;

                if (stack.isEmpty()) {
                    width = i;
                } else {
                    width = i - stack.peek() - 1;
                }

                maxArea = Math.max(maxArea, h * width);
            }

            stack.push(i);
        }

        return maxArea;
    }

    static void NearestSmallerValues(List<Integer> arr) {
        Stack<Integer> prevSmaller = new Stack<>();

        for (int i = 0; i < arr.size(); i++) {
            while (!prevSmaller.empty() && arr.get(prevSmaller.peek()) >= arr.get(i)) {
                prevSmaller.pop();
            }
            if (prevSmaller.empty()) System.out.print(-1 + " ");
            else System.out.print(prevSmaller.peek() + " ");
            prevSmaller.push(i);
        }
    }


}
