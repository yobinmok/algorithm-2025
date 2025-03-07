import java.util.*;

class Solution {
    /*
    같은 시간에 게임을 이용하는 사람이 m명 늘어날 때마다 서버 1대 추가
    증설된 서버는 k시간 동안 운영
    하루동안 모든 게임 이용자가 게임하기 위해 서버를 최소 몇 번 증설해야 하는가
    
    서버 별 끝나는 시간을 어떻게 처리하지.. Queue?
    */
    public int solution(int[] players, int m, int k) {
        int answer = 0, cur = 0;
        Queue<Integer> time = new ArrayDeque<>();
        
        for(int i = 0; i<players.length; i++){
            while(time.peek() != null && time.peek() == i){
                cur -= 1;
                time.poll();
            }
            
            if(players[i] < m) continue;
            if(players[i] > m * cur){ // 서버 증설이 필요한 경우
                int add = players[i] / m - cur;
                cur += add;
                answer += add;
                for(int j = 0; j<add; j++){
                    time.add(i+k);
                }
            }
        }
        
        return answer;
    }
}