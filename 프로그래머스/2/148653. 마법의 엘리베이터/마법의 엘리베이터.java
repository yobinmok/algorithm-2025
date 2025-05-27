import java.util.*;

class Solution {
    
    /*
        엘리베이터 버튼의 합이 음수인 경우 움직이지 않습니다.
        0층으로 내려가는 데 필요한 마법사 돌의 최소개수
        +- 10^c (c >= 0)
        
        올림 내림의 경우로 나눠서 탐색 -> 각 경우의 수에 걸리는 개수....?
    */
    
    int LIMIT = 100000001;
    int len, min;
    
    public int solution(int storey) {
        min = Integer.MAX_VALUE;
        len = Integer.toString(storey).length();
        
        recur(storey, 0, 0);
        return min;
    }
    
    void recur(int num, int depth, int cnt){
        // if(depth == len+1) return;
        if(num == 0){
            // System.out.println(num + " " + depth + "/" + len + ", cnt: " + cnt) ;
            min = Math.min(min, cnt);
            return; 
        }
        
        int n = getNumber(num, depth);
        int up =  num + (int) Math.pow(10, depth+1) - n * (int) Math.pow(10, depth);
        int down = num - n * (int) Math.pow(10, depth);
        
        if(up < LIMIT) recur(up, depth+1, cnt+10-n);
        if(down >= 0) recur(down,  depth+1, cnt+n);
    }
    
    // 1500 1500 % 1000 = 500 / 100 = 5
    int getNumber(int n, int loc){
        return n % (int) Math.pow(10, loc+1) / (int)Math.pow(10, loc);
    }
    
}