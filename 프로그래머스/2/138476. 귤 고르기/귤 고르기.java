import java.util.*;

class Solution {
    
    /*
        k를 고를 때 서로 다른 종류의 수의 최솟값을 구해라.
        그리디..?
        종류를 줄이려면 많이 있는 과일을 사용하는 게 좋자나
    */
    public int solution(int k, int[] tangerine) {
        int size = tangerine.length;
        int answer = 0, sum = 0;
        
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i<size; i++){
            map.put(tangerine[i], map.getOrDefault(tangerine[i], 0)+1);
        }
        
        List<Integer> count = new ArrayList<>();
        for(int key: map.keySet()){
            count.add(map.get(key));
        }
        count.sort(Comparator.reverseOrder());
        
        for(int c: count){
            if(sum >= k) break;
            sum += c;
            answer += 1;
        }
        
        return answer;
    }
}