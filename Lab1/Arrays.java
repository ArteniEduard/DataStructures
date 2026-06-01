package Lab1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Arrays {
    static void main() {
//        sumeSecv(List.of(1,2,3,4,5,6),List.of(0,4),List.of(5,5));
        twoSum(List.of(7, 3, 13, 6, 19), 20);
    }


    public static void sumeSecv(List<Integer> list, List<Integer> ls, List<Integer> rs) {
        int[] sume = new int[list.size() + 1];
        sume[0] = 0;

        for (int i = 0; i < list.size(); i++) {
            if (i == 0) sume[i + 1] = list.get(i);
            else sume[i + 1] = sume[i] + list.get(i);
        }

        for (int i = 0; i < ls.size(); i++) {
            int l = ls.get(i);
            int r = rs.get(i);
            System.out.println("[" + l + ", " + r + "] = " + (sume[r + 1] - sume[l]));
        }
    }

    public static void twoSum(List<Integer> list, int s) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            if (!map.containsKey(list.get(i))) {
                int tillS = s - list.get(i);
                map.put(tillS, i);
            } else {
                System.out.println(Math.min(i, map.get(list.get(i))) + " " + Math.max(i, map.get(list.get(i))));
            }
        }
    }
}
