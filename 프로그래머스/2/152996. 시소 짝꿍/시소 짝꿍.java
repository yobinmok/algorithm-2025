import java.util.*;

class Solution {
    
    public long solution(int[] weights) {
        int size = weights.length;
        long answer = 0;
        
        Arrays.sort(weights);
        Map<Double, Integer> map = new HashMap<>(); // 무게: 무게 갯수
        for(int i = 0; i<size; i++){
            double a = weights[i] * 1.0;
            double b = weights[i] * 2.0/3.0;
            double c = weights[i] * 2.0/4.0;
            double d = weights[i] * 3.0/4.0;
            
            if(map.containsKey(a)) answer += map.get(a);
            if(map.containsKey(b)) answer += map.get(b);
            if(map.containsKey(c)) answer += map.get(c);
            if(map.containsKey(d)) answer += map.get(d);
            
            map.put(a, map.getOrDefault(a, 0)+1);
        }
        
        return answer;
    }
}