/*
    각 곡괭이는 광물 5개를 캔 후에는 더 이상 사용할 수 없음
    최소한의 피로도로 광물을 캐려고 함
    
    - 한 번 사용한 곡괭이는 사용할 수 없을 때까지 사용
    - 광물은 주어진 순서대로
    - 모든 광물을 캐거나 / 사용할 곡괭이가 없을 때까지
    
    완탐(곡괭이 고르는 순서) -> dfs
    15P15는 1조...
*/
class Solution {
    
    int sum = 0, min = Integer.MAX_VALUE;
    int[] picks;
    String[] minerals;
    int[][] fatigue = {{1, 1, 1}, {5, 1, 1}, {25, 5, 1}}; // 다이아(0), 철(1), 돌(2)
    
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        this.picks = picks;
        this.minerals = minerals;
        
        for(int pick: picks) sum += pick;
        
        recur(0, 0, 0, "");
        return min;
    }
    
    // 사용한 곡괭이 수, 캔 광물 개수, 피로도
    public void recur(int pick, int mineral, int tired, String s){
        if(tired >= min) return;
        if(mineral == minerals.length){ // 모든 광물을 캔 경우
            // System.out.println("1: " + idx + " " + pick + " " + mineral + " " + tired + " => " + s);
            min = Math.min(min, tired);
            return;
        }
        if(pick == sum){ // 사용할 곡괭이가 없는 경우
            // System.out.println("2: " + idx + " " + pick + " " + mineral + " " + tired + " => " + s);
            min = Math.min(min, tired);
            return;
        }
        
        for(int i = 0; i<3; i++){
            if(picks[i] == 0) continue;
            picks[i] -= 1;
            int tempTired = tired;
            int m = 5;
            for(int j = 0; j<5; j++){
                if(j+mineral == minerals.length){
                    m = j;
                    break;
                }
                if(minerals[j+mineral].equals("diamond")){
                    tempTired += fatigue[i][0];
                }else if(minerals[j+mineral].equals("iron")){
                    tempTired += fatigue[i][1];
                }else{
                    tempTired += fatigue[i][2];
                }
            }
            recur(pick+1, mineral+m, tempTired, s + " " + i);
            picks[i] += 1;
        }
    }
}