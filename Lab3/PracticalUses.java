package Lab3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PracticalUses {

    static void main() {
        threeSum(List.of(16,0,0,4,2,45,6,2,16,35,6,3,10,15,20,16,100),16);
    }

    static void threeSum(List<Integer> a, int s){
        Map<Integer,List<Integer>> map = new HashMap<>();
        for(int i = 0; i < a.size(); i++){
            for(int j = i + 1; j < a.size(); j++){
                int sum = a.get(i) + a.get(j);
                int remaining = s - sum;
                map.put(remaining,List.of(i,j));
            }
        }
//        System.out.println(map);
        for(int i = 2; i < a.size(); i++){
            if(map.containsKey(a.get(i))){
                if(map.get(a.get(i)).get(0) != i && map.get(a.get(i)).get(1) != i){
                    System.out.println(i + " " + map.get(a.get(i)).get(0) + " " + map.get(a.get(i)).get(1));
                    return;
                }
            }
        }
    }
}
