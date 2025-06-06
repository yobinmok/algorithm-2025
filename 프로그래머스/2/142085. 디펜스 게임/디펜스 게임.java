import java.util.*;

class Solution {
    
    /*
        병사 < 적 : 게임 종료
        무적권 최대 k번 사용 가능
        최대한 많은 라운드를 진행하고 싶음
        
        dfs는 시간초과일 것 같은데
        dp로 풀 수 있는건가? 
    */
    
    public int solution(int n, int k, int[] enemy) {
        int size = enemy.length;
        int answer = size;
        
        // 피큐, 그리디
        PriorityQueue<Integer> que = new PriorityQueue<>();
        for(int i = 0; i<size; i++){
            que.add(enemy[i]);
            
            if(que.size() > k) 
                n -= que.poll();
            
            if(n < 0) return i;
        }
        return answer;
    }
}