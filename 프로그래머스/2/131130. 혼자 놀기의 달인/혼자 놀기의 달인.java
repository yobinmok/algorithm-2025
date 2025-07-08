import java.util.*;

class Solution {
    
    /*
        문제 개복잡하네 주글래?
        수를 하나 정하고 그 수보다 작거나 같은 숫자 카드 준비
        상자는 카드 수만큼 있음
        
        카드는 무작위로 정렬됨
        - 임의의 상자를 하나 선택하여 열기 -> 그 숫자 순서의 박스 열기 -> 열어야하는 상자가 이미 열려있을 때까지 반복 => 1번그룹
        - 남은 상자 중 하나를 선택해 이미 열려있는 상자를 만날 때까지 상자 열기 => 2번 그룹
        각 그룹의 상자수를 곱한 값이 게임의 점수
        최고 점수를 구해라.
        
        임의의 상자를 뭐를 선택하냐
        dfs, 백트래킹?
    */
    
    public int size, cards[];
    public boolean[] opened;
    
    public int solution(int[] cards) {
        this.cards = cards;
        size = cards.length;
        int max = 0;
        for(int i = 0; i<size; i++){ // 그룹 1의 임의 숫자를 고름
            opened = new boolean[size];
            int max1 = dfs(i, 0);
            int max2 = 0;
            
            for(int j = 0; j<size; j++){ // 그룹 2의 임의 숫자를 고름
                if(opened[j]) continue;
                max2 = Math.max(dfs(j, 0), max2);
            }
            max = Math.max(max, max1 * max2);
        }
        return max;
    }
    
    int dfs(int cur, int cnt){
        if(opened[cur]){
            return cnt;
        }
        
        opened[cur] = true;
        return dfs(cards[cur]-1, cnt+1);
    }
}